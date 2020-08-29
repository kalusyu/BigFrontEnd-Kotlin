package com.kalusyu.nettypractice.authority.chapter3

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

    lateinit var firstMessage: ByteBuf

    init {
        val req = CMD.toByteArray(Charset.defaultCharset())
        firstMessage = Unpooled.buffer(req.size)
        firstMessage.writeBytes(req)
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        ctx?.writeAndFlush(firstMessage)
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {

        val buf = msg as ByteBuf
        val req = ByteArray(buf.readableBytes())
        buf.readBytes(req)
        val body = String(req, Charset.defaultCharset())
        println("client Now is: $body")

    }
}