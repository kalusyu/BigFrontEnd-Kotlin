package com.kalusyu.nettypractice.authority.chapter8

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import java.lang.Exception

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/30 23:33
 *
 **/
class SubReqClient {

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
                            addLast(ProtobufVarint32FrameDecoder())
                            addLast(ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()))
                            addLast(ProtobufVarint32LengthFieldPrepender())
                            addLast(ProtobufEncoder())
                            addLast(SubReqClientHandler())
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

class SubReqClientHandler : SimpleChannelInboundHandler<Any>() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        for (i in 0..9) {
            ctx?.write(subReq(i))
        }
        ctx?.flush()
    }

    private fun subReq(i: Int): SubscribeReqProto.SubscribeReq {
        return SubscribeReqProto.SubscribeReq.newBuilder()
            .setSubReqID(i)
            .setUserName("Kalusyu")
            .setProductName("Netty Booke")
            .setAddress("1234").build()
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        println("Receive server response: $msg")
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}

fun main() {
    SubReqClient().connect(2000, "localhost")
}
