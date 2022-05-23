package com.loveuba.starwarsapplication.utils

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.ln

object UtilConstants {
    const val BASE_URL = "https://swapi.dev/api/"
}

fun centimeterToFeet(centimeter: String): String {
    var feetPart = 0
    var inchesPart = 0
    if (!centimeter.isEmpty()) {
        val dCentimeter = java.lang.Double.valueOf(centimeter)
        feetPart = floor(dCentimeter / 2.54 / 12).toInt()
        inchesPart = ceil(dCentimeter / 2.54 - feetPart * 12).toInt()
    }
    return String.format("%d' %d''", feetPart, inchesPart)
}

fun addCountSuffix(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.0f %c",
        count / Math.pow(1000.0, exp.toDouble()),
        "KMBTPE"[exp - 1]
    )
}