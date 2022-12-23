package com.sun.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tvTitle = findViewById(R.id.tvTitle) as TextView
        var btnSend= findViewById<Button>(R.id.btnSend)
        var tvReply = findViewById<TextView>(R.id.tvReply)
        var et_Message = findViewById<EditText>(R.id.et_Message)
        tvTitle.visibility= View.INVISIBLE;
        tvReply.visibility= View.INVISIBLE;
        btnSend.setOnClickListener(){
            if(et_Message.text.length<=0){
                Toast.makeText(this@MainActivity, "Vui lòng nhập Message", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, SecondActivity::class.java);
                intent.putExtra("message", et_Message.text.toString());
                startActivity(intent);
            }
        }
        val replySend = intent.getStringExtra("reply").toString();
        if(!replySend.equals("null")){
            tvReply.setText(replySend);
            tvTitle.visibility= View.VISIBLE;
            tvReply.visibility= View.VISIBLE;
        }
    }
}
