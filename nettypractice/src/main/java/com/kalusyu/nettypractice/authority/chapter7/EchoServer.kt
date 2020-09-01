package com.kalusyu.nettypractice.authority.chapter7

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender

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
                addLast("frameDecoder", LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                addLast("msgpack decoder", MsgpackDecoder())
                addLast("frameEncoder", LengthFieldPrepender(2))
                addLast("msgpack encoder", MsgpackEncoder())
                addLast(EchoServerHandler())
            }
        }
    }


}

fun main() {
    EchoServer().bind(PORT)

}



