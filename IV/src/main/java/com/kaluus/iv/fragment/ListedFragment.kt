package com.kaluus.iv.fragment

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/29 10:03
 *
 **/
class ListedFragment : ListFragment() {

    val data = mutableListOf("A","sd","圣诞快乐")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1, data)

    }
}