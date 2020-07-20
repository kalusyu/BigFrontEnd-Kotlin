package com.kalusyu.patterns.behavior

/**
 * desc:允许一个对象在其内部状态改变时改变它的行为。对象看起来似乎修改了它的类。
 *
 * 状态模式的关键设计思想在于状态切换
 *
 * @author biaowen.yu
 * @date 2020/7/20 19:28
 *
 **/

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val name: String) : AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userName: String
        get() = when (val state = this.state) {
            is Authorized -> state.name
            is Unauthorized -> "UnKnown"
        }

    fun loginUser(name: String) {
        state = Authorized(name)
    }

    fun logout() {
        state = Unauthorized
    }

    override fun toString() = "User '$userName' is logged in: $isAuthorized"
}