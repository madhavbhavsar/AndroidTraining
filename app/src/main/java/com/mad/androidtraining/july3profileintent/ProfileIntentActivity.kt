package com.mad.androidtraining.july3profileintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityProfileIntentBinding
import com.mad.androidtraining.july3profileintent.adapter.ProfileIntentAdapter
import com.mad.androidtraining.july3profileintent.model.ProfileIntentModel


class ProfileIntentActivity : AppCompatActivity() {

    var dataArrayList : ArrayList<ProfileIntentModel> = arrayListOf()
    private lateinit var profileBinding: ActivityProfileIntentBinding
    lateinit var profile :ProfileIntentModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_profile)

        profileBinding = ActivityProfileIntentBinding.inflate(layoutInflater)
        val view = profileBinding.root
        setContentView(view)


        Glide.with(this)
            .load("https://cdn.pixabay.com/photo/2017/11/06/13/45/cap-2923682_1280.jpg")
            .placeholder(R.drawable.ic_loading_spinner)
            .centerCrop()
            .into(findViewById(R.id.imgProfileImage))


        var isShow = true
        var scrollRange = -1
        profileBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                profileBinding.collapsingToolbar.title = "Profiles"
                isShow = true
            } else if (isShow){
                profileBinding.collapsingToolbar.title = " "
                isShow = false
            }
        })




        profileBinding.fabbtnAddProfile.setOnClickListener {
            val i = Intent(this@ProfileIntentActivity, AddProfileIntentActivity::class.java)
            startActivityForResult(i,1)

        }


        profileBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(profileBinding.edtSearch.text.toString().isEmpty()){
                    //profileBinding.edtSearch.clearFocus()
                    dataArrayList = getArrayList(this@ProfileIntentActivity)
                    setRecyclerView(dataArrayList)
                    setAutoCompleteTextView(dataArrayList)
                }

            }
        })


        profileBinding.edtSearch.setOnItemClickListener { adapterView, view, i, l ->

            val nameSelected:String = adapterView.getItemAtPosition(i).toString()
            val newarray = arrayListOf<ProfileIntentModel>()
            for(i in dataArrayList){
                if(i.name == nameSelected){
                    newarray.add(i)
                }
            }

            setRecyclerView(newarray)
        }


    }

    private fun setRecyclerView(data:ArrayList<ProfileIntentModel>) {
        profileBinding.rvProfiles.layoutManager = LinearLayoutManager(this)
        profileBinding.rvProfiles.adapter = ProfileIntentAdapter(this,data)
    }

    private fun setAutoCompleteTextView(data : ArrayList<ProfileIntentModel>) {
        val arrayNames = arrayListOf<String>()
        for (i in data){
            if(!arrayNames.contains(i.name)){
                i.name?.let { arrayNames.add(it) }
            }
        }
        val arrayAdapter:ArrayAdapter<String>  = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayNames)
        arrayAdapter.notifyDataSetChanged()
        profileBinding.edtSearch.setAdapter(arrayAdapter)
    }

    private fun getArrayList(context: Context) :ArrayList<ProfileIntentModel>{
        val arrayList:ArrayList<ProfileIntentModel> = arrayListOf()

        for (i in dataArrayList) {
            if(i.name==profileBinding.edtSearch.text.toString()){
                arrayList.add(i)
            }
        }
        return arrayList
    }

    override fun onResume() {
        super.onResume()

        setAutoCompleteTextView(dataArrayList)

        var newarray = arrayListOf<ProfileIntentModel>()
        for(i in dataArrayList){
            if(i.name == profileBinding.edtSearch.text.toString()){
                newarray.add(i)
            }
        }

        if(newarray.isEmpty()){
            newarray = dataArrayList
        }
        setRecyclerView(newarray)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === RESULT_OK) {

                profile = ProfileIntentModel(
                    data?.getStringExtra("id"),
                    data?.getStringExtra("name"),
                    data?.getStringExtra("email"),
                    data?.getStringExtra("mobile"),
                    data?.getStringExtra("password"),
                    data?.getStringExtra("confpassword"),
                    data?.getStringExtra("dob"),
                    data?.getStringExtra("gender"),
                    data?.getStringExtra("hobbies")
                )

                dataArrayList.add(profile)


            }
        }


    }

//    private fun getObjectwithkey(key:String,context:Context) : ProfileModel {
//        sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//        val json = sharedPreferences.getString(key, null)
//
//        return if (json != null) {
//            val gson = Gson()
//            val type = object : TypeToken<ProfileModel>() {}.type
//            gson.fromJson(json, type)
//        } else {
//            ProfileModel()
//        }
//
//    }


//    override fun onResume() {
//        super.onResume()
//
//        data = getArrayList(this)
//        //setRecyclerView(data)
//        setAutoCompleteTextView(data)
//
//        var newarray = arrayListOf<ProfileModel>()
//        for(i in data){
//            if(i.name == profileBinding.edtSearch.text.toString()){
//                newarray.add(i)
//            }
//        }
//
//        if(newarray.isEmpty()){
//            newarray = data
//        }
//        setRecyclerView(newarray)
//
//    }

    fun updateData() {
        onResume()
    }


}