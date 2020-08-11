package com.kalusyu.nettypractice.telnet

import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 20:26
 *
 **/
val SSL = System.getProperty("ssl") != null
val HOST = System.getProperty("host", "127.0.0.1")
val PORT = System.getProperty("port", if (SSL) "8992" else "8023")!!.toInt()
fun main() {

    val sslCtx: SslContext? = if (SSL) {
        SslContextBuilder.forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE).build()
    } else {
        null
    }

    val group: EventLoopGroup = NioEventLoopGroup()
    try {
        val b = Bootstrap()
        b.group(group)
            .channel(NioSocketChannel::class.java)
            .handler(TelnetClientInitializer(sslCtx))

        // Start the connection attempt.
        val ch: Channel = b.connect(HOST, PORT).sync().channel()

        // Read commands from the stdin.
        var lastWriteFuture: ChannelFuture? = null
        val `in` = BufferedReader(InputStreamReader(System.`in`))
        while (true) {
            val line: String = `in`.readLine() ?: break

            // Sends the received line to the server.
            lastWriteFuture = ch.writeAndFlush(
                """
                    $line
                    
                    """.trimIndent()
            )

            // If user typed the 'bye' command, wait until the server closes
            // the connection.
            if ("bye" == line.toLowerCase()) {
                ch.closeFuture().sync()
                break
            }
        }

        // Wait until all messages are flushed before closing the channel.
        lastWriteFuture?.sync()
    } finally {
        group.shutdownGracefully()
    }
}