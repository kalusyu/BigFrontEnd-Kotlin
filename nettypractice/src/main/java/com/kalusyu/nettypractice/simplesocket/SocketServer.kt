package com.kalusyu.nettypractice.simplesocket

import java.io.DataInputStream
import java.net.ServerSocket

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/12 11:32
 *
 **/
class SocketServer {
}

fun main() {
    val server = ServerSocket(3006)
    try {
        val socket = server.accept()
        try {
            val inputStream = DataInputStream(socket.getInputStream())
            val offset = inputStream.readLong()
            println("offset=$offset")

        } finally {
            println("CLosing....")
            socket.close()
        }
    } finally {
        server.close()
    }
}