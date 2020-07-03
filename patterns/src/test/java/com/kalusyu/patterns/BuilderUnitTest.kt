package com.kalusyu.patterns

import org.junit.jupiter.api.Test
import java.io.File

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/3 14:48
 *
 **/
class BuilderUnitTest {

    @Test
    fun testBuilder() {
        val dialog = dialog {
            title {
                text = "厦门企业繁荣昌盛"
                color = "#ff0000"
            }

            message {
                text = "死定了肯德基拉克丝的建发"
                color = "#00fff0"
            }

            image {
                File.createTempFile("image", "jpg")
            }
        }

        dialog.show()
    }
}