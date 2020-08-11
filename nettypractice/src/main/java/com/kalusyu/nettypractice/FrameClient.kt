package com.kalusyu.nettypractice

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.util.ReferenceCountUtil
import java.lang.StringBuilder
import java.net.InetSocketAddress


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 16:36
 *
 **/
class FrameClient:SimpleChannelInboundHandler<Object>() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        ctx?.writeAndFlush("12323")
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Object?) {

        msg as ByteBuf
        val str = msg.readBytes(msg.readableBytes())
        println("ybw $str")
    }

}

fun main() {
    val group = NioEventLoopGroup()
    try {
        val clientBootStrap = Bootstrap()
        clientBootStrap.group(group).channel(NioSocketChannel::class.java)
            .remoteAddress(InetSocketAddress("127.0.0.1", 20000))
            .handler(object : ChannelInitializer<SocketChannel>() {
                override fun initChannel(ch: SocketChannel?) {
                    ch?.run {
                        pipeline()?.let {
                            it.addLast("LengthFieldBasedFrameDecoder", LengthFieldBasedFrameDecoder(8192,0,4,0, 4))
                            it.addLast("LengthFieldPrepender", LengthFieldPrepender(4))
                            it.addLast(FrameClient())
                        }
                    }
                }
            })
        val channelFuture = clientBootStrap.connect().sync()
        channelFuture.channel().closeFuture().sync()
    } catch (e: Exception) {

    } finally {
        group.shutdownGracefully().sync()
    }
}