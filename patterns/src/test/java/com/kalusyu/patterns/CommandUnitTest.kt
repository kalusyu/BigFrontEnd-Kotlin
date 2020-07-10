package com.kalusyu.patterns

import com.kalusyu.patterns.behavior.CommandProcess
import com.kalusyu.patterns.behavior.CopyCommand
import com.kalusyu.patterns.behavior.DeleteCommand
import com.kalusyu.patterns.behavior.Operator
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/9 9:03
 *
 **/
class CommandUnitTest {

    @Test
    fun testCommand() {
        val operator = Operator()
        val copyCommand = CopyCommand(operator)
        val deleteCommand = DeleteCommand(operator)
        val commandProcess = CommandProcess()
        commandProcess.addToQueue(copyCommand)
        commandProcess.addToQueue(deleteCommand)

        commandProcess.processCommands()

    }
}