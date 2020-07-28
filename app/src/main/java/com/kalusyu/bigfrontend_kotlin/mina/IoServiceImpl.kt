package com.kalusyu.bigfrontend_kotlin.mina

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder
import org.apache.mina.core.filterchain.IoFilterChainBuilder
import org.apache.mina.core.future.WriteFuture
import org.apache.mina.core.service.*
import org.apache.mina.core.session.IoSession
import org.apache.mina.core.session.IoSessionConfig
import org.apache.mina.core.session.IoSessionDataStructureFactory

class IoServiceImpl:IoService {

    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isActive(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getManagedSessions(): MutableMap<Long, IoSession> {
        TODO("Not yet implemented")
    }

    override fun getTransportMetadata(): TransportMetadata {
        TODO("Not yet implemented")
    }

    override fun addListener(listener: IoServiceListener?) {
        TODO("Not yet implemented")
    }

    override fun getStatistics(): IoServiceStatistics {
        TODO("Not yet implemented")
    }

    override fun setHandler(handler: IoHandler?) {
        TODO("Not yet implemented")
    }

    override fun getScheduledWriteBytes(): Int {
        TODO("Not yet implemented")
    }

    override fun removeListener(listener: IoServiceListener?) {
        TODO("Not yet implemented")
    }

    override fun isDisposing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSessionConfig(): IoSessionConfig {
        TODO("Not yet implemented")
    }

    override fun getHandler(): IoHandler {
        TODO("Not yet implemented")
    }

    override fun getManagedSessionCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getActivationTime(): Long {
        TODO("Not yet implemented")
    }

    override fun getScheduledWriteMessages(): Int {
        TODO("Not yet implemented")
    }

    override fun getFilterChainBuilder(): IoFilterChainBuilder {
        TODO("Not yet implemented")
    }

    override fun broadcast(message: Any?): MutableSet<WriteFuture> {
        TODO("Not yet implemented")
    }

    override fun getSessionDataStructureFactory(): IoSessionDataStructureFactory {
        TODO("Not yet implemented")
    }

    override fun setFilterChainBuilder(builder: IoFilterChainBuilder?) {
        TODO("Not yet implemented")
    }

    override fun setSessionDataStructureFactory(sessionDataStructureFactory: IoSessionDataStructureFactory?) {
        TODO("Not yet implemented")
    }

    override fun getFilterChain(): DefaultIoFilterChainBuilder {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun dispose(awaitTermination: Boolean) {
        TODO("Not yet implemented")
    }
}