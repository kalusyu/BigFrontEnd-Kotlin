package com.kalusyu.patterns.struct.bridge

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/2 14:45
 *
 **/
class KeepCountTime:IKeepValidAction {

    override fun doCalculateValidAction() {
        println("计次且计时")
    }
}