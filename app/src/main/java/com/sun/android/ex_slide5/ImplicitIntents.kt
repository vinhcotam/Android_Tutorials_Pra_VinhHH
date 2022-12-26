package com.sun.android.ex_slide5

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sun.android.R
import com.sun.android.databinding.ActivityImplicitIntentsBinding
import com.sun.android.databinding.ActivityMainBinding

class ImplicitIntents : AppCompatActivity() {
    private val binding by lazy {
        ActivityImplicitIntentsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val editTextSearchWeb = binding.editTextSearchWeb
        val buttonSearchWeb = binding.buttonSearchWeb
        val editTextSearchLocation = binding.editTextSearchLocation
        val buttonSearchLocation = binding.buttonSearchLocation
        val editTextShareMessage = binding.editTextShareMessage
        val buttonShareMessage = binding.buttonShareMessage

        buttonSearchWeb.setOnClickListener() {
            if (editTextSearchWeb.text.toString().isNullOrEmpty()) {
                Toast.makeText(this, R.string.text_required_message, Toast.LENGTH_LONG).show()
            } else {
                val searchWebIntent: Intent = Uri.parse(editTextSearchWeb.text.toString()).let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }
                try {
                    startActivity(searchWebIntent);
                    editTextSearchWeb.text.clear()
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, R.string.intent_exception, Toast.LENGTH_LONG).show()
                }
            }


        }
        buttonSearchLocation.setOnClickListener() {
            if (editTextSearchLocation.text.toString().isNullOrEmpty()) {
                Toast.makeText(this, R.string.text_required_message, Toast.LENGTH_LONG).show()
            } else {
                val searchMapIntent: Intent =
                    Uri.parse("google.navigation:q=" + editTextSearchLocation.text.toString()).let { location ->
                        Intent(Intent.ACTION_VIEW, location)
                    }
                try {
                    startActivity(searchMapIntent);
                    editTextSearchLocation.text.clear()
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, R.string.intent_exception, Toast.LENGTH_LONG).show()
                }
            }


        }
        buttonShareMessage.setOnClickListener() {
            if (editTextShareMessage.text.toString().isNullOrEmpty()) {
                Toast.makeText(this, R.string.text_required_message, Toast.LENGTH_LONG).show()
            } else {
                val shareMessageIntent = Intent(Intent.ACTION_SEND)
                shareMessageIntent.putExtra(Intent.EXTRA_TEXT, editTextShareMessage.text.toString())
                shareMessageIntent.type = "text/plain"
                try {
                    startActivity(Intent.createChooser(shareMessageIntent, "Share to other apps"))
                    editTextShareMessage.text.clear()

                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, R.string.intent_exception, Toast.LENGTH_LONG).show()
                }
            }


        }
    }
}
