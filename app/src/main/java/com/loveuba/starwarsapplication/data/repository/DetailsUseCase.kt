package com.loveuba.starwarsapplication.data.repository

import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.data.models.SpeciesData
import com.loveuba.starwarsapplication.data.wrapper.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val starwarsRepository: IStarwarsRepository
) {
    suspend fun getCharacterPlanet(planetPath: String): Flow<Result<PlanetData>> {
        return starwarsRepository.fetchCharacterPlanet(planetPath)
    }

    suspend fun getAllSpecies(speciePath: String): Flow<Result<SpeciesData>> {
        return starwarsRepository.fetchCharacterSpecies(speciePath)
    }

    suspend fun getAllFilms(): Flow<Result<List<FilmData>>> {
        return starwarsRepository.fetchAllFilms()
    }
}