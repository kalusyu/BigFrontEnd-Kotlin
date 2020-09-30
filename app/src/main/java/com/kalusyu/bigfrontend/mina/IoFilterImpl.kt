package com.kalusyu.bigfrontend.mina

import org.apache.mina.core.filterchain.IoFilter
import org.apache.mina.core.filterchain.IoFilterAdapter
import org.apache.mina.core.filterchain.IoFilterChain
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.core.write.WriteRequest
import org.apache.mina.filter.FilterEvent

class IoFilterImpl:IoFilterAdapter() {
    override fun event(nextFilter: IoFilter.NextFilter?, session: IoSession?, event: FilterEvent?) {
    }

    override fun messageReceived(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        message: Any?
    ) {
        println("IoFilterImpl messageReceived")
    }

    override fun onPreRemove(
        parent: IoFilterChain?,
        name: String?,
        nextFilter: IoFilter.NextFilter?
    ) {
        println("IoFilterImpl onPreRemove")
    }

    override fun filterWrite(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        writeRequest: WriteRequest?
    ) {
        println("IoFilterImpl filterWrite")
    }

    override fun destroy() {
        println("IoFilterImpl destroy")
    }

    override fun messageSent(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        writeRequest: WriteRequest?
    ) {
        println("IoFilterImpl messageSent")
    }

    override fun init() {
        println("IoFilterImpl init")
    }

    override fun filterClose(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        println("IoFilterImpl filterClose")
    }

    override fun sessionCreated(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        println("IoFilterImpl sessionCreated")
    }

    override fun sessionIdle(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        status: IdleStatus?
    ) {
        println("IoFilterImpl sessionIdle")
    }

    override fun onPostAdd(
        parent: IoFilterChain?,
        name: String?,
        nextFilter: IoFilter.NextFilter?
    ) {
        println("IoFilterImpl onPostAdd")
    }

    override fun onPreAdd(parent: IoFilterChain?, name: String?, nextFilter: IoFilter.NextFilter?) {
        println("IoFilterImpl onPreAdd")
    }

    override fun sessionOpened(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        println("IoFilterImpl sessionOpened")
    }

    override fun sessionClosed(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        println("IoFilterImpl sessionClosed")
    }

    override fun onPostRemove(
        parent: IoFilterChain?,
        name: String?,
        nextFilter: IoFilter.NextFilter?
    ) {
        println("IoFilterImpl onPostRemove")
    }

    override fun inputClosed(nextFilter: IoFilter.NextFilter?, session: IoSession?) {
        println("IoFilterImpl inputClosed")
    }

    override fun exceptionCaught(
        nextFilter: IoFilter.NextFilter?,
        session: IoSession?,
        cause: Throwable?
    ) {
        println("IoFilterImpl exceptionCaught")
    }
}