package com.kalusyu.bigfrontend_kotlin.mina

import android.R.id
import org.apache.mina.core.service.IoAcceptor
import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.FilterEvent
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import org.apache.mina.filter.logging.LoggingFilter
import org.apache.mina.transport.socket.nio.NioSocketAcceptor
import java.net.InetSocketAddress
import java.nio.charset.Charset
import java.util.*


class TimeServerHandler : IoHandlerAdapter() {
    override fun sessionOpened(session: IoSession?) {
        println("服务端与客户端连接打开...");
        super.sessionOpened(session)
    }

    override fun sessionClosed(session: IoSession?) {
        println("服务端与客户端连接关闭...");
        super.sessionClosed(session)

    }

    override fun sessionCreated(session: IoSession?) {
        println("服务端与客户端创建连接...");
        super.sessionCreated(session)
    }

    override fun messageReceived(session: IoSession?, message: Any?) {
        super.messageReceived(session, message)
        val strMsg = id.message.toString()
        println("服务端接收到的数据为: $strMsg")
        if (strMsg.trim { it <= ' ' }.equals("quit", ignoreCase = true)) {
            session!!.close()
            return
        }
        val date = Date()
        session!!.write(date.toString())
    }

    override fun messageSent(session: IoSession?, message: Any?) {
        println("服务端发送信息成功..." + message.toString());
        super.messageSent(session, message)
    }

    override fun exceptionCaught(session: IoSession?, cause: Throwable?) {
        println("服务端发送异常..." + cause?.message);
        super.exceptionCaught(session, cause)
    }

    override fun sessionIdle(session: IoSession?, status: IdleStatus?) {
        super.sessionIdle(session, status)
        println("服务端进入空闲状态... " + session?.getIdleCount(status));
    }

    override fun event(session: IoSession?, event: FilterEvent?) {
        super.event(session, event)
    }

    override fun inputClosed(session: IoSession?) {
        super.inputClosed(session)
    }


}

private const val PORT = 6488
fun main() {
//监听即将到来的TCP连接，建立监控器

    //监听即将到来的TCP连接，建立监控器
    val acceptor: IoAcceptor = NioSocketAcceptor()
//设置拦截器
//设置拦截器
    acceptor.filterChain.addLast("logger", LoggingFilter())
    acceptor.filterChain.addLast(
        "codec",
        ProtocolCodecFilter(
            TextLineCodecFactory(
                Charset
                    .forName("GBK")
            )
        )
    )
//设置处理类
//设置处理类
    acceptor.handler = TimeServerHandler()
    //设置配置
    //设置配置
    acceptor.sessionConfig.readBufferSize = 2048
    acceptor.sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 10)

//绑定的监听端口，可多次绑定，也可同时绑定多个。

//绑定的监听端口，可多次绑定，也可同时绑定多个。
    acceptor.bind(InetSocketAddress(PORT))
    println("服务端启动成功......端口号为： $PORT")
}
