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

    }
}