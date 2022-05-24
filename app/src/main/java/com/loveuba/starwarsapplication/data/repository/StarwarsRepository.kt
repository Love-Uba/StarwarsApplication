package com.loveuba.starwarsapplication.data.repository

import com.loveuba.starwarsapplication.data.wrapper.Result
import com.loveuba.starwarsapplication.data.StarwarsService
import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.data.models.SpeciesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class StarwarsRepository @Inject constructor(private val apiService: StarwarsService) : IStarwarsRepository {

    override suspend fun fetchSearchCharacters(query: String): Flow<Result<List<CharacterData>>> =
        flow {
            try {
                val response = apiService.searchCharacters(query)
                if(response.body() != null) {
                    emit(Result.Success(response.body()!!.characters))
                }
            }catch (ex: Exception){
                emit(Result.Error(ex.localizedMessage ?: "Error getting searched character data!"))
            }
    }

    override suspend fun fetchCharacterPlanet(planetPath: String): Flow<Result<PlanetData>> =
        flow {
            try {
                val response = apiService.getPlanet(planetPath)
                if (response.body() != null) {
                    emit(Result.Success(response.body()!!))
                }
            } catch (ex: Exception) {
                emit(Result.Error(ex.localizedMessage ?: "Error getting character's Planet data!"))
            }
        }

    override suspend fun fetchCharacterSpecies(speciesPath: String): Flow<Result<SpeciesData>> =
        flow {
            try {
                val response = apiService.getSpecies(speciesPath)
                if (response.body() != null) {
                    emit(Result.Success(response.body()!!))
                }
            } catch (ex: Exception) {
                Result.Error(ex.localizedMessage!!, null)
                emit(Result.Error(ex.localizedMessage ?: "Error getting character's specie!"))
            }
        }

    override suspend fun fetchAllFilms(): Flow<Result<List<FilmData>>> =
        flow {
            try {
                val response = apiService.getFilms()
                if(response.body() != null) {
                    emit(Result.Success(response.body()!!.films))
                }
            }catch (ex: Exception){
                emit(Result.Error(ex.localizedMessage ?: "Error getting character's films!"))
            }
        }

}

interface IStarwarsRepository{
    suspend fun fetchSearchCharacters(query: String) : Flow<Result<List<CharacterData>>>
    suspend fun fetchCharacterPlanet(planetPath: String) : Flow<Result<PlanetData>>
    suspend fun fetchCharacterSpecies(speciesPath: String) : Flow<Result<SpeciesData>>
    suspend fun fetchAllFilms() : Flow<Result<List<FilmData>>>
}