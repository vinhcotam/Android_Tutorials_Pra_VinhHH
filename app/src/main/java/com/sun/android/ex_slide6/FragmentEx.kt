package com.sun.android.ex_slide6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sun.android.databinding.FragmentExBinding
import com.sun.android.R

class FragmentEx : AppCompatActivity() {
    private val binding by lazy {
        FragmentExBinding.inflate(layoutInflater)
    }
    private var buttonOpenClose: Button? = null
    var isDisplayFragment: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buttonOpenClose = binding.buttonOpenClose
        buttonOpenClose!!.setOnClickListener(){
            if(!isDisplayFragment){
                displayFragment()
            }else{
                closeFragment()
            }
        }
    }
    private fun displayFragment() {
        val entireActivityLifecycle: entireActivityLifecycle = entireActivityLifecycle().newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()
        fragmentTransaction.add(R.id.fragment_container,entireActivityLifecycle).addToBackStack(null).commit()
        buttonOpenClose!!.setText(R.string.close);
        isDisplayFragment = true
    }
    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val entireActivityLifecycle: entireActivityLifecycle? = fragmentManager
            .findFragmentById(R.id.fragment_container) as entireActivityLifecycle?
        if (entireActivityLifecycle != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(entireActivityLifecycle).commit()
        }
        buttonOpenClose!!.setText(R.string.open);
        isDisplayFragment = false
    }
}
