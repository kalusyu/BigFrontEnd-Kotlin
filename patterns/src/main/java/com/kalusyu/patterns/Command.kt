package com.kalusyu.patterns

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/8 9:19
 *
 **/
interface Command {
    fun execute()
}

interface IOperator {
    fun copy()
    fun delete()
}

class CopyCommand(private val operator: IOperator) : Command {

    override fun execute() {
        operator.copy()
    }
}

class DeleteCommand(private val operator: IOperator) : Command {
    override fun execute() {
        operator.delete()
    }
}

class CommandProcess {
    private val queue = mutableListOf<Command>()

    fun addToQueue(command: Command): CommandProcess =
        apply {
            queue.add(command)
        }

    fun processCommands(): CommandProcess =
        apply {
            queue.forEach {
                it.execute()

            }
            queue.clear()
        }

}

class Operator :IOperator{
    override fun copy() {
        println("execute copy")
    }

    override fun delete() {
        println("execute delete")
    }
}

