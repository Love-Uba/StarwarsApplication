package com.loveuba.starwarsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.data.models.SpeciesData
import com.loveuba.starwarsapplication.data.repository.DetailsUseCase
import com.loveuba.starwarsapplication.data.wrapper.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsUseCase,
) : ViewModel() {

    private val _requestedPlanet: MutableStateFlow<Result<PlanetData>> =
        MutableStateFlow(Result.NotLoading(null))
    val getPlanetResult: StateFlow<Result<PlanetData>> = _requestedPlanet

    private val _requestedSpecies: MutableStateFlow<Result<SpeciesData>> =
        MutableStateFlow(Result.NotLoading(null))
    val getSpeciesResult: StateFlow<Result<SpeciesData>> = _requestedSpecies

    private var _requestedFilms: MutableStateFlow<Result<List<FilmData>>> =
        MutableStateFlow(Result.NotLoading(null))
    val getFilmResult: StateFlow<Result<List<FilmData>>> get() = _requestedFilms


    fun actionGetPlanet(planetPath: String)  = viewModelScope.launch {
        try {
            _requestedPlanet.value = Result.Loading()
            detailsUseCase.getCharacterPlanet(planetPath).collect {
                _requestedPlanet.value = it
            }
        } catch (e: Exception) {
            _requestedPlanet.value = Result.Error(e.localizedMessage ?: "Error fetching data.")
        }
    }


    fun actionGetSpecies(speciesPath: String) = viewModelScope.launch {
        try {
            _requestedSpecies.value = Result.Loading()
            detailsUseCase.getAllSpecies(speciesPath).collect {
                _requestedSpecies.value = it
            }
        } catch (e: Exception) {
            _requestedSpecies.value = Result.Error(e.localizedMessage ?: "Error fetching data.")
        }
    }

    fun actionGetFilms() = viewModelScope.launch {
        try {
            _requestedFilms.value = Result.Loading()
            detailsUseCase.getAllFilms().collect {
                _requestedFilms.value = it
            }
        } catch (e: Exception) {
            _requestedFilms.value = Result.Error(e.localizedMessage ?: "Error fetching data.")
        }
    }
}











//        _requestedPlanet.value = Result.Loading
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                detailsUseCase.getCharacterPlanet(planetPath).collect {
//                    _requestedPlanet.postValue(it)
//                }
//            } catch (ex: Exception) {
//                println(ex.localizedMessage)
//            }
//        }

//        _requestedSpecies.value = Result.Loading
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                detailsUseCase.getAllSpecies(speciesPath).collect {
//                    _requestedSpecies.postValue(it)
//                }
//            } catch (ex: Exception) {
//                println(ex.localizedMessage)
//            }
//        }

//        val response = filmsUseCase.getAllFilms()
//
//        _requestedFilms.update {
//            it.copy(isLoading = true)
//        }
//        viewModelScope.launch {
//            response.catch { ex ->
//                _requestedFilms.update { _errorState ->
//                    _errorState.copy(
//                        userMessages = _errorState.userMessages
//                                + Message(
//                            UUID.randomUUID().mostSignificantBits,
//                            ex.message ?: "Error has occurred"
//                        ), isLoading = false
//                    )
//                }
//
//            }.collect {
//                it.onFailure {
//                    _requestedFilms.update { _errorState ->
//                        _errorState.copy(
//                            userMessages = _errorState.userMessages
//                                    + Message(
//                                UUID.randomUUID().mostSignificantBits,
//                                it.message ?: "Error has occurred"
//                            ), isLoading = false
//                        )
//                    }
//                }
//                it.onSuccess {
//                    _requestedFilms.update { _success ->
//                        _success.copy(it, isLoading = false)
//                    }
//                }
//            }
//        }
//    }
//
//    fun updateUserMessage(id: Long) {
//        _requestedFilms.update { currentUiState ->
//            val messages = currentUiState.userMessages.filterNot { it.id == id }
//            currentUiState.copy(userMessages = messages)
//        }
//    }


