package com.kalusyu.nettypractice.telnet

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 20:27
 *
 **/
@ChannelHandler.Sharable
class TelnetClientHandler:SimpleChannelInboundHandler<String>() {


    override fun channelRead0(ctx: ChannelHandlerContext?, msg: String?) {
        System.err.println(msg)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}