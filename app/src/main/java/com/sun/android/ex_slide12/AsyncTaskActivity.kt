package com.sun.android.ex_slide12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sun.android.R
import com.sun.android.databinding.ActivityAsyncTaskBinding

class AsyncTaskActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAsyncTaskBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            binding.textViewMessage.text = (savedInstanceState.getString(TEXT_STATE))
        }
    }
    fun startTask(view: View) {
        binding.textViewMessage.text = getString(R.string.napping)
        MyAsyncTaskClass(binding.textViewMessage,binding.progressBarAsync).execute()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, binding.textViewMessage.text.toString())
    }
    companion object{
        const val TEXT_STATE = "Current text"
    }


}
