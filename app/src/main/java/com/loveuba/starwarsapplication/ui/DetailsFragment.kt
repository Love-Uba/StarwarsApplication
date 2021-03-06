package com.loveuba.starwarsapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.wrapper.Result
import com.loveuba.starwarsapplication.databinding.FragmentDetailsBinding
import com.loveuba.starwarsapplication.ui.adapter.FilmsAdapter
import com.loveuba.starwarsapplication.utils.Util.addCountSuffix
import com.loveuba.starwarsapplication.utils.Util.centimeterToFeet
import com.loveuba.starwarsapplication.utils.toEndpoint
import com.loveuba.starwarsapplication.viewmodel.DetailsViewModel
import com.loveuba.starwarsapplication.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var bnd: FragmentDetailsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private val filmsAdapter = FilmsAdapter()
    private lateinit var planet: String
    private lateinit var species: String
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

        /*Request for all films to later be filtered by selected character*/

        detailsViewModel.actionGetFilms()

        /**
         * Observes the shared selected data from the search screen,
         * use necessary data to request for other detail data not provided in initial request
         * */

        sharedViewModel.getCharacter.observe(viewLifecycleOwner) {
            bnd.detailWrap.visibility = View.VISIBLE
            bnd.nameTv.text = it.name
            bnd.birthYearVw.setDetailText(it.birthYear)
            bnd.heightVw.setDetailText("${it.height}/${centimeterToFeet(it.height)}")
            planet = it.homeWorld.toEndpoint()
            detailsViewModel.actionGetPlanet(planet)
            if (it.species.isEmpty()) {
                bnd.lanuageTv.text = "N/A"
            } else {
                species = it.species.first().toEndpoint()
                detailsViewModel.actionGetSpecies(species)
            }
        }

        /**
         * Provides the planet name and population of the character's planet
         * */

        lifecycleScope.launchWhenStarted {
            detailsViewModel.getPlanetResult.collectLatest { result ->
                when (result) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        bnd.homeWorldTv.text = result.data?.name ?: "N/A"

                        if (result.data?.population?.isDigitsOnly() == false) {
                            result.data.let { bnd.populationVw.setDetailText(it.population) }
                        } else {
                            result.data?.population?.let {
                                addCountSuffix(
                                    it.toLong()
                                )
                            }?.let { bnd.populationVw.setDetailText(it) }
                        }
                    }
                    else -> {

                    }
                }
            }
        }

        /**
         * Observes the language of the given specie of the character
         * */

        lifecycleScope.launchWhenStarted {
            detailsViewModel.getSpeciesResult.collectLatest { result ->
                when (result) {
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        bnd.lanuageTv.text = result.data?.language ?: "N/A"
                    }
                    else -> {}
                }
            }
        }

        /**
         * The list of films received at this point is filtered
         * by the character's presence in the film's character list item
         * **/
        
        lifecycleScope.launchWhenStarted {
            detailsViewModel.getFilmResult.collectLatest { result ->

                when (result) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        bnd.rvProgressView.visibility = View.GONE
                        bnd.movieList.visibility = View.VISIBLE

                        result.data?.forEach {
                            if (it.characters.contains(args.characterId)) {
                                filmList.add(it)
                            } else {
                                filmList
                            }
                        }
                        filmsAdapter.submitList(filmList)
                    }
                    else -> {}
                }
            }
        }
    }
}