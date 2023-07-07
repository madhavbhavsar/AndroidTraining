package com.mad.androidtraining.july4profilefragment.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.R
import com.mad.androidtraining.july3profileintent.ProfileIntentActivity
import com.mad.androidtraining.july3profileintent.adapter.ProfileIntentAdapter
import com.mad.androidtraining.july3profileintent.model.ProfileIntentModel
import com.mad.androidtraining.july4profilefragment.ProfileFragmentActivity
import com.mad.androidtraining.july4profilefragment.fragments.AddProfileFragment
import com.mad.androidtraining.july4profilefragment.fragments.ProfileListFragment
import com.mad.androidtraining.july4profilefragment.model.ProfileFragmentModel


class ProfileFragmentAdapter(val context: Context, val data: ArrayList<ProfileFragmentModel>) :
    RecyclerView.Adapter<ProfileFragmentAdapter.ProfileFragmentViewHolder>() {

    class ProfileFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)
        val txtMobile: TextView = itemView.findViewById(R.id.txtMobile)

        val btnEdit: ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
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
        holder.txtEmail.text = "Email - ${model.email}"
        holder.txtMobile.text = "Mobile - ${model.mobile}"

        holder.btnEdit.setOnClickListener {

            val bundle = Bundle()
            //bundle.putString("update", "update")
            bundle.putString("name", model.name)
            bundle.putString("email", model.email)
            bundle.putString("mobile", model.mobile)
            bundle.putString("password", model.password)
            bundle.putString("confpassword", model.confpassword)
            bundle.putString("dob", model.dob)
            bundle.putString("gender", model.gender)
            bundle.putString("hobbies", model.hobbies)
            bundle.putInt("update", position)

            val addProfileFragment = AddProfileFragment()
            addProfileFragment.setArguments(bundle)


            val fragmentProfileActivity = context as ProfileFragmentActivity
            fragmentProfileActivity.saveData(1, bundle)
            fragmentProfileActivity.profileFragmentBinding.vpProfiles.currentItem = 1

        }

        holder.btnDelete.setOnClickListener {
            deleteAlertDialog(model, holder, position)
        }


    }

    private fun deleteAlertDialog(
        model: ProfileFragmentModel,
        holder: ProfileFragmentViewHolder,
        position: Int
    ) {

        val alertDialogBuilder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView: View = inflater.inflate(R.layout.layout_deleteprofile, null)
        alertDialogBuilder.setView(dialogView)
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()

        val btnDelete = dialogView.findViewById<Button>(R.id.btnDelete)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val txtDelete = dialogView.findViewById<TextView>(R.id.txtDelete)

        txtDelete.setText("Delete ${model?.name}")

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        btnDelete.setOnClickListener {

            //Log.i("idtodelete",model.id)
//            val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//            sharedPreferences.edit().remove(model.id).apply()
 //           data.removeAt(position)
            if (context is ProfileFragmentActivity) {
                (context as ProfileFragmentActivity).deleteData(position)
            }
            Toast.makeText(context, "Profile Deleted", Toast.LENGTH_SHORT).show()

            alertDialog.dismiss()


        }
    }
}