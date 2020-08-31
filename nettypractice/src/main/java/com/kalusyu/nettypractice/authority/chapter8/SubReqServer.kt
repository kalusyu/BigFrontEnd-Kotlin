package com.kalusyu.nettypractice.authority.chapter8

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/30 23:21
 *
 **/
class SubReqServer {

    @Throws(Exception::class)
    fun bind(port: Int) {
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .option(ChannelOption.SO_BACKLOG, 100)
                .childHandler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()?.run {
                            addLast(ProtobufVarint32FrameDecoder())
                            addLast(ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()))
                            addLast(ProtobufVarint32LengthFieldPrepender())
                            addLast(ProtobufEncoder())
                            addLast(SubReqServerHandler())

                        }
                    }
                })
            val future = b.bind(port).sync()

            future.channel().closeFuture().sync()

        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()

        }
    }
}

class SubReqServerHandler : SimpleChannelInboundHandler<Any>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        val req = msg as SubscribeReqProto.SubscribeReq
        if ("Kalusyu".equals(req.userName, true)) {
            println("Service accept client subscribe req: $req")
            ctx?.writeAndFlush(resp(req.subReqID))
        }
    }

    private fun resp(subReqID: Int): SubscribeRespProto.SubscribeResp {
        return SubscribeRespProto.SubscribeResp.newBuilder()
            .setSubReqID(subReqID)
            .setRespCode(0)
            .setDesc("Netty book order successed")
            .build()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}

fun main() {
    SubReqServer().bind(2000)
}