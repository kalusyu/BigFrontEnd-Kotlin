package com.kalusyu.patterns

import com.kalusyu.patterns.behavior.PrintTextChange
import com.kalusyu.patterns.behavior.TextView
import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun observerTest() {
        val listener = PrintTextChange()
        val textView = TextView().apply {
            listeners.add(listener)
        }
        with(textView){
            text = "A"
            text = "B"
        }
        assertThat(listener.text == "Text is changed: A -> B")

    }
}