package com.kalusyu.patterns.struct.bridge

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/2 14:28
 *
 **/
open class FreeTrain:AbTrain() {


    override fun calculateValidAction() {
        print("徒手")
        keepValidAction?.doCalculateValidAction()
    }
}