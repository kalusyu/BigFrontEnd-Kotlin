package com.kalusyu.nettypractice

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 16:36
 *
 **/
class FrameClient : SimpleChannelInboundHandler<Any>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        msg as ByteBuf
        val str = msg.readBytes(msg.readableBytes())
        println("ybw $str")
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        println("write 123")
        for (i in 0..9){
            ctx?.write("{\"message\": {\"code\": \"0\",\"description\": \"success\"}}")
        }
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}

fun main() {
    val group = NioEventLoopGroup()
    try {
        val clientBootStrap = Bootstrap()
        clientBootStrap.group(group).channel(NioSocketChannel::class.java)
            .handler(object : ChannelInitializer<SocketChannel>() {
                override fun initChannel(ch: SocketChannel?) {
                    ch?.run {
                        pipeline()?.run {
                            addLast(
                                "LengthFieldBasedFrameDecoder",
                                LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4)
                            )
                            addLast(StringDecoder())
                            addLast("LengthFieldPrepender", LengthFieldPrepender(4))
                            addLast(StringEncoder())
                            addLast(FrameClient())
                        }
                    }
                }
            })
//        val ip = "192.168.1.120"
        val ip = "127.0.0.1"
        val channelFuture = clientBootStrap.connect(ip, 20001).sync()

        channelFuture.channel().closeFuture().sync()
    } finally {
        group.shutdownGracefully().sync()
    }
}