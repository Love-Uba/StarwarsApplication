package com.loveuba.starwarsapplication.ui

import com.loveuba.starwarsapplication.data.models.Character
import com.loveuba.starwarsapplication.utils.Message

data class SearchUIState(
    val characters: List<Character> = emptyList(),
    val userMessages: List<Message> = emptyList(),
    val isNotLoading: Boolean = false,
    val isLoading: Boolean = false
)
