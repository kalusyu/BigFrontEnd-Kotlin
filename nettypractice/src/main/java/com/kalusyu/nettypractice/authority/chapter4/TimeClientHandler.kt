package com.kalusyu.nettypractice.authority.chapter4

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.nio.charset.Charset


const val CMD = "QUERY TIME ORDER"

const val PORT = 20003

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/29 12:14
 *
 **/
class TimeClientHandler : SimpleChannelInboundHandler<Any>() {


    var counter: Int = 0
    var req: ByteArray =
        CMD.toByteArray(Charset.defaultCharset()) + System.getProperty("line.separator")!!
            .toByteArray()


    override fun channelActive(ctx: ChannelHandlerContext?) {
        var message: ByteBuf
        for (i in 0..100) {
            message = Unpooled.buffer(req.size)
            message.writeBytes(req)
            ctx?.writeAndFlush(message)
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {

//        val buf = msg as ByteBuf
//        val req = ByteArray(buf.readableBytes())
//        buf.readBytes(req)
//        val body = String(req, Charset.defaultCharset())
        val body = msg as String
        println("client Now is: $body; the counter is ${++counter}")

    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}