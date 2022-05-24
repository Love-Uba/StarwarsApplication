package com.loveuba.starwarsapplication.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilFunctionTest {

    @Test
    fun `measurement in centimeter value equals feet equivalent returns true`() {
        val result = Util.centimeterToFeet(
            "188"
        )
        assertThat(result).isEqualTo("6' 3\"")
    }

    @Test
    fun `add counter suffix to convert zero count in the provided amount to letter equivalent`() {
        val result = Util.addCountSuffix(
            2000000
        )
        assertThat(result).isEqualTo("2 M")
    }
}