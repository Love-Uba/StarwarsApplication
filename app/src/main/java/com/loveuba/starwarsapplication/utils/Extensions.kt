package com.loveuba.starwarsapplication.utils

import com.loveuba.starwarsapplication.BuildConfig.BASE_URL

/**
 * Converts the url string the function is called upon to an endpoint to be added as a path for another request
 * For species and planet of character request
 * */

fun String.toEndpoint() : String{
    val url = this
    return url.substring(BASE_URL.lastIndex+1)
}