package com.loveuba.starwarsapplication.utils

import com.loveuba.starwarsapplication.utils.UtilConstants.BASE_URL


fun String.toEndpoint() : String{
    val url = this
    return url.substring(BASE_URL.lastIndex+1)
}