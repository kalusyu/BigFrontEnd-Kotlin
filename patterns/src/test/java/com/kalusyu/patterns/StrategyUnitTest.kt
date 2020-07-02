package com.kalusyu.patterns

import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/2 11:39
 *
 **/
class StrategyUnitTest {

    @Test
    fun testStrategy() {
        val testStr = "Hello World!"

        var printer = Printer(lowerCaseStringFormatter)
        printer.printString(testStr)

        printer = Printer(upperCaseStringFormatter)
        printer.printString(testStr)

        printer = Printer { "Prefix:$it" }
        printer.printString(testStr)
    }
}