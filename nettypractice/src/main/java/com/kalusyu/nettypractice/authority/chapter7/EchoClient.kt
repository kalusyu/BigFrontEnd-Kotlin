package com.kalusyu.nettypractice.authority.chapter7

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/29 12:07
 *
 **/
class EchoClient {

    @Throws(Exception::class)
    fun connect(port: Int, host: String) {
        val group = NioEventLoopGroup()
        try {
            val b = Bootstrap()
            b.group(group).channel(NioSocketChannel::class.java)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()?.run {
                            addLast("frameDecoder", LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                            addLast("msgpack decoder", MsgpackDecoder())
                            addLast("frameEncoder", LengthFieldPrepender(2))
                            addLast("msgpack encoder", MsgpackEncoder())
                            addLast(EchoClientHandler())
                        }

                    }
                })

            val future = b.connect(host, port).sync()

            future.channel().closeFuture().sync()
        } finally {
            group.shutdownGracefully()
        }
    }
}

fun main() {
    EchoClient().connect(PORT, "127.0.0.1")
}

