package com.sun.android.ex_slide9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sun.android.R
import com.sun.android.databinding.ActivityDroidCafeBinding
import com.sun.android.databinding.ActivityDroidCafeOrderBinding
import com.sun.android.ex_slide9.DroidCafe.Companion.ODER_ITEM_MESSAGE

class DroidCafeOrder : AppCompatActivity() {
    private val binding by lazy {
        ActivityDroidCafeOrderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textViewOrdered.text = intent.getStringExtra(ODER_ITEM_MESSAGE).toString()
        binding.radioButtonSameDay.setOnClickListener() {
            Toast.makeText(this, R.string.same_day_message_service, Toast.LENGTH_LONG).show()
        }
        binding.radioButtonNextDay.setOnClickListener() {
            Toast.makeText(this, R.string.next_day_ground_delivery, Toast.LENGTH_LONG).show()
        }
        binding.radioButtonPickUp.setOnClickListener() {
            Toast.makeText(this, R.string.pick_up, Toast.LENGTH_LONG).show()
        }
    }
}
