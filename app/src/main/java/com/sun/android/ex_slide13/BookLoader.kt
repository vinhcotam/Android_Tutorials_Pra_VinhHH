package com.sun.android.ex_slide13

import android.content.Context
import androidx.annotation.Nullable
import androidx.loader.content.AsyncTaskLoader

class BookLoader internal constructor(context: Context, private val mQueryString: String) :
    AsyncTaskLoader<String>(context) {
    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    @Nullable
    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(mQueryString)
    }
}
