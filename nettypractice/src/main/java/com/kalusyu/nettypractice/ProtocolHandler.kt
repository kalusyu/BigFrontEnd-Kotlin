package com.kalusyu.nettypractice

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 13:43
 *
 **/
class ProtocolHandler:SimpleChannelInboundHandler<MyProtocol>() {
    override fun channelActive(ctx: ChannelHandlerContext?) {
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: MyProtocol?) {
        val bytes = msg?.contentLength
//        println("bytes= $bytes")
    }
}