package com.kalusyu.nettypractice.discard

import android.os.Parcelable
import com.alibaba.fastjson.JSON
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.SimpleChannelInboundHandler
import kotlinx.android.parcel.Parcelize


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/4 9:36
 *
 **/
class DiscardServerHandler : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        println("connect server ${ctx?.read()}")
        ctx?.run {
            writeAndFlush("to client")
        }
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        println("ybw server read ")
        val response = Response("leftDistance", 1)
        val res = JSON.toJSON(response)
        while (true) {
            Thread.sleep(500)
            ctx?.run {
                println("ybw $res")
                write(res.toString())
            }
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace();
        ctx?.close();
    }
}

@Parcelize
data class Response(val name: String, val type: Int) : Parcelable