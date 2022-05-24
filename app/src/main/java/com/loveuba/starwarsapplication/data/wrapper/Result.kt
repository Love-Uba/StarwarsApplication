package com.loveuba.starwarsapplication.data.wrapper

//sealed class Result<out T> {
//    data class Success<T>(val value: T) : Result<T>()
//    data class Error(val message: String) : Result<Nothing>()
//    object Loading : Result<Nothing>()
//    object NotLoading : Result<Nothing>()
//}

sealed class Result<T> (val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Result<T>(data)
    class Error<T>(message: String, data: T? = null): Result<T>(data ,message)
    class Loading<T>(data: T? = null): Result<T>(data)
    class NotLoading<T>(data: T? = null): Result<T>(data)
}
