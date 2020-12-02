package com.kalusyu.patterns.struct.bridge

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/2 14:38
 *
 **/
class DeviceTrain : AbTrain() {


    override fun calculateValidAction() {
        print("设备")
        keepValidAction?.doCalculateValidAction()
    }
}

fun main() {
    val keepCount = KeepCount()
    val keepTime = KeepTime()
    val keepCountTime = KeepCountTime()

    var train: AbTrain = FreeTrain()
    train.keepValidAction = keepCount
    train.calculateValidAction()

    train.keepValidAction = keepTime
    train.calculateValidAction()

    train.keepValidAction = keepCountTime
    train.calculateValidAction()

    train = DeviceTrain()
    train.keepValidAction = keepCount
    train.calculateValidAction()

    train.keepValidAction = keepTime
    train.calculateValidAction()

    train.keepValidAction = keepCountTime
    train.calculateValidAction()


}