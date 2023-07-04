package com.mad.androidtraining.june30Profile.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mad.androidtraining.R
import com.mad.androidtraining.june30Profile.ProfileActivity
import com.mad.androidtraining.june30Profile.model.ProfileModel
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList


class ProfileAdapter (var context:Context, var data:ArrayList<ProfileModel>) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtName : TextView = itemView.findViewById(R.id.txtName)
        val txtEmail : TextView = itemView.findViewById(R.id.txtEmail)
        val txtMobile : TextView = itemView.findViewById(R.id.txtMobile)

        val btnEdit : ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_profiles,parent,false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        val model = data[position]
        holder.txtName.text = "Name - ${model.name}"
        holder.txtEmail.text ="Email - ${model.email}"
        holder.txtMobile.text ="Mobile - ${model.mobile}"



        holder.btnEdit.setOnClickListener {
            editAlertDialog(model,position,holder)
        }

        holder.btnDelete.setOnClickListener {
            deleteAlertDialog(model)
        }

    }

    private fun deleteAlertDialog(model: ProfileModel) {

        val alertDialogBuilder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView: View = inflater.inflate(R.layout.layout_deleteprofile, null)
        alertDialogBuilder.setView(dialogView)
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()

        val btnDelete = dialogView.findViewById<Button>(R.id.btnDelete)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val txtDelete = dialogView.findViewById<TextView>(R.id.txtDelete)

        txtDelete.setText("Delete ${model.name}")

        btnDelete.setOnClickListener {

            Log.i("idtodelete",model.id)
            val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove(model.id).apply()
            Toast.makeText(context, "Profile Deleted", Toast.LENGTH_SHORT).show()

            alertDialog.dismiss()


        }

        alertDialog.setOnDismissListener {
            if (context is ProfileActivity) {
                (context as ProfileActivity).updateData()
            }
        }

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

    }

    private fun editAlertDialog(model: ProfileModel , position: Int, holder: ProfileViewHolder) {

        val alertDialogBuilder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView: View = inflater.inflate(R.layout.layout_editprofile, null)
        alertDialogBuilder.setView(dialogView)


        val edtName = dialogView.findViewById<EditText>(R.id.edtName)
        val edtEmail = dialogView.findViewById<EditText>(R.id.edtEmail)
        val edtMobile = dialogView.findViewById<EditText>(R.id.edtMobile)
        val edtPassword = dialogView.findViewById<EditText>(R.id.edtPassword)
        val edtConfPassword = dialogView.findViewById<EditText>(R.id.edtConfPassword)
        val edtDob = dialogView.findViewById<EditText>(R.id.edtDob)
        val txtDob = dialogView.findViewById<TextInputLayout>(R.id.txtDob)

        val btnMale = dialogView.findViewById<RadioButton>(R.id.btnMale)
        val btnFemale = dialogView.findViewById<RadioButton>(R.id.btnFemale)

        val cbSports = dialogView.findViewById<CheckBox>(R.id.checkboxSports)
        val cbMusic = dialogView.findViewById<CheckBox>(R.id.checkboxMusic)
        val cbArt = dialogView.findViewById<CheckBox>(R.id.checkBoxArt)

        val btnUpdate = dialogView.findViewById<Button>(R.id.btnUpdate)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()

        dateOfBirth(edtDob,txtDob)
        setGender(btnMale,btnFemale,model.gender)
        setHobbies(cbSports,cbMusic,cbArt,model.hobbies)


        edtName.setText( model.name)
        edtEmail.setText( model.email)
        edtMobile.setText( model.mobile)
        edtPassword.setText( model.password)
        edtConfPassword.setText( model.confpassword)
        edtDob.setText( model.dob)

        btnUpdate.setOnClickListener {
            //val getData = getArrayFromSharedPreferences()
            if(validateName(edtName.text.toString().trim())){
                showToast("Enter Name")
            } else if(validateEmail(edtEmail.text.toString().trim())){
                showToast("Enter Email Properly")
            } else if (validatePhoneNumber(edtMobile.text.toString().trim())){
                showToast("Enter Mobile Properly")
            } else if (validatePassword(edtPassword.text.toString().trim())){
                showToast("Enter Password Properly")
            } else if(validateConfPassword(edtConfPassword.text.toString().trim(),edtPassword.text.toString().trim())){
                showToast("Password Not Matched")
            } else if (validateDoB(edtDob.text.toString().trim())){
                showToast("Select Date of Birth")
            } else if (validateGender(btnMale,btnFemale)){
                showToast("Select Gender")
            } else if (validateHobbies(cbSports,cbMusic,cbArt)){
                showToast("Select 1 minimum hobby")
            } else {

                val id = model.id

                val profile = ProfileModel(
                    id,
                    edtName.text.toString().trim(),
                    edtEmail.text.toString().trim(),
                    edtMobile.text.toString().trim(),
                    edtPassword.text.toString().trim(),
                    edtConfPassword.text.toString().trim(),
                    edtDob.text.toString().trim(),
                    getGender(btnMale,btnFemale),
                    getHobbies(cbSports, cbMusic,cbArt)
                )



                updateIdtoSharedPreferences(context,id,profile)
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()

            }

        }

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setOnDismissListener {
            if (context is ProfileActivity) {
                (context as ProfileActivity).updateData()
            }
        }
    }

    private fun setHobbies(cbSports: CheckBox?, cbMusic: CheckBox?, cbArt: CheckBox?, hobbies: String) {

        for(i in hobbies.toCharArray()){
            if(i=='S'){
                cbSports?.isChecked = true
            }
            if(i=='M'){
                cbMusic?.isChecked = true
            }
            if(i=='A'){
                cbArt?.isChecked = true
            }
        }


    }

    private fun setGender(btnMale: RadioButton?, btnFemale: RadioButton?, gender: String) {
        if(gender=="M"){
            btnMale?.isChecked = true
        }
        if(gender=="F"){
            btnFemale?.isChecked = true
        }


    }

    private fun updateIdtoSharedPreferences(context: Context, id: String, profile: ProfileModel) {
        val preferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(profile)
        editor.putString(id, json)
        editor.apply()

    }
    private fun dateOfBirth(edtDob:EditText,txtDob:TextInputLayout) {
        edtDob.isEnabled = false

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            edtDob.setText("" + dayOfMonth + "/" +( monthOfYear+1) + "/" + year)
        }, year, month, day)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis();
        txtDob.setEndIconOnClickListener {
            datePickerDialog.show()
        }

    }

    private fun validateGender(btnMale:RadioButton,btnFemale:RadioButton): Boolean {

        if (!btnMale.isChecked && !btnFemale.isChecked) {
            return true;
        }
        return false
    }

    private fun validateHobbies(checkboxSports:CheckBox,checkboxMusic:CheckBox,checkBoxArt:CheckBox): Boolean {

        if (!checkboxSports.isChecked && !checkboxMusic.isChecked && !checkBoxArt.isChecked) {
            return true;
        }
        return false

    }

    private fun validateDoB(s: String): Boolean {
        return s.isEmpty()
    }

    private fun validateConfPassword(s: String,s2:String): Boolean {
        return s != s2
    }

    private fun validatePassword(s: String): Boolean {

        if (s.length < 8) return true
        if (s.filter { it.isDigit() }.firstOrNull() == null) return true
        if (s.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return true
        if (s.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return true

        return false

    }


    private fun getGender(btnMale:RadioButton,btnFemale:RadioButton): String {
        var gender = ""
        if (btnMale.isChecked) {
            gender = "M"
        }
        if (btnFemale.isChecked) {
            gender = "F"
        }
        return gender
    }

    private fun getHobbies(checkboxSports:CheckBox,checkboxMusic:CheckBox,checkBoxArt:CheckBox): String {
        var checkbox = ""
        if (checkboxSports.isChecked) {
            checkbox += "S"
        }
        if (checkboxMusic.isChecked) {
            checkbox += "M"
        }
        if (checkBoxArt.isChecked) {
            checkbox += "A"
        }
        return checkbox
    }

    private fun validateName(s: String): Boolean {
        return s.isEmpty()
    }

    private fun validateEmail(url: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$")
        val matcher = pattern.matcher(url)
        return !(matcher.matches())
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        val pattern = Pattern.compile("^[+]?[0-9]{10,13}$")
        val matcher = pattern.matcher(phoneNumber)
        return !(matcher.matches())
    }

    private fun showToast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }


}