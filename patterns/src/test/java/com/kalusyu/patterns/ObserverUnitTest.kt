package com.kalusyu.patterns

import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/2 10:09
 *
 **/
class ObserverUnitTest {

    @Test
    fun delegateTest() {
        val textView = TextView()
        textView.name = "Hello JUnit"
        println(textView.name)
    }
}