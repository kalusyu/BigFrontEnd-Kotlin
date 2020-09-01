package com.kalusyu.nettypractice.authority.chapter10

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST
import io.netty.handler.stream.ChunkedWriteHandler

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
                            addLast("", HttpRequestDecoder())
                            addLast("", HttpObjectAggregator(65536))
                            addLast("", HttpResponseEncoder())
                            addLast("", ChunkedWriteHandler())

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

class SubReqServerHandler : SimpleChannelInboundHandler<FullHttpRequest>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, request: FullHttpRequest?) {
        if (request?.decoderResult()?.isSuccess == false){
//            sendError(ctx, BAD_REQUEST)
            return
        }
        if (request?.method() != HttpMethod.GET){
            return
        }
        val uri = request?.uri()
//        val path = sanitizeUri(uri)
    }


    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        ctx?.close()
    }
}

fun main() {
    SubReqServer().bind(2000)
}