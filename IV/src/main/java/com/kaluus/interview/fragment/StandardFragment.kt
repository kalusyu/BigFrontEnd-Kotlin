package com.kaluus.interview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kaluus.interview.R
import kotlinx.android.synthetic.main.layout_simple.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/29 10:03
 *
 **/
class StandardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  View.inflate(requireContext(), R.layout.layout_simple, null)
        view.findViewById<TextView>(R.id.tvDemo).text= "StandardFragment"
        return view
    }
}