package com.kalusyu.patterns.behavior

import java.math.BigDecimal

/**
 * desc:使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。
 *
 * @author biaowen.yu
 * @date 2020/7/16 15:21
 *
 **/
data class Request(val name: String, val amount: BigDecimal)

interface Handler {
    fun process(request: Request): Boolean
}

class ManagerHandler:Handler{
    override fun process(request: Request): Boolean {
        TODO("Not yet implemented")
    }
}

class DirectorHandler:Handler{
    override fun process(request: Request): Boolean {
        TODO("Not yet implemented")
    }
}

class CEOHandler:Handler{
    override fun process(request: Request): Boolean {
        TODO("Not yet implemented")
    }
}

class HandlerChain{
    private val handlerList = mutableListOf<Handler>()

    fun addHandler(handler: Handler) {
        handlerList.add(handler)
    }

    fun process(request: Request):Boolean {
        for (handler in handlerList){
            val b = handler.process(request)
            b?.run {
                return b
            }
        }
        return false
    }
}