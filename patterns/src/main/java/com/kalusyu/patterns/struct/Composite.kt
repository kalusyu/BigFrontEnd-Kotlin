package com.kalusyu.patterns.struct

/**
 * desc:将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具有一致性。
 *
 * @author biaowen.yu
 * @date 2020/7/15 9:23
 *
 **/

interface View {
    fun draw()
}

class CircleView : View {
    override fun draw() {
        println("draw circle")
    }
}

class Rectangle : View {
    override fun draw() {
        println("draw rectangle")
    }
}

class Composite : View {
    private val list = mutableListOf<View>()

    fun addView(view: View) {
        list.add(view)
    }

    override fun draw() {
        for (view in list) {
            view.draw()
        }
    }
}