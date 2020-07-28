package com.kalusyu.bigfrontend_kotlin.mina

import org.apache.mina.core.service.IoHandler
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.FilterEvent

class IoHandlerImpl:IoHandler {
    override fun event(session: IoSession?, event: FilterEvent?) {
        TODO("Not yet implemented")
    }

    override fun messageReceived(session: IoSession?, message: Any?) {
        TODO("Not yet implemented")
    }

    override fun sessionOpened(session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun sessionClosed(session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun messageSent(session: IoSession?, message: Any?) {
        TODO("Not yet implemented")
    }

    override fun inputClosed(session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun sessionCreated(session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun sessionIdle(session: IoSession?, status: IdleStatus?) {
        TODO("Not yet implemented")
    }

    override fun exceptionCaught(session: IoSession?, cause: Throwable?) {
        TODO("Not yet implemented")
    }
}