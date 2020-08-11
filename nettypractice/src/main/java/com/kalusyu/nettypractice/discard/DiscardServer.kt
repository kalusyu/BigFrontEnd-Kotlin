package com.kalusyu.nettypractice.discard

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
 * @date 2020/8/4 9:42
 *
 **/
class DiscardServer(val port: Int) {


    @Throws(Exception::class)
    fun run() {
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap() // (2)
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java) // (3)
                .childHandler(object : ChannelInitializer<SocketChannel>() {
                    // (4)
                    @Throws(Exception::class)
                    override fun initChannel(ch: SocketChannel) {
                        ch.pipeline().addLast(DiscardServerHandler())
                        ch.pipeline()?.let {
                            it.addLast(
                                "LengthFieldBasedFrameDecoder",
                                LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4)
                            )
                            it.addLast("LengthFieldPrepender", LengthFieldPrepender(4))
                        }
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128) // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true) // (6)

            // Bind and start to accept incoming connections.
            val f = b.bind(port).sync() // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync()
        } finally {
            workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
    }


}

fun main(args: Array<String>) {
    var port = 20000
    if (args.size > 0) {
        port = args[0].toInt()
    }
    DiscardServer(port).run()
}