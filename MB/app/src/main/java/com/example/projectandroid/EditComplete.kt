package com.example.projectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_complete.*

class EditComplete : AppCompatActivity() {
    var idusername =""
    var username = ""
    var password = ""
    var address = ""
    var tel = ""
    var email = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        idusername = intent.getStringExtra("1").toString()
        username = intent.getStringExtra("2").toString()
        password = intent.getStringExtra("3").toString()
        address = intent.getStringExtra("4").toString()
        tel = intent.getStringExtra("5").toString()
        email = intent.getStringExtra("6").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_complete)
        btn_editComplete.setOnClickListener(){
            Toast.makeText(this@EditComplete, "กลับสู่หน้าหลัก", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@EditComplete, Home::class.java)
            intent.putExtra("1", idusername)
            intent.putExtra("2", username)
            intent.putExtra("3", password)
            intent.putExtra("4", address)
            intent.putExtra("5", tel)
            intent.putExtra("6", email)
            startActivity(intent)
            finish()
        }

    }
}