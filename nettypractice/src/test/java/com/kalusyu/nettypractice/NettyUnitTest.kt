package com.kalusyu.nettypractice

import io.netty.bootstrap.Bootstrap
import io.netty.channel.AdaptiveRecvByteBufAllocator
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import org.junit.Test
import java.net.InetSocketAddress

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 9:06
 *
 **/
class NettyUnitTest {

    @Test
    fun testClient() {
        val group = NioEventLoopGroup()
        try {
            val clientBootStrap = Bootstrap()
            clientBootStrap.group(group).channel(NioSocketChannel::class.java)
                .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator(
                    64, 8192, 65536))
                .remoteAddress(InetSocketAddress("192.168.1.21", 20001))
                .handler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.run {
                            pipeline()?.let {
                                it.addLast("LengthFieldBasedFrameDecoder", LengthFieldBasedFrameDecoder(8192,0,4,0, 4))
                                it.addLast("LengthFieldPrepender", LengthFieldPrepender(4))
//                                it.addLast("myDecoder", MyDecoder())
//                                it.addLast("myEnCoder", MyEnCoder())
//                                it.addLast("protocolHandler", ProtocolHandler())
//                                it.addLast("pinger", Pinger())
//                                it.addLast(ClientHandler())
                                it.addLast(FrameClient())
                            }
                        }
//                    ch?.pipeline()?.addLast(ClientHandler())
                    }
                })
            val channelFuture = clientBootStrap.connect().sync()
            channelFuture.channel().closeFuture().sync()
        } catch (e: Exception) {

        } finally {
            group.shutdownGracefully().sync()
        }
    }
}