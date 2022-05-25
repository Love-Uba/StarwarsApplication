package com.loveuba.starwarsapplication.data.models

import com.google.gson.annotations.SerializedName

//Models the response for fetching all films

data class FilmsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val films: List<FilmData>
)