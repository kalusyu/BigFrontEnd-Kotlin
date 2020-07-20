package com.kalusyu.patterns

import com.kalusyu.patterns.behavior.ChatMediator
import com.kalusyu.patterns.behavior.ChatUser
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/20 19:20
 *
 **/
class MediatorUnitTest {

    @Test
    fun testMediator() {
        val mediator = ChatMediator()
        val john = ChatUser(mediator, "john")

        mediator.addUser(ChatUser(mediator,"Alice"))
            .addUser(ChatUser(mediator, "Bob"))
            .addUser(john)

        john.send("Hi everyone")
    }
}