package com.loveuba.starwarsapplication.data.repository

import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.data.wrapper.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val starwarsRepository : IStarwarsRepository
) {

    suspend fun searchByCharacterName(query: String): Flow<Result<List<CharacterData>>> {
        return starwarsRepository.fetchSearchCharacters(query)
    }

}