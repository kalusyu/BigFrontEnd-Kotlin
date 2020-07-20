package com.kalusyu.patterns.behavior

/**
 * desc:定义一个操作中的算法的骨架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
 *
 * @author biaowen.yu
 * @date 2020/7/20 19:25
 *
 **/

abstract class Template{
    abstract fun getCount(name:String):Int

    fun calculate(){
        val count = getCount("ab")
        val sum = count+2
        println(sum)
    }
}

class TemplateMethod :Template(){
    override fun getCount(name: String): Int {
        return 120
    }
}