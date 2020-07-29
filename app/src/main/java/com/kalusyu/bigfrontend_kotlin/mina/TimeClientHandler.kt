package com.kalusyu.bigfrontend_kotlin.mina

import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.proxy.utils.ByteUtilities


class TimeClientHandler : IoHandlerAdapter() {
    @Throws(Exception::class)
    override fun messageReceived(iosession: IoSession?, message: Any) {
        val bbuf = message as IoBuffer
        val byten = ByteArray(bbuf.limit())
        bbuf[byten, bbuf.position(), bbuf.limit()]
        println("客户端收到消息" + /*ByteUtilities.asHex(byten)*/byten)
    }

    @Throws(Exception::class)
    override fun exceptionCaught(session: IoSession?, cause: Throwable?) {
        println("客户端异常")
        super.exceptionCaught(session, cause)
    }

    @Throws(Exception::class)
    override fun messageSent(iosession: IoSession?, obj: Any?) {
        println("客户端消息发送")
        super.messageSent(iosession, obj)
    }

    @Throws(Exception::class)
    override fun sessionClosed(iosession: IoSession?) {
        println("客户端会话关闭")
        super.sessionClosed(iosession)
    }

    @Throws(Exception::class)
    override fun sessionCreated(iosession: IoSession?) {
        println("客户端会话创建")
        super.sessionCreated(iosession)
    }

    @Throws(Exception::class)
    override fun sessionIdle(iosession: IoSession?, idlestatus: IdleStatus?) {
        println("客户端会话休眠")
        super.sessionIdle(iosession, idlestatus)
    }

    @Throws(Exception::class)
    override fun sessionOpened(iosession: IoSession?) {
        println("客户端会话打开")
        super.sessionOpened(iosession)
    }

}