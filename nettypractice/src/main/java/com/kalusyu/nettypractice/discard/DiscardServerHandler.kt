package com.kalusyu.nettypractice.discard

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.ReferenceCountUtil





/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 9:36
 *
 **/
class DiscardServerHandler :ChannelInboundHandlerAdapter() {

    /**
     * Please keep in mind that it is the handler's responsibility to release any reference-counted object passed to the handler
     * 所有的引用计数都是由handler释放的
     */
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
//        val `in` = msg as ByteBuf
//        try {
//            while (`in`.isReadable) { // (1)
//                print(`in`.readByte().toChar())
//                System.out.flush()
//            }
//        } finally {
//            ReferenceCountUtil.release(msg) // (2)
//        }
        ctx?.write(msg)
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace();
        ctx?.close();
    }
}