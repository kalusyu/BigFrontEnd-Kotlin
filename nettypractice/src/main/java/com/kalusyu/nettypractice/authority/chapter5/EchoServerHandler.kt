package com.kalusyu.nettypractice.authority.chapter5

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/29 23:32
 *
 **/
class EchoServerHandler : SimpleChannelInboundHandler<Any>() {

    var counter = 0
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {

//        var body = msg as String
//        println("This is :${++counter} times receive client: ${body}")
//
//        body += "\$_"
//        ctx?.writeAndFlush(Unpooled.copiedBuffer(body.toByteArray()))

        println("Receive client: [ ${msg as String} ]")
    }
}
