package com.example.projectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_admin__cancle.*
import kotlinx.android.synthetic.main.activity_admin__home.*
import kotlinx.android.synthetic.main.activity_bed.*
import kotlinx.android.synthetic.main.activity_show_booking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShowBooking : AppCompatActivity() {
    var reservelist = arrayListOf<Reserve>()
    var idusername =""
    var username = ""
    var password = ""
    var address = ""
    var tel = ""
    var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_booking)

        reclycleview20.adapter = UserSerchAdapter(this.reservelist, applicationContext)
        reclycleview20.layoutManager = LinearLayoutManager(applicationContext)
        reclycleview20.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))

        idusername = intent.getStringExtra("1").toString()
        username = intent.getStringExtra("2").toString()
        password = intent.getStringExtra("3").toString()
        address = intent.getStringExtra("4").toString()
        tel = intent.getStringExtra("5").toString()
        email = intent.getStringExtra("6").toString()


        showbook.setOnClickListener(){
            val popup = PopupMenu(this, showbook)
            popup.inflate(R.menu.bt_navigation)
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                    item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.item_home -> {
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("1", idusername)
                        intent.putExtra("2", username)
                        intent.putExtra("3", password)
                        intent.putExtra("4", address)
                        intent.putExtra("5", tel)
                        intent.putExtra("6", email)
                        startActivity(intent)
                        finish()
                    }
                    R.id.item_bed -> {
                        val intent = Intent(this, Bed::class.java)
                        intent.putExtra("1", idusername)
                        intent.putExtra("2", username)
                        intent.putExtra("3", password)
                        intent.putExtra("4", address)
                        intent.putExtra("5", tel)
                        intent.putExtra("6", email)
                        startActivity(intent)
                        finish()
                    }

                    R.id.item_booking -> {
                        val intent = Intent(this, ShowBooking::class.java)
                        intent.putExtra("1", idusername)
                        intent.putExtra("2", username)
                        intent.putExtra("3", password)
                        intent.putExtra("4", address)
                        intent.putExtra("5", tel)
                        intent.putExtra("6", email)
                        startActivity(intent)
                        finish()
                    }

                    R.id.item_info -> {
                        val intent = Intent(this, my_info::class.java)
                        intent.putExtra("1", idusername)
                        intent.putExtra("2", username)
                        intent.putExtra("3", password)
                        intent.putExtra("4", address)
                        intent.putExtra("5", tel)
                        intent.putExtra("6", email)
                        startActivity(intent)
                        finish()
                    }
                    R.id.item_logout -> {
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                true })
            popup.show()
        }
    }

    fun showuserbooking (){
        reservelist.clear()
        val server: ClientAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientAPI::class.java)

        server.serchuser(idusername.toInt()).enqueue(object :
            Callback<List<Reserve>> {
            override fun onResponse(call: Call<List<Reserve>>, response: Response<List<Reserve>>) {
                response.body()?.forEach{
                    reservelist.add(Reserve(it.Reserve_ID, it.Cus_ID, it.Reserve_Date, it.Check_In_Date, it.Check_Out_Date, it.Total_Price, it.Room_Type))
                }
                reclycleview20.adapter = UserSerchAdapter(reservelist, applicationContext)
            }

            override fun onFailure(call: Call<List<Reserve>>, t: Throwable) {
                return t.printStackTrace()
            }
        })

    }
    override fun onResume() {
        super.onResume()
        showuserbooking ()
    }
}