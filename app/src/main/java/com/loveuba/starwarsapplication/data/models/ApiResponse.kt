package com.loveuba.starwarsapplication.data.models

import com.google.gson.annotations.SerializedName

open class ApiResponse<T> {

    @SerializedName("count")
    val count: Int? = null

    @SerializedName("next")
    val next: Any? = null

    @SerializedName("previous")
    val previous: Any? = null

    fun hasData(): Boolean {
        return responseData != null
    }

    @SerializedName("results")
    open var responseData: T? = null

    fun getData(): T? {
        return responseData
    }
}