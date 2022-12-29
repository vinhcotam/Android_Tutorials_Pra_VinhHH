package com.sun.android.ex_slide14

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sun.android.BuildConfig
import com.sun.android.R


class PowerReceiverActivity : AppCompatActivity() {
    private val broadCastReceiverEx14: BroadCastReceiverEx14 = BroadCastReceiverEx14()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_power_receiver)
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(broadCastReceiverEx14, filter);
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadCastReceiverEx14, IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    override fun onDestroy() {
        this.unregisterReceiver(broadCastReceiverEx14);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadCastReceiverEx14);
        super.onDestroy();
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    companion object {
        const val ACTION_CUSTOM_BROADCAST: String = BuildConfig.APPLICATION_ID + R.string.action_custom_broadcast
    }
}
