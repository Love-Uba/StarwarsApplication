package com.loveuba.starwarsapplication.data.repository

import android.util.Log
import com.loveuba.starwarsapplication.data.StarwarsService
import com.loveuba.starwarsapplication.data.models.CharacterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val apiService: StarwarsService
) {

    fun searchByCharacterName(query: String): Flow<Result<List<CharacterData>>> =
        flow {
            try {
                val response = apiService.searchCharacters(query)
                if (response.body() != null) {
                    emit(Result.success(response.body()!!.characters))
                }
            } catch (ex: Exception) {
                Log.d("ERRORTAG", "searchByCharacterName: ${ex.printStackTrace()}")
                ex.printStackTrace()
            }
        }
}