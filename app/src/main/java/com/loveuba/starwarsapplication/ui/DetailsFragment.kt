package com.loveuba.starwarsapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.wrapper.Result
import com.loveuba.starwarsapplication.databinding.FragmentDetailsBinding
import com.loveuba.starwarsapplication.ui.adapter.FilmsAdapter
import com.loveuba.starwarsapplication.utils.Message
import com.loveuba.starwarsapplication.utils.addCountSuffix
import com.loveuba.starwarsapplication.utils.centimeterToFeet
import com.loveuba.starwarsapplication.utils.toEndpoint
import com.loveuba.starwarsapplication.viewmodel.DetailsViewModel
import com.loveuba.starwarsapplication.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//design fixings and optimize managing loading state
//optimize
//Documenting Processes
//Write Tests

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var bnd: FragmentDetailsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: DetailsViewModel by viewModels()
    private val filmsAdapter = FilmsAdapter()
    private lateinit var planet: String
    private lateinit var species: String
    private val args: DetailsFragmentArgs by navArgs()
    private var filmList = ArrayList<FilmData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bnd = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bnd.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        bnd.movieList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = filmsAdapter
            setHasFixedSize(true)
        }

        viewModel.actionGetFilms()

        /**
         * Observes the shared selected data from the search screen, use necessary data to request for other necessary data
         * */

        sharedViewModel.getCharacter.observe(viewLifecycleOwner) {
            bnd.detailWrap.visibility = View.VISIBLE
            bnd.nameTv.text = it.name
            bnd.birthYearVw.setDetailText(it.birthYear)
            bnd.heightVw.setDetailText("${it.height}/${centimeterToFeet(it.height)}")
            planet = it.homeWorld.toEndpoint()
            viewModel.actionGetPlanet(planet)
            if (it.species.isEmpty()) {
                bnd.lanuageTv.text = "N/A"
            } else {
                species = it.species.first().toEndpoint()
                viewModel.actionGetSpecies(species)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getFilmResult.collectLatest { result ->
                    if (!result.isLoading) {
                        bnd.rvProgressView.visibility = View.GONE
                        result.films.forEach {
                            if (it.characters.contains(args.characterId)) {
                                filmList.add(it)
                            } else {
                                filmList
                            }
                        }
                        filmsAdapter.submitList(filmList)

                        handleError(result.userMessages)
                    }
                }
            }
        }

        viewModel.getSpeciesResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    bnd.lanuageTv.text = result.value.language
                }
            }
        }

        viewModel.getPlanetResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Failed to Fetch Data",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Result.Loading -> {
                }
                is Result.Success -> {
                    bnd.homeWorldTv.text = result.value.name

                    if(!result.value.population.isDigitsOnly()){
                        bnd.populationVw.setDetailText(result.value.population)
                    }else {
                        bnd.populationVw.setDetailText(addCountSuffix(result.value.population.toLong()))
                    }
                }
            }
        }
    }

    private fun handleError(userMessage: List<Message>) {
        if (userMessage.isNotEmpty()) {
            Toast.makeText(requireContext(), userMessage.last().message, Toast.LENGTH_LONG).show()
            viewModel.updateUserMessage(userMessage.last().id)
        }
    }
}