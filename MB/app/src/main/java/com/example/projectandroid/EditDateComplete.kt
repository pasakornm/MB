package com.example.projectandroid

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_edit_date.*
import kotlinx.android.synthetic.main.activity_edit_date_complete.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.register
import kotlinx.android.synthetic.main.activity_qrpayment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class EditDateComplete : AppCompatActivity() {
    var idusername = ""
    var username = ""
    var password = ""
    var address = ""
    var tel = ""
    var email = ""
    var date3 = ""
    var date4 = ""
    var price = ""
    var roomtype = ""
    var editID = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_date_complete)

        idusername = intent.getStringExtra("1").toString()
        username = intent.getStringExtra("2").toString()
        password = intent.getStringExtra("3").toString()
        address = intent.getStringExtra("4").toString()
        tel = intent.getStringExtra("5").toString()
        email = intent.getStringExtra("6").toString()
        date3 = intent.getStringExtra("7").toString()
        date4 = intent.getStringExtra("8").toString()
        editID = intent.getStringExtra("11").toString()
        roomtype = intent.getStringExtra("12").toString()

        if(roomtype == "1"){
            price = "2500"
        }

        else if (roomtype == "2"){
            price = "5000"
        }

        val parts = date3.split("/")
        var startday = parts[0]
        var startmonth = parts[1]
        var startyear = parts[2]
        var parts2 = date4.split("/")
        var endday = parts2[0]
        var endmonth = parts2[1]
        var endyear = parts2[2]

        var start = LocalDate.of(startyear.toInt(), startmonth.toInt(), startday.toInt())
        var end = LocalDate.of(endyear.toInt(), endmonth.toInt(), endday.toInt())
        var calculator = ChronoUnit.DAYS.between(start, end)
        var totalprice2 = price.toInt() * calculator.toInt()

        btn_editdateComplete.setOnClickListener() {
            val server: ClientAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientAPI::class.java)
            server.editdate(editID.toInt(),date3,date4,totalprice2).enqueue(object : Callback<Reserve> {
                override fun onResponse(call: Call<Reserve>, response: Response<Reserve>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@EditDateComplete, Admin_Cancle::class.java)
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

                override fun onFailure(call: Call<Reserve>, t: Throwable) {
                    return t.printStackTrace()
                }
            })
        }
    }
}