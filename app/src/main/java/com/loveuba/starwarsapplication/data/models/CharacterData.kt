package com.loveuba.starwarsapplication.data.models

import com.google.gson.annotations.SerializedName

//Models the data gotten from response of the character search request

data class CharacterData(
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("species")
    val species: List<String>,
    @SerializedName("starships")
    val starships: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("vehicles")
    val vehicles: List<String>
)