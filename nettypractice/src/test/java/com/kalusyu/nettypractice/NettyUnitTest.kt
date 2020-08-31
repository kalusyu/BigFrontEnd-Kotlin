package com.kalusyu.nettypractice

import com.kalusyu.nettypractice.authority.chapter8.SubscribeReqProto
import io.netty.bootstrap.Bootstrap
import io.netty.channel.AdaptiveRecvByteBufAllocator
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import org.junit.Assert
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

    @Test
    fun testProtocol() {
        val req = createSubscribeReq()
        println("Before encode: $req")
        val req2 = decode(encode(req))
        println("After decode: $req2")
        Assert.assertEquals(req, req2)
    }

    companion object {

        @JvmStatic
        fun encode(req: SubscribeReqProto.SubscribeReq): ByteArray {
            return req.toByteArray()
        }

        @JvmStatic
        fun decode(body: ByteArray): SubscribeReqProto.SubscribeReq {
            return SubscribeReqProto.SubscribeReq.parseFrom(body)
        }

        @JvmStatic
        fun createSubscribeReq():SubscribeReqProto.SubscribeReq{
            return SubscribeReqProto.SubscribeReq.newBuilder()
                .setSubReqID(1)
                .setUserName("Lileifeng")
                .setProductName("Netty Book")
                .setAddress("232323").build()
        }
    }
}