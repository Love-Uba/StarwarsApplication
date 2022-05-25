package com.loveuba.starwarsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loveuba.starwarsapplication.data.models.CharacterData

class SharedViewModel : ViewModel() {

    /**
     * Shares the selected data of the character to the detail screen
     * */

    private val _character = MutableLiveData<CharacterData>()

    val getCharacter: LiveData<CharacterData> = _character

    fun shareCharacter(character: CharacterData) {
        _character.value = character
    }
}