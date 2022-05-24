package com.loveuba.starwarsapplication.common.data.api

import com.loveuba.starwarsapplication.BuildConfig
import com.loveuba.starwarsapplication.data.StarwarsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StarwarsApiTest {

    private val mockCharacterResponse = "{\n" +
            "   \"count\": 1,\n" +
            "   \"next\": null,\n" +
            "   \"previous\": null,\n" +
            "   \"results\": [\n" +
            "   {\n" +
            "       \"name\": \"Yoda\",\n" +
            "       \"height\": \"66\",\n" +
            "       \"mass\": \"17\",\n" +
            "       \"hair_color\": \"white\",\n" +
            "       \"skin_color\": \"green\",\n" +
            "       \"eye_color\": \"brown\",\n" +
            "       \"birth_year\": \"896BBY\",\n" +
            "       \"gender\": \"male\",\n" +
            "       \"homeworld\": \"https://swapi.dev/api/planets/28/\",\n" +
            "       \"films\": [\n" +
            "           \"https://swapi.dev/api/films/2/\",\n" +
            "           \"https://swapi.dev/api/films/3/\",\n" +
            "           \"https://swapi.dev/api/films/4/\",\n" +
            "           \"https://swapi.dev/api/films/5/\",\n" +
            "           \"https://swapi.dev/api/films/6/\",\n" +
            "           ],\n" +
            "       \"species\": [\n" +
            "           \"https://swapi.dev/api/species/6/\"\n" +
            "           ],\n" +
            "       \"vehicles\": [],\n" +
            "       \"starships\": [],\n" +
            "       \"created\": \"2014-12-15T12:26:01.042000Z\",\n" +
            "       \"edited\": \"2014-12-20T21:17:50.345000Z\",\n" +
            "       \"url\": \"https://swapi.dev/api/people/20/\",\n" +
            "        }\n" +
            "    ]\n" +
            "        }".trimIndent()

    private lateinit var mockWebServer: MockWebServer

    private fun getApiService(): StarwarsService {
        val baseUrl = mockWebServer.url(BuildConfig.BASE_URL)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(StarwarsService::class.java)
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(mockCharacterResponse))
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun canResponseObjectBeParsed() = runTest {

        val service = getApiService()
        val query = ("Yoda")

        val response = service.searchCharacters(query)

        Assert.assertNotNull(response)
        response.body()?.characters?.isNotEmpty()?.let { Assert.assertTrue(it) }
    }
}