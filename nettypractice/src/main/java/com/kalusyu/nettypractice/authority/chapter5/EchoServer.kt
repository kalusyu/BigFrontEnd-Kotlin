package com.kalusyu.nettypractice.authority.chapter5

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import kotlin.Exception

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/29 11:33
 *
 **/
const val PORT = 20004

class EchoServer {
    @Throws(Exception::class)
    fun bind(port: Int) {

        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(ChildChannelHandler())

            val future = b.bind(port).sync()
            future.channel().closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }

    }

    inner class ChildChannelHandler : ChannelInitializer<SocketChannel>() {

        @Throws(Exception::class)
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.run {
                val byteBuf = Unpooled.copiedBuffer("\$_".toByteArray())
                addLast(DelimiterBasedFrameDecoder(1024, byteBuf))
                addLast(StringDecoder())
                addLast(EchoServerHandler())
            }
        }
    }


}

fun main() {
    EchoServer().bind(PORT)

}



