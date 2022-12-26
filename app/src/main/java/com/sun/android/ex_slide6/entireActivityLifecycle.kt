package com.sun.android.ex_slide6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sun.android.databinding.FragmentExBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class entireActivityLifecycle : Fragment() {
    // TODO: Rename and change types of parameters
    private var  params1: String? = null
    private var  params2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params1 = it.getString(ARG_PARAM1)
            params2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View =
            inflater.inflate(com.sun.android.R.layout.fragment_entire_activity_lifecycle, container, false);
        val radioGroup : RadioGroup = rootView.findViewById(com.sun.android.R.id.radio_group);
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = radioGroup.findViewById<View>(checkedId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = rootView.findViewById<TextView>(com.sun.android.R.id.fragment_header)
            when (index) {
                YES -> textView.setText(com.sun.android.R.string.yes_message)
                NO -> textView.setText(com.sun.android.R.string.no_message)
                else -> {}
            }
        }
        return rootView;
    }

    fun newInstance():entireActivityLifecycle{
        return entireActivityLifecycle();
    }
    companion object {
        const val  YES: Int =0
        const val NO: Int =1
    }
}
