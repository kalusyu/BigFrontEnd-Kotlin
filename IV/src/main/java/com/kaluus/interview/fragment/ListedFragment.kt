package com.kaluus.interview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListAdapter
import androidx.fragment.app.ListFragment
import com.kaluus.interview.R
import kotlinx.android.synthetic.main.layout_simple.*

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