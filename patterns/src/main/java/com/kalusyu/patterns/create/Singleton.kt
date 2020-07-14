package com.kalusyu.patterns.create

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/14 9:10
 *
 **/
object PrintDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print(): PrintDriver = apply { println("Printing with object: $this") }
}

