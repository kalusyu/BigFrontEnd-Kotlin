package com.kalusyu.nettypractice.authority.chapter7

import com.daveanthonythomas.moshipack.MoshiPack
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/30 15:52
 *
 **/
class MsgpackEncoder : MessageToByteEncoder<Object>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: Object?, out: ByteBuf?) {
        val pack = MoshiPack()
        val outBuffer = pack.pack(msg)
        out?.writeBytes(outBuffer.readByteArray())
    }
}