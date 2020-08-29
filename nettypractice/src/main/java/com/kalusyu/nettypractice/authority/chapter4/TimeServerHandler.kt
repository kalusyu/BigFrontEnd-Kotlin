package com.kalusyu.nettypractice.authority.chapter4

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerAdapter
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.nio.charset.Charset
import java.util.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/29 11:42
 *
 **/
class TimeServerHandler : SimpleChannelInboundHandler<Any>() {
    var counter: Int = 0

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
//        val buf = msg as ByteBuf
//        val req = ByteArray(buf.readableBytes())
//        buf.readBytes(req)
//        val body = String(req, Charset.defaultCharset()).substring(0, req.size - System.getProperty("line.separator")!!.length)
        val body = msg as String
        println("body=${body} ; the counter is : ${++counter}")
        var currentTime =
            if (CMD.equals(body, true)) Date(System.currentTimeMillis()).toString() else "BAD ORDER"

        currentTime += System.getProperty("line.separator")
        val resp = Unpooled.copiedBuffer(currentTime.toByteArray(Charset.defaultCharset()))
        ctx?.write(resp)

    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}