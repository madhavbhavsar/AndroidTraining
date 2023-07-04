package com.mad.androidtraining.june30Profile

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityAddProfileBinding
import com.mad.androidtraining.june30Profile.model.ProfileModel
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class AddProfileActivity : AppCompatActivity() {


    var data: ArrayList<ProfileModel> = arrayListOf()
    private lateinit var addProfileBinding: ActivityAddProfileBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_profile)

        addProfileBinding = ActivityAddProfileBinding.inflate(layoutInflater)
        val view = addProfileBinding.root
        setContentView(view)

        dateOfBirth()


        //arrayList = getArrayFromSharedPreferences(this)


        addProfileBinding.btnSubmit.setOnClickListener {


            if (validateName(addProfileBinding.edtName.text.toString().trim())) {
                showToast("Enter Name")
            } else if (validateEmail(addProfileBinding.edtEmail.text.toString().trim())) {
                showToast("Enter Email Properly")
            } else if (validatePhoneNumber(addProfileBinding.edtMobile.text.toString().trim())) {
                showToast("Enter Mobile Properly")
            } else if (validatePassword(addProfileBinding.edtPassword.text.toString().trim())) {
                showToast("Enter Password Properly")
            } else if (validateConfPassword(
                    addProfileBinding.edtConfPassword.text.toString().trim()
                )
            ) {
                showToast("Password Not Matched")
            } else if (validateDoB(addProfileBinding.edtDob.text.toString().trim())) {
                showToast("Select Date of Birth")
            } else if (validateGender()) {
                showToast("Select Gender")
            } else if (validateHobbies()) {
                showToast("Select 1 minimum hobby")
            } else {

                //val id = getIdFromArray(arrayList)
                var id = getIdFromSharedPreference()
                id += 1
                addProfile(id.toString())

                val snack = Snackbar.make(it,"Profile Added", Snackbar.LENGTH_LONG)
                snack.show()
                clearAll()

            }


        }


    }

    private fun clearAll() {
        addProfileBinding.edtName.setText("")
        addProfileBinding.edtEmail.setText("")
        addProfileBinding.edtMobile.setText("")
        addProfileBinding.edtPassword.setText("")
        addProfileBinding.edtConfPassword.setText("")
        addProfileBinding.edtDob.setText("")

        addProfileBinding.rbMale.isChecked = false
        addProfileBinding.rbFemale.isChecked = false
        addProfileBinding.cbSports.isChecked = false
        addProfileBinding.cbMusic.isChecked = false
        addProfileBinding.cbArt.isChecked = false


    }


    private fun dateOfBirth() {
        addProfileBinding.edtDob.isEnabled = false

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            addProfileBinding.edtDob.setText("" + dayOfMonth + "/" +( monthOfYear+1) + "/" + year)
        }, year, month, day)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis();
        addProfileBinding.txtDob.setEndIconOnClickListener {
            datePickerDialog.show()
        }

    }

    private fun getIdFromSharedPreference(): Int {
        var id = 0
        sharedPreferences = this.getSharedPreferences("MyIds", Context.MODE_PRIVATE)
        id = sharedPreferences.getInt("pid", 0)
        return id
    }

    private fun addProfile(id: String) {


        val profile = ProfileModel(
            id,
            addProfileBinding.edtName.text.toString().trim(),
            addProfileBinding.edtEmail.text.toString().trim(),
            addProfileBinding.edtMobile.text.toString().trim(),
            addProfileBinding.edtPassword.text.toString().trim(),
            addProfileBinding.edtConfPassword.text.toString().trim(),
            addProfileBinding.edtDob.text.toString().trim(),
            getGender(),
            getHobbies()

        )
//        arrayList.add(profile)
//        //saveObjectList(this,arrayList)
//
//        hashmapList.put(id,profile)
        saveIdstoSharedPreferences(id.toInt())
        saveProfiletoSharedPreferences(this, profile)

    }

    private fun getGender(): String {
        var gender = ""
        if (addProfileBinding.rbMale.isChecked) {
            gender = "M"
        }
        if (addProfileBinding.rbFemale.isChecked) {
            gender = "F"
        }
        return gender
    }

    private fun getHobbies(): String {
        var checkbox = ""
        if (addProfileBinding.cbSports.isChecked) {
            checkbox += "S"
        }
        if (addProfileBinding.cbMusic.isChecked) {
            checkbox += "M"
        }
        if (addProfileBinding.cbArt.isChecked) {
            checkbox += "A"
        }
        return checkbox
    }


    private fun saveIdstoSharedPreferences(id: Int) {

        sharedPreferences = this.getSharedPreferences("MyIds", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("pid", id)
        editor.apply()
    }

    private fun validateGender(): Boolean {

        if (!addProfileBinding.rbMale.isChecked && !addProfileBinding.rbFemale.isChecked) {
            return true;
        }
        return false
    }

    private fun validateHobbies(): Boolean {

        if (!addProfileBinding.cbSports.isChecked && !addProfileBinding.cbMusic.isChecked && !addProfileBinding.cbArt.isChecked) {
            return true;
        }
        return false

    }

    private fun validateDoB(s: String): Boolean {
        return s.isEmpty()
    }

    private fun validateConfPassword(s: String): Boolean {
        return s != addProfileBinding.edtPassword.text.toString().trim()
    }

    private fun validatePassword(s: String): Boolean {

        if (s.length < 8) return true
        if (s.filter { it.isDigit() }.firstOrNull() == null) return true
        if (s.filter { it.isLetter() }.filter { it.isUpperCase() }
                .firstOrNull() == null) return true
        if (s.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return true

        return false

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
        Toast.makeText(this@AddProfileActivity, s, Toast.LENGTH_SHORT).show()
    }


    private fun saveProfiletoSharedPreferences(context: Context, profile: ProfileModel) {
        val preferences: SharedPreferences =
            context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(profile)
        editor.putString(profile.id, json)
        editor.apply()
    }


}