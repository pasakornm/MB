package com.example.projectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_date.*
import kotlinx.android.synthetic.main.activity_prepayment.*
import kotlinx.android.synthetic.main.activity_prepayment.btn_date
import kotlinx.android.synthetic.main.activity_prepayment.btn_date2
import kotlinx.android.synthetic.main.activity_prepayment2.*

class EditDate : AppCompatActivity() {
    var idusername =""
    var username = ""
    var password = ""
    var address = ""
    var tel = ""
    var email = ""
    var price = ""
    var date =""
    var editID = ""
    var roomtype = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_date)
        idusername = intent.getStringExtra("1").toString()
        username = intent.getStringExtra("2").toString()
        password = intent.getStringExtra("3").toString()
        address = intent.getStringExtra("4").toString()
        tel = intent.getStringExtra("5").toString()
        email = intent.getStringExtra("6").toString()
        editID = intent.getStringExtra("11").toString()
        roomtype = intent.getStringExtra("12").toString()
        btn_date5.setOnClickListener() {
            val newDateFragment = DatePickerFragment5()
            newDateFragment.show(supportFragmentManager, "Date Picker")

        }

        btn_date6.setOnClickListener() {
            val newDateFragment = DatePickerFragment6()
            newDateFragment.show(supportFragmentManager, "Date Picker")
        }
        button_editdate.setOnClickListener() {
            if (datafragment5.text.isEmpty() and datafragment6.text.isEmpty()) {
                Toast.makeText(this, "กรุณาใส่วันเช็คอินและวันเช็คเอาท์", Toast.LENGTH_LONG).show()
            } else if (datafragment5.text.isEmpty()) {
                Toast.makeText(this, "กรุณาใส่วันเช็คอิน", Toast.LENGTH_LONG).show()
            } else if (datafragment6.text.isEmpty()) {
                Toast.makeText(this, "กรุณาใส่วันเช็คเอาท์", Toast.LENGTH_LONG).show()
            } else {

                val intent = Intent(this@EditDate, EditDateComplete::class.java)
                intent.putExtra("1", idusername)
                intent.putExtra("2", username)
                intent.putExtra("3", password)
                intent.putExtra("4", address)
                intent.putExtra("5", tel)
                intent.putExtra("6", email)
                intent.putExtra("7", datafragment5.text.toString())
                intent.putExtra("8", datafragment6.text.toString())
                intent.putExtra("11", editID)
                intent.putExtra("12", roomtype)
                startActivity(intent)
                finish()
            }
        }
    }
}