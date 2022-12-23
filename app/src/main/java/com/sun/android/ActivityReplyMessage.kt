package com.sun.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.sun.android.ActivitySendMessage.Companion.EXTRA_REPLY

class ActivityReplyMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val textViewMessage: TextView = findViewById(R.id.text_view_message)
        val editTextReply: EditText = findViewById(R.id.edit_text_reply)
        val buttonReply: Button = findViewById(R.id.button_reply)
        val messageSend: String = intent.getStringExtra("message").toString();
        textViewMessage.text = messageSend;
        buttonReply.setOnClickListener(){
            if(editTextReply.text.isNullOrEmpty()){
                Toast.makeText(this, R.string.textr_required_message, Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, ActivitySendMessage::class.java);
                intent.putExtra(EXTRA_REPLY, editTextReply.text.toString());
                startActivity(intent);
            }
        }
    }
}
