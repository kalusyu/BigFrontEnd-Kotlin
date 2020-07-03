package com.kalusyu.patterns

import java.io.File

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/3 11:49
 *
 **/
class Dialog {
    fun setTitle(title: String) = println("setting title text $title")
    fun setTitleColor(color: String) = println("setting title color $color")
    fun setMessage(text: String) = println("setting message $text")
    fun setMessageColor(color: String) = println("setting message color $color")
    fun setImage(bitmapBytes: ByteArray) = println("setting image with size ${bitmapBytes.size}")

    fun show() = println("showing dialog $this")
}

class DialogBuilder() {
    constructor(init: DialogBuilder.() -> Unit) : this() {
        init()
    }

    private var titleHolder: TextView? = null
    private var messageHolder: TextView? = null
    private var imageHolder: File? = null


    fun title(attributes: TextView.() -> Unit) {
        titleHolder = TextView().apply { attributes() }
    }

    fun message(attr: TextView.() -> Unit) {
        messageHolder = TextView().apply { attr() }
    }

    fun image(file: () -> File) {
        imageHolder = file()
    }

    fun build(): Dialog {
        println("build")
        val dialog = Dialog()
        titleHolder?.apply {
            dialog.setTitle(text)
            dialog.setTitleColor(color)
        }

        messageHolder?.apply {
            dialog.setMessage(text)
            dialog.setMessageColor(color)
        }

        imageHolder?.apply {
            dialog.setImage(readBytes())
        }
        return dialog
    }

    class TextView {
        var text: String = ""
        var color: String = "#00000"
    }
}

fun dialog(init: DialogBuilder.() -> Unit): Dialog = DialogBuilder(init).build()
