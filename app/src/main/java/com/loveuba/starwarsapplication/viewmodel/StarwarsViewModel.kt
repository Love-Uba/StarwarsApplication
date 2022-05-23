package com.loveuba.starwarsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loveuba.starwarsapplication.data.repository.SearchUseCase
import com.loveuba.starwarsapplication.ui.SearchUIState
import com.loveuba.starwarsapplication.utils.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StarwarsViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _requestedCharacter = MutableStateFlow(SearchUIState())

    val getSearchResult: StateFlow<SearchUIState> = _requestedCharacter.asStateFlow()

    fun actionSearch(query: String) {
        val response = searchUseCase.searchByCharacterName(query)
        _requestedCharacter.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            response.catch { ex ->
                _requestedCharacter.update { _errorState ->
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
                    _requestedCharacter.update { _errorState ->
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
                    _requestedCharacter.update { _success ->
                        _success.copy(characters = it, isLoading = false)
                    }
                }
            }
        }
    }

    fun updateUserMessage(id: Long) {
        _requestedCharacter.update { currentUiState ->
            val messages = currentUiState.userMessages.filterNot { it.id == id }
            currentUiState.copy(userMessages = messages)
        }
    }
}
