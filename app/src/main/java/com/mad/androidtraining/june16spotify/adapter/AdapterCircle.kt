package com.mad.androidtraining.june16spotify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mad.androidtraining.june16spotify.model.ModelSmall
import com.mad.androidtraining.R

class AdapterCircle (val data: List<ModelSmall>, val context:Context) : RecyclerView.Adapter<AdapterCircle.VHCircle>() {

    class VHCircle(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<TextView>(R.id.txtTitle)
        val imgId : ImageView = itemView.findViewById<ImageView>(R.id.imgViewCircle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHCircle {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_rvcircle, parent, false)
        return VHCircle(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VHCircle, position: Int) {
        val model = data[position]
        holder.title.text = model.title
        //holder.imgId.setImageResource(model.imgId)

        Glide
            .with(context)
            .load(model.imgId)
            .centerCrop()
            .placeholder(R.drawable.ic_loading_spinner)
            .into(holder.imgId);

    }
}