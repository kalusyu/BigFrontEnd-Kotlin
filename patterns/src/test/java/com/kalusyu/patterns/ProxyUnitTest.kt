package com.kalusyu.patterns

import com.kalusyu.patterns.struct.NormalFile
import com.kalusyu.patterns.struct.SecurityFile
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/10 9:37
 *
 **/
class ProxyUnitTest {

    @Test
    fun testProxy() {
        val normalFile = NormalFile()
        val securityFile = SecurityFile(normalFile)
        securityFile.run {
            read()
            password = "secret"
            read()
        }

    }
}