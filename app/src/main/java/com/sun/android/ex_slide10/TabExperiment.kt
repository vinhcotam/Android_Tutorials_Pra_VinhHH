package com.sun.android.ex_slide10

import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityTabExperimentBinding
import com.sun.android.ex_slide3.ActivitySendMessage
import com.sun.android.ex_slide5.ImplicitIntents
import com.sun.android.ex_slide6.FragmentEx
import com.sun.android.ex_slide8.DrawerExercise
import com.sun.android.ex_slide9.AlertDateTime
import com.sun.android.ex_slide9.DroidCafe


class TabExperiment : AppCompatActivity() {
    private val binding by lazy {
        ActivityTabExperimentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(TabTopStories(), getString(R.string.stories))
        fragmentAdapter.addFragment(TabTechNews(), getString(R.string.tech))
        fragmentAdapter.addFragment(TabCooking(), getString(R.string.cooking))
        binding.viewPagerTabExperiment.adapter = fragmentAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPagerTabExperiment)
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
        when (item.itemId) {
            R.id.night_mode -> {
                val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                recreate()
            }
            R.id.ex_1 -> {
                val intentEx1 = Intent(this, ActivitySendMessage::class.java)
                startActivity(intentEx1)
            }
            R.id.ex_2 -> {
                val intentEx2 = Intent(this, ImplicitIntents::class.java)
                startActivity(intentEx2)
            }
            R.id.ex_3 -> {
                val intentEx3 = Intent(this, FragmentEx::class.java)
                startActivity(intentEx3)
            }
            R.id.ex_5 -> {
                val intentEx5 = Intent(this, DrawerExercise::class.java)
                startActivity(intentEx5)
            }
            R.id.ex_6_1 -> {
                val intentDroidCafe = Intent(this, DroidCafe::class.java)
                startActivity(intentDroidCafe)
            }
            R.id.ex_6_2 -> {
                val intentAlertDateTime = Intent(this, AlertDateTime::class.java)
                startActivity(intentAlertDateTime)
            }
            R.id.ex_7 ->{
                val intentUserNavigation = Intent(this, TabExperiment::class.java)
                startActivity(intentUserNavigation)
            }
        }
        return true
    }
}
