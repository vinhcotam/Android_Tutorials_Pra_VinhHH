package com.sun.android.ex_slide9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityDroidCafeBinding
import com.sun.android.ex_slide3.ActivityReplyMessage
import com.sun.android.ex_slide3.ActivitySendMessage
import com.sun.android.ex_slide5.ImplicitIntents
import com.sun.android.ex_slide6.FragmentEx
import com.sun.android.ex_slide8.DrawerExercise

class DroidCafe : AppCompatActivity() {
    private val binding by lazy {
        ActivityDroidCafeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_droid_cafe)
    }

    fun orderItemClick(view: View) {
        when (view.id) {
            binding.imageButtonDonut.id -> {
                Toast.makeText(this, R.string.order_donut, Toast.LENGTH_LONG).show()
                binding.textViewItemOrder.setText(R.string.order_donut)
            }
            binding.imageButtonIceCreamSanwich.id -> {
                Toast.makeText(this, R.string.order_ice_cream_sanwich, Toast.LENGTH_LONG).show()
                binding.textViewItemOrder.setText(R.string.order_ice_cream_sanwich)
            }
            binding.imageButtonYogurt.id -> {
                Toast.makeText(this, R.string.order_yogurt, Toast.LENGTH_LONG).show()
                binding.textViewItemOrder.setText(R.string.order_yogurt)
            }
            binding.imageButtonCart.id -> {
                val intent = Intent(this, DroidCafeOrder::class.java)
                intent.putExtra(ODER_ITEM_MESSAGE, binding.textViewItemOrder.text.toString());
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        } else if (item.itemId == R.id.ex_1) {
            val intentEx1 = Intent(this, ActivitySendMessage::class.java)
            startActivity(intentEx1)
        } else if (item.itemId == R.id.ex_2) {
            val intentEx2 = Intent(this, ImplicitIntents::class.java)
            startActivity(intentEx2)
        } else if (item.itemId == R.id.ex_3) {
            val intentEx3 = Intent(this, FragmentEx::class.java)
            startActivity(intentEx3)
        } else if (item.itemId == R.id.ex_5) {
            val intentEx5 = Intent(this, DrawerExercise::class.java)
            startActivity(intentEx5)
        } else if (item.itemId == R.id.ex_6_1) {
            val intentDroidCafe = Intent(this, DroidCafe::class.java)
            startActivity(intentDroidCafe)
        } else if (item.itemId == R.id.ex_6_2) {
            val intentAlertDateTime = Intent(this, AlertDateTime::class.java)
            startActivity(intentAlertDateTime)
        }
        return true
    }

    companion object {
        const val ODER_ITEM_MESSAGE = "order"
    }
}
