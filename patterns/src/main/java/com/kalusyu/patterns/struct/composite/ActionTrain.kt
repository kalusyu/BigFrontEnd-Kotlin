package com.kalusyu.patterns.struct.composite

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/1 16:11
 *
 **/
interface ActionTrain {

    /**
     *
     */
    fun doActionTrain()

    /**
     * @param actionTrain
     */
    fun addAction(actionTrain: ActionTrain){}

    /**
     * @param actionTrain
     */
    fun removeAction(actionTrain: ActionTrain){}
}