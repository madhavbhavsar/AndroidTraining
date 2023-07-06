package com.mad.androidtraining.july4profilefragment.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.R
import com.mad.androidtraining.july4profilefragment.ProfileFragmentActivity
import com.mad.androidtraining.july4profilefragment.fragments.AddProfileFragment
import com.mad.androidtraining.july4profilefragment.model.ProfileFragmentModel


class ProfileFragmentAdapter(val context: Context, val data: ArrayList<ProfileFragmentModel>) :
    RecyclerView.Adapter<ProfileFragmentAdapter.ProfileFragmentViewHolder>() {

    class ProfileFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName : TextView = itemView.findViewById(R.id.txtName)
        val txtEmail : TextView = itemView.findViewById(R.id.txtEmail)
        val txtMobile : TextView = itemView.findViewById(R.id.txtMobile)

        val btnEdit : ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileFragmentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_profiles, parent, false)
        return ProfileFragmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProfileFragmentViewHolder, position: Int) {
        val model = data[position]
        holder.txtName.text = "Name - ${model.name}"
        holder.txtEmail.text ="Email - ${model.email}"
        holder.txtMobile.text ="Mobile - ${model.mobile}"

        holder.btnEdit.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("update", "update")
            bundle.putString("name", model.name)

            val addProfileFragment = AddProfileFragment()
            addProfileFragment.setArguments(bundle)


            val fragmentProfileActivity = context as ProfileFragmentActivity
            fragmentProfileActivity.saveData(1,bundle)
            fragmentProfileActivity.profileFragmentBinding.vpProfiles.currentItem = 1

        }

        holder.btnDelete.setOnClickListener {

        }



    }
}