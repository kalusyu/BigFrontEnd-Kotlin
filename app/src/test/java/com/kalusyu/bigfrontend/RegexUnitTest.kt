package com.kalusyu.bigfrontend

import org.junit.jupiter.api.Test
import java.util.regex.Pattern

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/17 11:39
 *
 **/
class RegexUnitTest {

    @Test
    fun testRegex(){
        val transport = "RTP/AVP/UDP;unicast;client_port=55640-55641;source=3.84.6.190;server_port=929498-929499;ssrc=5A721AC3"
        val regexUDPTransport = Pattern.compile(
            "client_port=(\\d+)-\\d+;server_port=(\\d+)-\\d+",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = regexUDPTransport.matcher(transport)
        println("found : ${matcher.find()}" )
    }
}