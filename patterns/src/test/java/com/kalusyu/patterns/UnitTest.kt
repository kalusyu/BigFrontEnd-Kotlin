package com.kalusyu.patterns

import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/21 17:49
 *
 **/
class UnitTest {

    @Test
    fun testArray(){
        val distance = FloatArray(1)
        distance[0] = 1.1f
        println(distance.size)
    }
}