package com.kalusyu.patterns.struct.composite

/**
 * desc: 训练中使用组合模式，一开始画出了树形结构，以为组合模式能够解决这个问题，
 * 但是发现他们没有太多共同的特性，而是各自从不同的地方发展，并且树形结构也没有直接的联系，后面尝试使用
 * 桥接模式，桥接模式就是使用多个方向共同变化的情况，正好符合这个情况
 *
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