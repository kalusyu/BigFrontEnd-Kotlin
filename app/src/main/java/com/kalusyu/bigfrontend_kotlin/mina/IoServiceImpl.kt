package com.kalusyu.bigfrontend_kotlin.mina

import org.apache.mina.core.service.AbstractIoService
import org.apache.mina.core.service.TransportMetadata
import org.apache.mina.core.session.IoSessionConfig
import java.util.concurrent.Executor

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/29 9:40
 *
 **/
class IoServiceImpl(sessionConfig: IoSessionConfig, executor: Executor) :
    AbstractIoService(sessionConfig, executor) {

    override fun getTransportMetadata(): TransportMetadata {
        TODO("Not yet implemented")
    }

    override fun getSessionConfig(): IoSessionConfig {
        TODO("Not yet implemented")
    }

    override fun dispose0() {
        TODO("Not yet implemented")
    }
}