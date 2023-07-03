package com.mad.androidtraining.june16spotify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mad.androidtraining.R
import com.mad.androidtraining.june16spotify.model.ModelSquare

class AdapterSquare (val data: List<ModelSquare>, val context : Context) : RecyclerView.Adapter<AdapterSquare.VHSquare>() {

    class VHSquare(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<TextView>(R.id.txtTitle)
        val imgId : ImageView = itemView.findViewById<ImageView>(R.id.imgViewSmall)
        val desc:TextView = itemView.findViewById<TextView>(R.id.txtDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHSquare {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_rvsquare, parent, false)
        return VHSquare(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VHSquare, position: Int) {
        val model = data[position]
        holder.title.text = model.title
//        holder.imgId.setImageResource(model.imgId)
        holder.desc.text = model.desc

        Glide
            .with(context)
            .load(model.imgId)
            .centerCrop()
            .placeholder(R.drawable.ic_loading_spinner)
            .into(holder.imgId)
    }
}