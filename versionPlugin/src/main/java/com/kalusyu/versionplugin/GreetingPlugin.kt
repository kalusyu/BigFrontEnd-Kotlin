package com.kalusyu.versionplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/28 18:03
 *
 **/
class GreetingPlugin :Plugin<Project>{

    override fun apply(target: Project) {
        target.task("hello"){
            it.doLast {
                println("Hello from the GreetingPlugin")
            }
        }
    }
}