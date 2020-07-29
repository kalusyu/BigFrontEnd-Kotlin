package com.kalusyu.bigfrontend_kotlin

import com.kalusyu.bigfrontend_kotlin.mina.TimeClientHandler
import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.future.ConnectFuture
import org.apache.mina.core.service.IoConnector
import org.apache.mina.core.session.IoSession
import org.apache.mina.transport.socket.nio.NioSocketConnector
import org.junit.jupiter.api.Test
import java.net.InetSocketAddress


class MinaUnitTest {

    @Test
    fun testMina() {

        val PORT = 6488
        var connector: IoConnector
        var session: IoSession
        val clientHandler = TimeClientHandler()
        connector = NioSocketConnector()
        //设置处理类
        connector.setHandler(clientHandler)
        val connFuture: ConnectFuture = connector.connect(InetSocketAddress("localhost", PORT))
        connFuture.awaitUninterruptibly()
        session = connFuture.session
        clientHandler.sessionOpened(session)
        println("TCP 客户端启动")

        for (j in 0..4) { // 发送两遍
            val bts = ByteArray(20)
            for (i in 0..19) {
                bts[i] = i.toByte()
            }
            val buffer = IoBuffer.allocate(20)
            // 自动扩容
            buffer.isAutoExpand = true
            // 自动收缩
            buffer.isAutoShrink = true
            buffer.put(bts)
            buffer.flip()
            session.write(buffer)
            Thread.sleep(2000)
        }
        // 关闭会话，待所有线程处理结束后
        connector.dispose(true)
    }

}