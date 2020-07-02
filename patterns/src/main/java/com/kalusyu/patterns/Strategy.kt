package com.kalusyu.patterns

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/2 11:36
 *
 **/

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(string: String) {
        println(stringFormatterStrategy(string))
    }
}

val lowerCaseStringFormatter: (String) -> String = { it.toLowerCase() }
val upperCaseStringFormatter = { it: String -> it.toUpperCase() }

