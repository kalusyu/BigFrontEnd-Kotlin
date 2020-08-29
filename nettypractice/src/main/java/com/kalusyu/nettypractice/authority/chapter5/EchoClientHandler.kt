package com.kalusyu.nettypractice.authority.chapter5

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class EchoClientHandler : SimpleChannelInboundHandler<Any>() {

    companion object {
        const val EHCO_REQ = "Hi, kalusyu. welcome to Netty.\$_"
    }

    var counter = 0

    override fun channelActive(ctx: ChannelHandlerContext?) {
        for (i in 1..10) {
            ctx?.writeAndFlush(Unpooled.copiedBuffer(EHCO_REQ.toByteArray()))
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        println("This is  ${++counter} times receive server ${msg as String}")
    }
}
