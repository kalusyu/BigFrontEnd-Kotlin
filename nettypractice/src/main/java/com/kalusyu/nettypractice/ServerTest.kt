package com.kalusyu.nettypractice

import com.alibaba.fastjson.JSON
import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.logging.ByteBufFormat
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import java.nio.ByteBuffer

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/9/1 10:00
 *
 **/
data class Message(val code:Int, val description:String)
data class Request(val message: Message)

class ServerHandler : SimpleChannelInboundHandler<Any>() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        println("server write")
//        ctx?.writeAndFlush("{\"id\":10}")
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        msg as String
        val obj = JSON.parseObject(msg, Request::class.java)
        println("obj = ${obj.message.description}")
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}

class ServerTest {

    @Throws(Exception::class)
    fun bind(port: Int) {

        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()?.run {
                            addLast(
                                "LengthFieldBasedFrameDecoder",
                                LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4)
                            )
                            addLast(StringDecoder())
                            addLast("LengthFieldPrepender", LengthFieldPrepender(4))
                            addLast(StringEncoder())
                            addLast(ServerHandler())
                        }
                    }
                })
            val f = b.bind(port).sync()

            f.channel().closeFuture().sync()

        } finally {
            bossGroup.shutdownGracefully().sync()
            workerGroup.shutdownGracefully().sync()
        }
    }

}

fun main() {
    ServerTest().bind(20001)
}