package com.kalusyu.bigfrontend_kotlin.mina

import org.apache.mina.core.filterchain.IoFilter
import org.apache.mina.core.filterchain.IoFilterChain
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.core.write.WriteRequest
import org.apache.mina.filter.FilterEvent

class IoFilterImpl:IoFilter {
    override fun event(nextFilter: IoFilter.NextFilter?, session: IoSession?, event: FilterEvent?) {
        TODO("Not yet implemented")
    }

    override fun messageReceived(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        message: Any?
    ) {
        TODO("Not yet implemented")
    }

    override fun onPreRemove(
        parent: IoFilterChain?,
        name: String?,
        nextFilter: IoFilter.NextFilter?
    ) {
        TODO("Not yet implemented")
    }

    override fun filterWrite(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        writeRequest: WriteRequest?
    ) {
        TODO("Not yet implemented")
    }

    override fun destroy() {
        TODO("Not yet implemented")
    }

    override fun messageSent(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        writeRequest: WriteRequest?
    ) {
        TODO("Not yet implemented")
    }

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun filterClose(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun sessionCreated(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun sessionIdle(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        status: IdleStatus?
    ) {
        TODO("Not yet implemented")
    }

    override fun onPostAdd(
        parent: IoFilterChain?,
        name: String?,
        nextFilter: IoFilter.NextFilter?
    ) {
        TODO("Not yet implemented")
    }

    override fun onPreAdd(parent: IoFilterChain?, name: String?, nextFilter: IoFilter.NextFilter?) {
        TODO("Not yet implemented")
    }

    override fun sessionOpened(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun sessionClosed(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun onPostRemove(
        parent: IoFilterChain?,
        name: String?,
        nextFilter: IoFilter.NextFilter?
    ) {
        TODO("Not yet implemented")
    }

    override fun inputClosed(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        TODO("Not yet implemented")
    }

    override fun exceptionCaught(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        cause: Throwable?
    ) {
        TODO("Not yet implemented")
    }
}