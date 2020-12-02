package com.kalusyu.patterns.struct.bridge

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/2 14:45
 *
 **/
class KeepCount:IKeepValidAction {

    override fun doCalculateValidAction() {
        println("计次")
    }
}