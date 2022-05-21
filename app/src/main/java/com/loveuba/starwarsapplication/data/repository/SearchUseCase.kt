package com.loveuba.starwarsapplication.data.repository

import android.util.Log
import com.loveuba.starwarsapplication.data.StarwarsService
import com.loveuba.starwarsapplication.data.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val apiService: StarwarsService
) {

    fun searchByCharacterName(query: String): Flow<Result<List<Character>>> =
        flow {
            try {
                val response = apiService.searchCharacters(query)
                if (response.body() != null) {
                    emit(Result.success(response.body()!!.characters))
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
}