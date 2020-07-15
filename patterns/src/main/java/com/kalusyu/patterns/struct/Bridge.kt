package com.kalusyu.patterns.struct

/**
 * desc:将抽象部分与它的实现部分分离，使它们都可以独立地变化。
 * 有两个维度的变化的时候使用
 * 定义一个抽象类，定义其内部子类的实现接口
 *
 * @author biaowen.yu
 * @date 2020/7/15 16:27
 * 人才和公司是两个不同维度的发展，公司的发展和人才的发展可以同时发生不同的变化
 **/
abstract class ACompany {
    var rencai: Rencai? = null
    fun doExtend() {
        rencai?.doSomething()
    }
}

abstract class Rencai {
    fun doSomething() {}
}