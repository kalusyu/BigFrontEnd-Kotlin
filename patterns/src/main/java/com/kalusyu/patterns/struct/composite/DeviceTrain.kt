package com.kalusyu.patterns.struct.composite

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/1 16:13
 *
 **/
class DeviceTrain : ActionTrain {

    private val actions = mutableListOf<ActionTrain>()

    override fun doActionTrain() {
        actions.forEach { action ->
            action.doActionTrain()
        }
    }

    override fun addAction(actionTrain: ActionTrain) {
        actions.add(actionTrain)
    }

    override fun removeAction(actionTrain: ActionTrain) {
        actions.remove(actionTrain)
    }
}