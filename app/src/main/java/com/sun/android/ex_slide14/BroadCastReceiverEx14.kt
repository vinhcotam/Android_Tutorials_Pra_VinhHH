package com.sun.android.ex_slide14

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.sun.android.BuildConfig
import com.sun.android.R

class BroadCastReceiverEx14 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var intentAction = intent.action
        if (!intentAction.isNullOrEmpty()) {
            val toastMessage =  when (intentAction) {
                Intent.ACTION_POWER_CONNECTED ->  R.string.power_connected
                Intent.ACTION_POWER_DISCONNECTED ->  R.string.power_disconnected
                ACTION_CUSTOM_BROADCAST ->  R.string.custom_broadcast_received
                else->R.string.unknow_intent_action
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    companion object {
        const val ACTION_CUSTOM_BROADCAST: String = BuildConfig.APPLICATION_ID + R.string.action_custom_broadcast
    }
}
