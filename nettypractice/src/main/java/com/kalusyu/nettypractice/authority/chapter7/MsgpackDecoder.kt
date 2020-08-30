package com.kalusyu.nettypractice.authority.chapter7

import com.daveanthonythomas.moshipack.MoshiPack
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import io.netty.handler.codec.MessageToMessageDecoder
import java.util.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/30 15:55
 *
 **/
class MsgpackDecoder : MessageToMessageDecoder<ByteBuf>() {

    override fun decode(ctx: ChannelHandlerContext?, msg: ByteBuf?, out: MutableList<Any>?) {
        msg?.let {
            val byteArray = ByteArray(msg.readableBytes())
            msg.getBytes(msg.readerIndex(), byteArray, 0, msg.readableBytes())
            val msgpack = MoshiPack()
            out?.add(msgpack.unpack(byteArray))
        }
    }
}