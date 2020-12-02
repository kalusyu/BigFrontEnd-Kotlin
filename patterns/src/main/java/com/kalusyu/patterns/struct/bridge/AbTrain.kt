package com.kalusyu.patterns.struct.bridge

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/2 14:22
 *
 **/
abstract class AbTrain {

    var keepValidAction: IKeepValidAction? = null

    abstract fun calculateValidAction()

}