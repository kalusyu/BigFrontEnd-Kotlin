package com.kalusyu.patterns

/**
 * desc:将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化，对请求排队或记录请求日志，以及支持可撤销的操作。
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

