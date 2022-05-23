package com.loveuba.starwarsapplication.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.loveuba.starwarsapplication.R
import com.loveuba.starwarsapplication.databinding.FragmentSearchBinding
import com.loveuba.starwarsapplication.ui.adapter.SearchAdapter
import com.loveuba.starwarsapplication.utils.Message
import com.loveuba.starwarsapplication.viewmodel.SharedViewModel
import com.loveuba.starwarsapplication.viewmodel.StarwarsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //Recyclerview visibility is populated after search, clear viewmodel, manage functionality and delete comment only after handling that
    private lateinit var bnd: FragmentSearchBinding
    private val homeViewModel: StarwarsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val searchAdapter = SearchAdapter()
    private val characterSearchQuery = MutableStateFlow("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bnd = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpViewModel()

    }

    fun setUpView() {
        bnd.searchList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
            setHasFixedSize(true)
        }

        bnd.clearSearch.setOnClickListener {
            bnd.searchField.text = null
            bnd.emptySearchTv.visibility = View.GONE
        }

        /**
         * *Observe for changes in search field text
         * */
        bnd.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                input: CharSequence?,
                start: Int,
                count: Int,
                before: Int
            ) {
            }

            override fun onTextChanged(
                input: CharSequence?,
                start: Int,
                count: Int,
                before: Int
            ) {
                if (input.toString().isNotEmpty()) {
                    characterSearchQuery.value = input.toString()
                    lifecycleScope.launch {
                        characterSearchQuery.debounce(200)
                            .filter { query ->
                                return@filter query.isNotBlank()
                            }
                            .distinctUntilChanged().collectLatest {
                                homeViewModel.actionSearch(query = it)
                            }
                    }
                } else {
                    //confirm this
                    searchAdapter.submitList(emptyList())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        /**
         * *Shares selected character data with detail screen upon navigation
         * */
        searchAdapter.setOnItemClickListener { position ->
            val character = searchAdapter.currentList[position]
            sharedViewModel.shareCharacter(character)
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(characterId = character.url))
        }
    }

    fun setUpViewModel() {

        /**
         * Sets up ViewModel related requests to update view states
         * */

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                homeViewModel.getSearchResult.collectLatest {
                    if (!it.isLoading) {
                        if (!bnd.searchField.text.isNullOrBlank() && it.characters.isNullOrEmpty()) {
                            bnd.emptySearchTv.visibility = View.VISIBLE
                        } else {
                            bnd.emptySearchTv.visibility = View.GONE
                        }
                        searchAdapter.submitList(it.characters)
                        bnd.searchList.visibility = View.VISIBLE

                        handleError(it.userMessages)
                    }
                }
            }
        }
    }

    /**
     * Handle error state
     * */

    private fun handleError(userMessage: List<Message>) {
        if (userMessage.isNotEmpty()) {
            Toast.makeText(requireContext(), userMessage.last().message, Toast.LENGTH_LONG).show()
            homeViewModel.updateUserMessage(userMessage.last().id)
        }
    }

}