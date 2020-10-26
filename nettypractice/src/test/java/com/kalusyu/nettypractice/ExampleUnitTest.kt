package com.kalusyu.nettypractice

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testAnosFunc() {
        val len = stringLengthFunc("21234123")
        assert(len == 8)

        val hilen = stringMapper("Android"){
            it.length
        }
        assert(hilen == 7)
    }

    /**
     * 匿名函数
     */
    val stringLengthFunc: (String) -> Int = { input ->
        input.length
    }

    fun stringMapper(str: String, mapper: (String) -> Int): Int {
        return mapper(str)
    }
}