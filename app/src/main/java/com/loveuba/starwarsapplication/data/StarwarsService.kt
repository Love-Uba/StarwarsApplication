package com.loveuba.starwarsapplication.data

import com.loveuba.starwarsapplication.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarwarsService {

    @GET("people/")
    suspend fun searchCharacters(@Query("search")characterName: String) : Response<CharacterResponse>

    @GET("{planet}")
    suspend fun getPlanet(@Path("planet")homePath: String) : Response<PlanetData>

    @GET("{species}")
    suspend fun getSpecies(@Path("species")speciesPath: String): Response<SpeciesData>

    @GET("films/")
    suspend fun getFilms(): Response<FilmsResponse>

}