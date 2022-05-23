package com.loveuba.starwarsapplication.data.repository

import com.loveuba.starwarsapplication.data.StarwarsService
import com.loveuba.starwarsapplication.data.models.FilmData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FilmsUseCase @Inject constructor(
    private val apiService: StarwarsService
) {
    fun getAllFilms() : Flow<Result<List<FilmData>>> =
        flow {
            try {
                val response = apiService.getFilms()
                if(response.body() != null) {
                    emit(Result.success(response.body()!!.films))
                }
            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }
}