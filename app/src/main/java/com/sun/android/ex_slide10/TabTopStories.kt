package com.sun.android.ex_slide10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.android.R
import com.sun.android.databinding.FragmentTabCookingBinding
import com.sun.android.databinding.FragmentTabTopStoriesBinding

class TabTopStories : Fragment() {
    private val binding by lazy {
        FragmentTabTopStoriesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}
