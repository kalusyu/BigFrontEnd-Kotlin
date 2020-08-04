package com.kalusyu.nettypractice

import androidx.core.util.Pools
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.CharsetUtil
import java.nio.charset.Charset

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 9:14
 *
 **/
class ClientHandler : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
//        super.channelActive(ctx)
        println("channelActive")
//        ctx?.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
    }

    override fun channelRegistered(ctx: ChannelHandlerContext?) {
//        super.channelRegistered(ctx)
        println("channelRegistered")
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
//        super.channelReadComplete(ctx)
//        println("channelReadComplete")
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        msg as ByteBuf
        val str = String(msg.array(), Charset.forName("utf-8"))
        println("str=$str")
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
    }
}