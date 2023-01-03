package com.sun.android.ex_slide13

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.sun.android.R
import com.sun.android.databinding.ActivityBookApiBinding
import org.json.JSONException
import org.json.JSONObject


class BookApiActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    private val binding by lazy {
        ActivityBookApiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun searchBook(view: View) {
        val queryString: String = binding.editTextEnterBookName.text.toString()
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager?.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        val connMgr = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = null
        if (connMgr != null) {
            networkInfo = connMgr.activeNetworkInfo
        }
        if (networkInfo != null && networkInfo.isConnected && queryString.isNotEmpty()
        ) {
            val queryBundle = Bundle()
            queryBundle.putString(R.string.query.toString(), queryString)
            supportLoaderManager.restartLoader(0, queryBundle, this)
            binding.textViewBookAuthor.text = ""
            binding.textViewBookNameResult.setText(R.string.loading)
        } else {
            if (queryString.isEmpty()) {
                binding.textViewBookAuthor.text = ""
                binding.textViewBookNameResult.setText(R.string.no_result)
            } else {
                binding.textViewBookAuthor.text = ""
                binding.textViewBookNameResult.setText(R.string.no_network)
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        var queryString: String = ""

        if (args != null) {
            queryString = args.getString(R.string.query.toString()).toString()
        }

        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            val jsonObject = JSONObject(data)
            val itemsArray = jsonObject.getJSONArray(ITEM)
            var i = 0
            var title: String? = null
            var authors: String? = null
            while (i < itemsArray.length() &&
                authors == null && title == null
            ) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject(VOLUME_INFO)
                try {
                    title = volumeInfo.getString(TITLE)
                    authors = volumeInfo.getString(AUTHOR)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                i++
            }
            if (title != null && authors != null) {
                binding.textViewBookNameResult.text = title
                binding.textViewBookAuthor.text = authors
            } else {
                binding.textViewBookNameResult.setText(R.string.no_result)
                binding.textViewBookAuthor.text = ""
            }
        } catch (e: Exception) {
            binding.textViewBookNameResult.setText(R.string.no_result)
            binding.textViewBookAuthor.text = ""
            e.printStackTrace()
        }

    }

    override fun onLoaderReset(loader: Loader<String>) {
    }

    companion object {
        const val TITLE = "title"
        const val AUTHOR = "authors"
        const val VOLUME_INFO = "volumeInfo"
        const val ITEM = "items"
    }
}
