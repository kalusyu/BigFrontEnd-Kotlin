package com.kalusyu.patterns

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/1 10:30
 *
 **/
interface TextChangeListener {
    fun onTextChange(oldText: String, newText: String)
}

class PrintTextChange : TextChangeListener {
    var text = ""
    override fun onTextChange(oldText: String, newText: String) {
        text = "Text is changed: $oldText -> $newText"
    }
}

class TextView {
    val listeners = mutableListOf<TextChangeListener>()
    var text: String by Delegates.observable("<empty>") { _, old, new ->
        listeners.forEach {
            it.onTextChange(old, new)
        }
    }
    var name: String by MyDelegate()
}


class MyDelegate {
    var storedValue: String? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me! current value is $storedValue"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        storedValue = value
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}