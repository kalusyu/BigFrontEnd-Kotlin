package com.kalusyu.nettypractice.simplesocket

import java.io.DataOutputStream
import java.net.Socket

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/12 11:32
 *
 **/
class SocketClient {

}

fun main() {
    val socket = Socket("localhost",3006)
    val out = socket.getOutputStream()
    val ins = socket.getInputStream()
    val dout = DataOutputStream(out)
    dout.writeLong(12342)

}