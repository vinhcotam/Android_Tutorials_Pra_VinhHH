package com.sun.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var tvMessage = findViewById<TextView>(R.id.tvMessage);
        var et_Reply = findViewById<EditText>(R.id.et_Reply);
        var btnReply = findViewById<Button>(R.id.btnReply);
        val messageSend: String = intent.getStringExtra("message").toString();
        tvMessage.setText(messageSend);
        btnReply.setOnClickListener(){
            if(et_Reply.text.length<=0){
                Toast.makeText(this, "Vui lòng nhập Message", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, MainActivity::class.java);
                intent.putExtra("reply", et_Reply.text.toString());
                startActivity(intent);
            }
        }
    }
}
