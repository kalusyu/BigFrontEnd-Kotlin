package com.kalusyu.nettypractice.authority.chapter7

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class EchoClientHandler : SimpleChannelInboundHandler<Any>() {

    companion object {
    }


    override fun channelActive(ctx: ChannelHandlerContext?) {

        val infos = userInfo()
        infos.forEach {
            ctx?.write(it)
        }
        ctx?.flush()
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        println("Client receive the msgpack message: $msg")
    }

    private fun userInfo(): Array<UserInfo?> {
        val userInfos = arrayOfNulls<UserInfo>(100)
        for (i in 0..99) {
            val userInfo = UserInfo("ABCDEF----${i}", i)
            userInfos[i] = userInfo
        }
        return userInfos
    }


}

class UserInfo(val name: String, val age: Int)


