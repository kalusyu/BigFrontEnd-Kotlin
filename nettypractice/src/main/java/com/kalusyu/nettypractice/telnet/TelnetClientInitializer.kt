package com.kalusyu.nettypractice.telnet

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.ssl.SslContext

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 20:26
 *
 **/
class TelnetClientInitializer(private val sslCtx:SslContext?) : ChannelInitializer<SocketChannel>() {
    companion object {
        val DECODER = StringDecoder()
        val ENCODER = StringEncoder()
        val CLIENT_HANDLER = TelnetClientHandler()
    }
    override fun initChannel(ch: SocketChannel?) {
        val pipline = ch?.pipeline()
        sslCtx?.let {
            pipline?.addLast(it.newHandler(ch.alloc(),HOST, PORT))
        }

        pipline?.let {
            it.addLast(DelimiterBasedFrameDecoder(8192, *Delimiters.lineDelimiter()))
            it.addLast(DECODER)
            it.addLast(ENCODER)
            it.addLast(CLIENT_HANDLER)
        }
    }
}