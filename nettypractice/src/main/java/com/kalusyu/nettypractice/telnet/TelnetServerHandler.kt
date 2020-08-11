package com.kalusyu.nettypractice.telnet

import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.net.InetAddress
import java.util.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 19:29
 *
 **/
@ChannelHandler.Sharable
class TelnetServerHandler : SimpleChannelInboundHandler<String>() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        ctx?.run {
            write("Welcome to ${InetAddress.getLocalHost().hostName}!\r\n")
            write("It is ${Date()} now.\r\n")
            flush()
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: String?) {
        var close = false
        var response = ""
        if (msg.isNullOrEmpty()) {
            response = "Please type something.\r\n";
        } else if ("bye" == (msg.toLowerCase())) {
            response = "Have a good day!\r\n";
            close = true;
        } else {
            response = "Did you say '${msg}'?\r\n";
        }

        val future = ctx?.write(response)

        if (close) {
            future?.addListener(ChannelFutureListener.CLOSE)
        }
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}