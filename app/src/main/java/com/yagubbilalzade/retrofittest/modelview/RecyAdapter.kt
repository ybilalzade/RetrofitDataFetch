package com.yagubbilalzade.retrofittest.modelview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yagubbilalzade.retrofittest.R
import com.yagubbilalzade.retrofittest.model.Users


class RecyAdapter(private val userList: ArrayList<Users>):RecyclerView.Adapter<RecyAdapter.ViewHolder>(){



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView1: TextView = itemView.findViewById(R.id.textView)
        val textView2 : TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyview, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = userList[position]

        holder.textView1.text = ItemsViewModel.name
        holder.textView2.text = ItemsViewModel.surname

    }


}