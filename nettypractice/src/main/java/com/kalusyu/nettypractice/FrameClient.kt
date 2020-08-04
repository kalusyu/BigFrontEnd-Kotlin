package com.kalusyu.nettypractice

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.ReferenceCountUtil
import java.lang.StringBuilder


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 16:36
 *
 **/
class FrameClient:SimpleChannelInboundHandler<ByteBuf>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ByteBuf?) {
        var str = StringBuilder()
        val `in` = msg as ByteBuf
        try {
            while (`in`.isReadable) { // (1)
                str.append(`in`.readByte() as Char)
            }
            println("str=$str")
        } finally {
            ReferenceCountUtil.release(msg) // (2)
        }
    }
}