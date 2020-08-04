package com.kalusyu.nettypractice

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 14:24
 *
 **/
class MyEnCoder : MessageToByteEncoder<MyProtocol>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: MyProtocol?, out: ByteBuf?) {
        out?.run {
            msg?.let {
                val body: ByteArray = it.content
                val bb = ByteBuffer.allocate(4 + body.size)
                bb.order(ByteOrder.BIG_ENDIAN)
                bb.putInt(body.size)
                bb.put(body)
                writeBytes(bb)
            }
        }
    }
}