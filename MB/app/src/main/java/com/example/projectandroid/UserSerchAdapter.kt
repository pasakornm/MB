package com.example.projectandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.canclereserve.view.*
import kotlinx.android.synthetic.main.usershowbooking.view.*
import java.text.SimpleDateFormat

class UserSerchAdapter: RecyclerView.Adapter<UserSerchAdapter.ViewHolder> {
    var reserve: List<Reserve>
    var context: Context
    constructor(reserve: List<Reserve>, context: Context){
        this.reserve = reserve
        this.context = context
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val rev_id3 = view.User_Rev_ID2
        val cussid3 = view.User_Cuss_ID2
        val resdt3 = view.User_Res_DT2
        val indt3 = view.User_In_DT2
        val outdt3 = view.User_Out_DT2
        val totalpc3 = view.User_Total_PC2
        val roomtype3 = view.User_Room_Type2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.usershowbooking, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rev_id3.text = reserve[position].Reserve_ID.toString()
        holder.cussid3.text = reserve[position].Cus_ID.toString()
        val formatDate = SimpleDateFormat("dd/MM/YYY HH:mm")
        val Date = formatDate.format(reserve[position].Reserve_Date)
        holder.resdt3.text = Date.toString()
        holder.indt3.text = reserve[position].Check_In_Date
        holder.outdt3.text = reserve[position].Check_Out_Date
        holder.totalpc3.text = reserve[position].Total_Price.toString()
        holder.roomtype3.text = reserve[position].Room_Type.toString()

    }

    override fun getItemCount(): Int {
        return reserve.size
    }
}