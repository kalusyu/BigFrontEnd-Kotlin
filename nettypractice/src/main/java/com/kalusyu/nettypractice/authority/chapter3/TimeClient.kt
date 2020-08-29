package com.kalusyu.nettypractice.authority.chapter3

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/29 12:07
 *
 **/
class TimeClient {

    @Throws(Exception::class)
    fun connect(port: Int, host: String) {
        val group = NioEventLoopGroup()
        try {
            val b = Bootstrap()
            b.group(group).channel(NioSocketChannel::class.java)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(object:ChannelInitializer<SocketChannel>(){
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()?.addLast(TimeClientHandler())
                    }
                })

            val future = b.connect(host,port).sync()

            future.channel().closeFuture().sync()
        } finally {
            group.shutdownGracefully()
        }
    }
}

fun main() {
    TimeClient().connect(PORT, "127.0.0.1")
}

