package com.kalusyu.patterns

import com.kalusyu.patterns.behavior.AuthorizationPresenter
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/20 19:40
 *
 **/
class StateUnitTest {

    @Test
    fun testState() {
        val authorizationPresenter = AuthorizationPresenter()
        authorizationPresenter.loginUser("kkk")
        println(authorizationPresenter)

        authorizationPresenter.logout()
        println(authorizationPresenter)
    }
}