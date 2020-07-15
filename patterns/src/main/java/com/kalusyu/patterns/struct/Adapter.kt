package com.kalusyu.patterns.struct

import java.util.concurrent.Callable

/**
 * desc: 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 改写原来的接口可以，但是需要改掉其他任何地方使用到他的地方容易出问题
 * 只是实现的接口不一样而已，实际功能是一样的
 *
 * 1.实现目标接口，这里是Runnable；
 * 2.内部持有一个待转换接口的引用，这里是通过字段持有Callable接口；
 * 3.在目标接口的实现方法内部，调用待转换接口的方法。
 *
 * 例子：　
 * InputStreamReader即为适配器
 * InputStream input = Files.newInputStream(Paths.get("/path/to/file"));
 * Reader reader = new InputStreamReader(input, "UTF-8");
 *　readText(reader);
 *
 * @author biaowen.yu
 * @date 2020/7/14 11:02
 *
 **/

class Task(private val long: Long) : Callable<Long> {
    override fun call(): Long {
        var r = 0L
        for (n in (1..long + 1)) {
            r += n
        }
        return r
    }
}


class RunnableAdapter(private val callable: Callable<*>) : Runnable {
    override fun run() {
        try {
            callable.call()
        } catch (e: Exception) {

        }
    }
}

// Another case
interface EnglishPlugin {
    fun largeHole()
}

interface ChinesePlugin {
    fun smallHole()
}

class ChineseToEnglishPluginAdapter(private val chinesePlugin: ChinesePlugin) : EnglishPlugin {
    override fun largeHole() {
        println("ChineseToEnglishPluginAdapter convert")
        chinesePlugin.smallHole()
    }
}

class ChinesePluginProduct :ChinesePlugin{
    override fun smallHole() {
        println("ChinesePluginProduct Bull Brand")
    }
}