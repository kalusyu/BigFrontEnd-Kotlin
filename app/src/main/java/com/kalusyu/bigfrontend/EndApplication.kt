package com.kalusyu.bigfrontend

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/16 10:49
 *
 **/
class EndApplication:Application(),CameraXConfig.Provider {

    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }


}