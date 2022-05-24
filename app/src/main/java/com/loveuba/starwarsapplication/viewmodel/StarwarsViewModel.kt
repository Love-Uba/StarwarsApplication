package com.loveuba.starwarsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.data.repository.SearchUseCase
import com.loveuba.starwarsapplication.data.wrapper.Result
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

    private var _requestedCharacter: MutableStateFlow<Result<List<CharacterData>>> =
        MutableStateFlow(Result.NotLoading(null))
    val getSearchResult: StateFlow<Result<List<CharacterData>>> get() = _requestedCharacter

    fun actionSearch(query: String) = viewModelScope.launch {
        try {
            _requestedCharacter.value = Result.Loading()
            searchUseCase.searchByCharacterName(query).collect {
                _requestedCharacter.value = it
            }
        } catch (e: Exception) {
            _requestedCharacter.value = Result.Error(e.localizedMessage ?: "Error fetching data.")
        }
    }
}