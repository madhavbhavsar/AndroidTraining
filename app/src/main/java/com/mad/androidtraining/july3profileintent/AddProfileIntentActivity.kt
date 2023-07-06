package com.mad.androidtraining.july3profileintent

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mad.androidtraining.databinding.ActivityAddProfileIntentBinding
import com.mad.androidtraining.july3profileintent.model.ProfileIntentModel
import java.util.*
import java.util.regex.Pattern

class AddProfileIntentActivity : AppCompatActivity() {

    var data: ArrayList<ProfileIntentModel> = arrayListOf()
    private lateinit var addProfileBinding: ActivityAddProfileIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_profile) if(intent.getStringExtra("pid")!=null){

        addProfileBinding = ActivityAddProfileIntentBinding.inflate(layoutInflater)
        val view = addProfileBinding.root
        setContentView(view)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        dateOfBirth()


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

                addProfile("0")

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


    private fun addProfile(id: String) {


        val profile = ProfileIntentModel(
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


//        val intent: Intent =
//            Intent(this@AddProfileIntentActivity, ProfileIntentActivity::class.java).putExtra("profile", profile)
//        intent.putExtra("id2",profile.id)
//        startActivity(intent)

        val i = intent
        i.putExtra("id",id)
        i.putExtra("name",addProfileBinding.edtName.text.toString().trim())
        i.putExtra("email",addProfileBinding.edtEmail.text.toString().trim())
        i.putExtra("mobile",addProfileBinding.edtMobile.text.toString().trim())
        i.putExtra("password",addProfileBinding.edtPassword.text.toString().trim())
        i.putExtra("confpassword",addProfileBinding.edtConfPassword.text.toString().trim())
        i.putExtra("dob",addProfileBinding.edtDob.text.toString().trim())
        i.putExtra("gender",getGender())
        i.putExtra("hobbies",getHobbies())

        setResult(RESULT_OK,i);

        onBackPressed()
        finish()



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
        Toast.makeText(this@AddProfileIntentActivity, s, Toast.LENGTH_SHORT).show()
    }






}