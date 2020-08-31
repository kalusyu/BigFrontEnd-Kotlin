package com.kalusyu.nettypractice.authority.chapter7

import com.daveanthonythomas.moshipack.MoshiPack
import okio.BufferedSource
import okio.ByteString


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/30 11:51
 *
 **/

data class MessagePackWebsitePlug(var compack: Boolean = true, var schema: Int = 0)

fun main() {
    val src = mutableListOf<String>()
    src.add("msgpack")
    src.add("kumofs")
    src.add("viver")

    val moshipack = MoshiPack()
    val packed:BufferedSource = moshipack.pack(MessagePackWebsitePlug())

    println(packed.readByteString().hex())

    val bytes = ByteString.decodeHex("82a7636f6d7061636bc3a6736368656d6100").toByteArray()
    val plug = moshipack.unpack<MessagePackWebsitePlug>(bytes)
    println("plug:$plug")


}