package com.kalusyu.patterns.behavior

/**
 * desc:定义一系列的算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。
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

