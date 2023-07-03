package com.mad.androidtraining.june16spotify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mad.androidtraining.R
import com.mad.androidtraining.june16spotify.model.ModelBig

class AdapterBig(val data: List<ModelBig>, val context : Context) : RecyclerView.Adapter<AdapterBig.VHBig>() {

    class VHBig(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById<TextView>(R.id.txtTitle)
        val desc: TextView = itemView.findViewById<TextView>(R.id.txtDesc)
        val imgBigId : ImageView = itemView.findViewById<ImageView>(R.id.imgBigImage)
        val imgSmallId : ImageView = itemView.findViewById<ImageView>(R.id.imgSmallImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHBig {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_rvbig, parent, false)
        return VHBig(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VHBig, position: Int) {
        val model = data[position]
        holder.title.text = model.songTitle
        holder.desc.text = model.songDesc
        //holder.imgBigId.setImageResource(model.bigImgId)

        //"https://cdn.pixabay.com/photo/2018/06/17/20/35/chain-3481377_1280.jpg"

        val sharedOptions = RequestOptions().placeholder(R.drawable.ic_loading_spinner).centerCrop()

        Glide
            .with(context)
            .load(model.bigImgId)
            .apply(sharedOptions)
            .into(holder.imgBigId);
        Glide
            .with(context)
            .load(model.smallImgId)
            .apply(sharedOptions)
            .into(holder.imgSmallId);

    }
}