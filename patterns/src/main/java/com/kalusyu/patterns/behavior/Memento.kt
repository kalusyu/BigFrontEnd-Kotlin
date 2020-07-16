package com.kalusyu.patterns.behavior

/**
 * desc:在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 *
 * Memonto：存储的内部状态；
 * Originator：创建一个备忘录并设置其状态；
 * Caretaker：负责保存备忘录。
 *
 * 耗内存
 *
 * @author biaowen.yu
 * @date 2020/7/16 13:38
 *
 **/
data class Memento(val state: String)

class Original(var state: String) {
    fun createMemento() = Memento(state)
    fun restoreState(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = mutableListOf<Memento>()

    fun saveState(memento: Memento) {
        mementoList.add(memento)
    }

    fun restoreState(index: Int): Memento {
        return mementoList[index]
    }
}