package com.loveuba.starwarsapplication.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val characters: List<Character>
)