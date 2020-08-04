package com.kalusyu.nettypractice

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.nio.charset.Charset


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 11:59
 *
 **/
class MyDecoder : ByteToMessageDecoder() {
    @Throws(Exception::class)
    override fun decode(
        channelHandlerContext: ChannelHandlerContext?,
        byteBuf: ByteBuf,
        list: MutableList<Any?>
    ) {
        val contentLength = byteBuf.readBytes(4).readInt()
        val content = ByteArray(contentLength)
        byteBuf.readBytes(content)
        val str = String(content, Charset.forName("utf-8"))
        println("contentLength=$contentLength , content=$str")
        val protocol = MyProtocol(1, contentLength, content)
        list.add(protocol)
    }
}