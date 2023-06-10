package com.example.imageapp.test

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.R

class TestAdapter(val context : Context) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private var userList : List<User> = emptyList()
    class TestViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.user_name)
        val email = view.findViewById<TextView>(R.id.user_email)
        val phone = view.findViewById<TextView>(R.id.user_phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_item, parent, false)

        return TestViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.email.text = userList[position].email
        holder.phone.text = userList[position].phone

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${userList[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list : List<User>) {
        userList = list
        notifyDataSetChanged()
    }
}