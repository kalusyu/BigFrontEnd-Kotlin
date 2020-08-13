package com.kalusyu.hiltpractice

import org.junit.Test

import org.junit.Assert.*
import javax.inject.Inject

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
    class A @Inject constructor() {
        fun say(){
            println("I'am A")
        }
    }

    class B{
        @Inject
        lateinit var a:A

    }


    @Test
    fun testInject(){
        B().a.say()
    }
}