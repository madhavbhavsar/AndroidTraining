package com.mad.androidtraining.july4profilefragment.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.FragmentAddProfileBinding
import com.mad.androidtraining.july4profilefragment.ProfileFragmentActivity
import com.mad.androidtraining.july4profilefragment.model.ProfileFragmentModel
import java.util.Calendar
import java.util.regex.Pattern


class AddProfileFragment : Fragment() {

    private lateinit var addProfileFragment:FragmentAddProfileBinding
    private lateinit var profile: ProfileFragmentModel
    private var bundle:Bundle? = null
    private var position:Int =-1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addProfileFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_add_profile , container, false);

        addProfileFragment.edtName.setText(arguments?.getString("message"))

        dateOfBirth()


        addProfileFragment.btnSubmit.setOnClickListener {


            if (validateName(addProfileFragment.edtName.text.toString().trim())) {
                showToast("Enter Name")
            } else if (validateEmail(addProfileFragment.edtEmail.text.toString().trim())) {
                showToast("Enter Email Properly")
            } else if (validatePhoneNumber(addProfileFragment.edtMobile.text.toString().trim())) {
                showToast("Enter Mobile Properly")
            } else if (validatePassword(addProfileFragment.edtPassword.text.toString().trim())) {
                showToast("Enter Password Properly")
            } else if (validateConfPassword(
                    addProfileFragment.edtConfPassword.text.toString().trim()
                )
            ) {
                showToast("Password Not Matched")
            } else if (validateDoB(addProfileFragment.edtDob.text.toString().trim())) {
                showToast("Select Date of Birth")
            } else if (validateGender()) {
                showToast("Select Gender")
            } else if (validateHobbies()) {
                showToast("Select 1 minimum hobby")
            } else {

                addProfile("0")

                val snack = Snackbar.make(it, "Profile Added", Snackbar.LENGTH_LONG)
                snack.show()
                clearAll()

            }
        }

        addProfileFragment.btnData.setOnClickListener {
            addProfileFragment.edtName.setText("test1")
            addProfileFragment.edtEmail.setText("test@gmail.com")
            addProfileFragment.edtMobile.setText("6565656565")
            addProfileFragment.edtPassword.setText("123@Qwerty")
            addProfileFragment.edtConfPassword.setText("123@Qwerty")
            addProfileFragment.edtDob.setText("12/12/2012")
            addProfileFragment.rbMale.isChecked = false
            addProfileFragment.rbFemale.isChecked = true
            addProfileFragment.cbSports.isChecked = false
            addProfileFragment.cbMusic.isChecked = true
            addProfileFragment.cbArt.isChecked = true
        }



        return addProfileFragment.root
    }

    private fun clearAll() {
        addProfileFragment.edtName.setText("")
        addProfileFragment.edtEmail.setText("")
        addProfileFragment.edtMobile.setText("")
        addProfileFragment.edtPassword.setText("")
        addProfileFragment.edtConfPassword.setText("")
        addProfileFragment.edtDob.setText("")
        addProfileFragment.rbMale.isChecked = false
        addProfileFragment.rbFemale.isChecked = false
        addProfileFragment.cbSports.isChecked = false
        addProfileFragment.cbMusic.isChecked = false
        addProfileFragment.cbArt.isChecked = false


    }


    private fun dateOfBirth() {
        addProfileFragment.edtDob.isEnabled = false

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            addProfileFragment.edtDob.setText("" + dayOfMonth + "/" +( monthOfYear+1) + "/" + year)
        }, year, month, day)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis();
        addProfileFragment.txtDob.setEndIconOnClickListener {
            datePickerDialog.show()
        }

    }


    private fun addProfile(id: String) {


//        val profile = ProfileFragmentModel(
//            addProfileFragment.edtName.text.toString().trim(),
//            addProfileFragment.edtEmail.text.toString().trim(),
//            addProfileFragment.edtMobile.text.toString().trim(),
//            addProfileFragment.edtPassword.text.toString().trim(),
//            addProfileFragment.edtConfPassword.text.toString().trim(),
//            addProfileFragment.edtDob.text.toString().trim(),
//            getGender(),
//            getHobbies()
//        )
        val profileListFragment = ProfileListFragment()
        val args = Bundle()
        args.putString("name", addProfileFragment.edtName.text.toString().trim())
        args.putString("email", addProfileFragment.edtEmail.text.toString().trim())
        args.putString("mobile", addProfileFragment.edtMobile.text.toString().trim())
        args.putString("password", addProfileFragment.edtPassword.text.toString().trim())
        args.putString("confpassword", addProfileFragment.edtConfPassword.text.toString().trim())
        args.putString("dob", addProfileFragment.edtDob.text.toString().trim())
        args.putString("gender", getGender())
        args.putString("hobbies", getHobbies())
        args.putInt("update",position)

        profileListFragment.arguments = args



        val activity = requireActivity() as ProfileFragmentActivity
        activity.saveData(0,args)
        activity.profileFragmentBinding.vpProfiles.currentItem=0

        //addProfileFragment.btnSubmit.text = "Submit"
        position =-1


//        val intent: Intent =
//            Intent(this@AddProfileIntentActivity, ProfileIntentActivity::class.java).putExtra("profile", profile)
//        intent.putExtra("id2",profile.id)
//        startActivity(intent)

//        val i = intent
//        i.putExtra("id",id)
//        i.putExtra("name",addProfileFragment.edtName.text.toString().trim())
//        i.putExtra("email",addProfileFragment.edtEmail.text.toString().trim())
//        i.putExtra("mobile",addProfileFragment.edtMobile.text.toString().trim())
//        i.putExtra("password",addProfileFragment.edtPassword.text.toString().trim())
//        i.putExtra("confpassword",addProfileFragment.edtConfPassword.text.toString().trim())
//        i.putExtra("dob",addProfileFragment.edtDob.text.toString().trim())
//        i.putExtra("gender",getGender())
//        i.putExtra("hobbies",getHobbies())

        //setResult(AppCompatActivity.RESULT_OK,i);

        //onBackPressed()
        //finish()



    }

    override fun onResume() {
        super.onResume()

        clearAll()
        position=-1

        val activity = requireActivity() as ProfileFragmentActivity
        if(position!=-1){
            addProfileFragment.btnSubmit.text = "Update"
        } else {
            addProfileFragment.btnSubmit.text = "Submit"
        }
        if(activity.getSavedDataBundle()!=null){
            bundle = activity.getSavedDataBundle()!!
            addProfileFragment.edtName.setText(bundle!!.getString("name"))
            addProfileFragment.edtEmail.setText(bundle!!.getString("email"))
            addProfileFragment.edtMobile.setText(bundle!!.getString("mobile"))
            addProfileFragment.edtPassword.setText(bundle!!.getString("password"))
            addProfileFragment.edtConfPassword.setText(bundle!!.getString("confpassword"))
            addProfileFragment.edtDob.setText(bundle!!.getString("dob"))

            val gender: String? = bundle!!.getString("gender")
            val hobbies: String? = bundle!!.getString("hobbies")
            if (gender != null) {
                setGender(addProfileFragment.rbMale,addProfileFragment.rbFemale,gender)
            }
            if (hobbies != null) {
                setHobbies(addProfileFragment.cbSports,addProfileFragment.cbMusic,addProfileFragment.cbArt,hobbies)
            }

            position = bundle!!.getInt("update",-1)

            if(position!=-1){
                addProfileFragment.btnSubmit.text = "Update"
            } else {
                addProfileFragment.btnSubmit.text = "Submit"
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

    private fun getGender(): String {
        var gender = ""
        if (addProfileFragment.rbMale.isChecked) {
            gender = "M"
        }
        if (addProfileFragment.rbFemale.isChecked) {
            gender = "F"
        }
        return gender
    }

    private fun getHobbies(): String {
        var checkbox = ""
        if (addProfileFragment.cbSports.isChecked) {
            checkbox += "S"
        }
        if (addProfileFragment.cbMusic.isChecked) {
            checkbox += "M"
        }
        if (addProfileFragment.cbArt.isChecked) {
            checkbox += "A"
        }
        return checkbox
    }


    private fun validateGender(): Boolean {

        if (!addProfileFragment.rbMale.isChecked && !addProfileFragment.rbFemale.isChecked) {
            return true;
        }
        return false
    }

    private fun validateHobbies(): Boolean {

        if (!addProfileFragment.cbSports.isChecked && !addProfileFragment.cbMusic.isChecked && !addProfileFragment.cbArt.isChecked) {
            return true;
        }
        return false

    }

    private fun validateDoB(s: String): Boolean {
        return s.isEmpty()
    }

    private fun validateConfPassword(s: String): Boolean {
        return s != addProfileFragment.edtPassword.text.toString().trim()
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
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }





}