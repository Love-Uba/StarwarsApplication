package com.loveuba.starwarsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.data.models.SpeciesData
import com.loveuba.starwarsapplication.data.repository.DetailsUseCase
import com.loveuba.starwarsapplication.data.repository.FilmsUseCase
import com.loveuba.starwarsapplication.data.wrapper.Result
import com.loveuba.starwarsapplication.ui.FilmUIState
import com.loveuba.starwarsapplication.ui.SearchUIState
import com.loveuba.starwarsapplication.utils.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsUseCase,
    private val filmsUseCase: FilmsUseCase
) : ViewModel() {

    private val _requestedPlanet = MutableLiveData<Result<PlanetData>>()
    val getPlanetResult: LiveData<Result<PlanetData>> = _requestedPlanet


    private val _requestedSpecies = MutableLiveData<Result<SpeciesData>>()
    val getSpeciesResult: LiveData<Result<SpeciesData>> = _requestedSpecies


    private val _requestedFilms = MutableStateFlow(FilmUIState())
    val getFilmResult: StateFlow<FilmUIState> = _requestedFilms.asStateFlow()


    fun actionGetPlanet(planetPath: String) {
        _requestedPlanet.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                detailsUseCase.getCharacterPlanet(planetPath).collect {
                    _requestedPlanet.postValue(it)
                }
            } catch (ex: Exception) {
                println(ex.localizedMessage)
            }
        }
    }

    fun actionGetSpecies(speciesPath: String) {
        _requestedSpecies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                detailsUseCase.getAllSpecies(speciesPath).collect {
                    _requestedSpecies.postValue(it)
                }
            } catch (ex: Exception) {
                println(ex.localizedMessage)
            }
        }
    }

    fun actionGetFilms() {

        val response = filmsUseCase.getAllFilms()

        _requestedFilms.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            response.catch { ex ->
                _requestedFilms.update { _errorState ->
                    _errorState.copy(
                        userMessages = _errorState.userMessages
                                + Message(
                            UUID.randomUUID().mostSignificantBits,
                            ex.message ?: "Error has occurred"
                        ), isLoading = false
                    )
                }

            }.collect {
                it.onFailure {
                    _requestedFilms.update { _errorState ->
                        _errorState.copy(
                            userMessages = _errorState.userMessages
                                    + Message(
                                UUID.randomUUID().mostSignificantBits,
                                it.message ?: "Error has occurred"
                            ), isLoading = false
                        )
                    }
                }
                it.onSuccess {
                    _requestedFilms.update { _success ->
                        _success.copy(it, isLoading = false)
                    }
                }
            }
        }
    }

    fun updateUserMessage(id: Long) {
        _requestedFilms.update { currentUiState ->
            val messages = currentUiState.userMessages.filterNot { it.id == id }
            currentUiState.copy(userMessages = messages)
        }
    }
}

