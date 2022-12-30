package com.sun.android.ex_slide12

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.SystemClock
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference
import kotlin.random.Random

class MyAsyncTaskClass(textView: TextView, progressBar: ProgressBar) : AsyncTask<Void, Int, String>() {
    private var textViewMessage: WeakReference<TextView>? = null
    private var progressBarAsync: ProgressBar
    init {
        textViewMessage = WeakReference(textView)
        progressBarAsync = progressBar

    }

    override fun doInBackground(vararg p0: Void?): String {
        val r = Random
        val n: Int = r.nextInt(11)
        val s = n * 200
        for (i in 0..100) {
            SystemClock.sleep((s / 100).toLong())
            publishProgress(i)
        }
        try {
            Thread.sleep(s.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return "Awake at last after sleeping for $s milliseconds!"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val number = values[0]
        if (number != null) {
            progressBarAsync.progress = number
        }

    }

    override fun onPostExecute(result: String) {
        textViewMessage?.get()?.text = result
    }
}
