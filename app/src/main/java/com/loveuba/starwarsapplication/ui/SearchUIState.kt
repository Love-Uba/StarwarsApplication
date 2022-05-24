package com.loveuba.starwarsapplication.ui

import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.utils.Message

data class SearchUIState(
    val characters: List<CharacterData> = emptyList(),
    val userMessages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)

data class FilmUIState(
    val films: List<FilmData> = emptyList(),
    val userMessages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
