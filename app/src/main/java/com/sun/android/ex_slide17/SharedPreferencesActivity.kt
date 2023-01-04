package com.sun.android.ex_slide17

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sun.android.R
import com.sun.android.databinding.ActivitySharedPreferencesBinding


class SharedPreferencesActivity : AppCompatActivity(), OnClickListener {
    private val binding by lazy {
        ActivitySharedPreferencesBinding.inflate(layoutInflater)
    }
    private var mCount = 0
    private var mColor = 0
    private val mPreferences by lazy {
        getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mColor = ContextCompat.getColor(this, R.color.default_background)
        mCount = mPreferences.getInt(COUNT_KEY, 0)
        binding.textViewShowCount.text = mCount.toString()
        mColor = mPreferences.getInt(COLOR_KEY, mColor)
        binding.textViewShowCount.setBackgroundColor(mColor)
        binding.buttonBlueColor.setOnClickListener(this)
        binding.buttonRedColor.setOnClickListener(this)
        binding.buttonGreenColor.setOnClickListener(this)
        binding.buttonBlackColor.setOnClickListener(this)
    }

    private fun changeBackground(view: View) {
        val color = (view.background as? ColorDrawable)?.color
        if (color != null) {
            binding.textViewShowCount.setBackgroundColor(color)
            mColor = color
        }
    }

    fun reset(view: View) {
        mCount = 0
        binding.textViewShowCount.text = mCount.toString()
        mColor = ContextCompat.getColor(this, R.color.grey)
        binding.textViewShowCount.setBackgroundColor(mColor)
        val preferencesEditor = mPreferences.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }

    override fun onClick(p0: View) {
        changeBackground(p0)
    }

    fun count(view: View) {
        mCount++
        binding.textViewShowCount.text = mCount.toString()
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferences.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)
        preferencesEditor.apply()
    }

    companion object {
        const val COUNT_KEY = "count"
        const val COLOR_KEY = "color"
        const val sharedPrefFile = "com.sun.android.ex_slide17"
    }


}

