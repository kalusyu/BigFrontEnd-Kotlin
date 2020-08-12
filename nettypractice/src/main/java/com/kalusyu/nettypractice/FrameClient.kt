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


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 16:36
 *
 **/
class FrameClient : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        println("write 123")
        ctx?.write(2134)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        msg as ByteBuf
        val str = msg.readBytes(msg.readableBytes())
        println("ybw $str")
        ctx?.writeAndFlush("googogogo=====")
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
                        pipeline()?.let {
//                            it.addLast(
//                                "LengthFieldBasedFrameDecoder",
//                                LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4)
//                            )
//                            it.addLast("LengthFieldPrepender", LengthFieldPrepender(4))
                            it.addLast(FrameClient())
                        }
                    }
                    ch?.write(10)
                }
            })
        val channelFuture = clientBootStrap.connect("127.0.0.1", 20000).sync()
        channelFuture.channel().writeAndFlush("g000--------------")
        //channelFuture.channel().closeFuture().sync()
    } finally {
        group.shutdownGracefully().sync()
    }
}