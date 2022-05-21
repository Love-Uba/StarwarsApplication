package com.loveuba.starwarsapplication.data

import com.loveuba.starwarsapplication.data.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarsService {

    @GET("people/")
    suspend fun searchCharacters(@Query("search")characterName: String) : Response<CharacterResponse>

}