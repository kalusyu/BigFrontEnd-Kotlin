package com.kalusyu.nettypractice

import com.alibaba.fastjson.JSON
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.concurrent.Future
import io.netty.util.concurrent.GenericFutureListener
import io.netty.util.concurrent.ScheduledFuture
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 14:22
 *
 **/
class Pinger :ChannelInboundHandlerAdapter(){

    private var channel: Channel? = null

    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
        println("channelActive")
        channel = ctx.channel()
        ping(ctx.channel())
    }

    private fun ping(channel: Channel) {
        val second = 2L
        println("next heart beat will send after " + second + "s.")
        val future: ScheduledFuture<*> = channel.eventLoop().schedule(Runnable {
            if (channel.isActive) {
                println("sending heart beat to the server...")
                val pulseRequest = PulseRequest("ping", "sys", 1)
                val content = JSON.toJSON(pulseRequest).toString()
                println("content:$content")
                val myProtocol = MyProtocol(1, 4, content.toByteArray(Charset.defaultCharset()))
                channel?.writeAndFlush(myProtocol)
            } else {
                System.err.println("The connection had broken, cancel the task that will send a heart beat.")
                channel.closeFuture()
                throw RuntimeException()
            }
        }, second, TimeUnit.SECONDS)

        future.addListener(object:GenericFutureListener<Future<Any?>>{
            override fun operationComplete(future: Future<Any?>?) {
                if (future?.isSuccess == true) {
                    ping(channel);
                }
            }
        })
    }

    @Throws(Exception::class)
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        // 当Channel已经断开的情况下, 仍然发送数据, 会抛异常, 该方法会被调用.
        cause.printStackTrace()
        ctx.close()
    }
}