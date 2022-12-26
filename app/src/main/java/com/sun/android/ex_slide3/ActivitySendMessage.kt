package com.sun.android.ex_slide3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.R

class ActivitySendMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textViewTitle : TextView = findViewById(R.id.text_view_title)
        val buttonSend: Button = findViewById(R.id.button_send)
        val textViewReply: TextView = findViewById(R.id.text_view_reply)
        val editTextSend = findViewById<EditText>(R.id.edit_text_send)
        buttonSend.setOnClickListener(){
            if(editTextSend.text.isNullOrEmpty()){
                Toast.makeText(this@ActivitySendMessage, R.string.textr_required_message, Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, ActivityReplyMessage::class.java)
                intent.putExtra(EXTRA_MESSAGE, editTextSend.text.toString())
                startActivity(intent)
            }
        }
        val replySend = intent.getStringExtra(EXTRA_REPLY);
        if(!replySend.isNullOrEmpty()){
            textViewReply.text = replySend
            textViewTitle.visibility= View.VISIBLE
            textViewReply.visibility= View.VISIBLE
        }
    }
    companion object {
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_REPLY="reply"
    }
}


