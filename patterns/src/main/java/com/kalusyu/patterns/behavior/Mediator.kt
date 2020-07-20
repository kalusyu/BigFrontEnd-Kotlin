package com.kalusyu.patterns.behavior

/**
 * desc:用一个中介对象来封装一系列的对象交互。中介者使各个对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *
 * 对象与对象之间存在大量的关联关系，这样势必会导致系统的结构变得很复杂，同时若一个对象发生改变，我们也需要跟踪与之相关联的对象，同时做出相应的处理。
 * 何时使用：多个类相互耦合，形成了网状结构。
 * 如何解决：将上述网状结构分离为星型结构。
 * @author biaowen.yu
 * @date 2020/7/16 15:51
 *
 **/

class ChatUser(private val mediator: ChatMediator, private val name: String) {

    fun send(msg: String) {
        println("$name: Sending Message= $msg")
        mediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        println("$name: Message received: $msg")
    }

}

class ChatMediator {

    private val users = mutableListOf<ChatUser>()

    fun sendMessage(msg: String, user: ChatUser) {

        users.filter { it != user }
            .forEach {
                it.receive(msg)
            }
    }

    fun addUser(user: ChatUser): ChatMediator = apply { users.add(user) }
}



