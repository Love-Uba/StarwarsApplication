package com.loveuba.starwarsapplication.data.repository

import com.loveuba.starwarsapplication.data.StarwarsService
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.data.models.SpeciesData
import com.loveuba.starwarsapplication.data.wrapper.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val apiService: StarwarsService
) {

    fun getCharacterPlanet(planetPath: String): Flow<Result<PlanetData>> =
        flow {
            try {
                val response = apiService.getPlanet(planetPath)
                if (response.body() != null) {
                    emit(Result.Success(response.body()!!))
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

    fun getAllSpecies(speciesPath: String): Flow<Result<SpeciesData>> =
        flow {
            try {
                val response = apiService.getSpecies(speciesPath)
                if (response.body() != null) {
                    emit(Result.Success(response.body()!!))
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
}