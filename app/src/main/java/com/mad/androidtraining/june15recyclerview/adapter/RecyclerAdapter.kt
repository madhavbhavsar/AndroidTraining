package com.mad.androidtraining.june15recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.R
import com.mad.androidtraining.june15recyclerview.model.User

class RecyclerAdapter(val data: List<User>) : RecyclerView.Adapter<RecyclerAdapter.RViewHolder>() {

    class RViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById<ImageView>(R.id.imgProfile)
        val firstName: TextView = itemView.findViewById<TextView>(R.id.txtFirstName)
        val lastName: TextView = itemView.findViewById<TextView>(R.id.txtLastName)
        val email: TextView = itemView.findViewById<TextView>(R.id.txtEmail)
        val mobile: TextView = itemView.findViewById<TextView>(R.id.txtMobile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_details, parent, false)
        return RViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RViewHolder, position: Int) {
        val model = data[position]
        holder.imgProfile.setImageResource(model.imgId)
        holder.firstName.text = model.firstName
        holder.lastName.text = model.lastName
        holder.email.text = model.email
        holder.mobile.text = model.mobile

    }
}