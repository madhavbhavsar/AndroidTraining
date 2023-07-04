package com.mad.androidtraining.june30Profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityProfileBinding
import com.mad.androidtraining.june30Profile.adapter.ProfileAdapter
import com.mad.androidtraining.june30Profile.model.ProfileModel


class ProfileActivity : AppCompatActivity() {

    var data : ArrayList<ProfileModel> = arrayListOf()
    private lateinit var profileBinding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_profile)

        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
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
                profileBinding.collapsingToolbar.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })




        profileBinding.fabbtnAddProfile.setOnClickListener {
            val i = Intent(this@ProfileActivity,AddProfileActivity::class.java)
            startActivity(i)
        }


        profileBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(profileBinding.edtSearch.text.toString().isEmpty()){
                    //profileBinding.edtSearch.clearFocus()
                    data = getArrayList(this@ProfileActivity)
                    setRecyclerView(data)
                    setAutoCompleteTextView(data)
                }

            }
        })


        profileBinding.edtSearch.setOnItemClickListener { adapterView, view, i, l ->

            val nameSelected:String = adapterView.getItemAtPosition(i).toString()
            val newarray = arrayListOf<ProfileModel>()
            for(i in data){
                if(i.name == nameSelected){
                    newarray.add(i)
                }
            }

            setRecyclerView(newarray)
        }


    }

    private fun setRecyclerView(data:ArrayList<ProfileModel>) {
        profileBinding.rvProfiles.layoutManager = LinearLayoutManager(this)
        profileBinding.rvProfiles.adapter = ProfileAdapter(this,data)
    }

    private fun setAutoCompleteTextView(data : ArrayList<ProfileModel>) {
        val arrayNames = arrayListOf<String>()
        for (i in data){
            if(!arrayNames.contains(i.name)){
                arrayNames.add(i.name)
            }
        }
        val arrayAdapter:ArrayAdapter<String>  = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayNames)
        arrayAdapter.notifyDataSetChanged()
        profileBinding.edtSearch.setAdapter(arrayAdapter)
    }

    private fun getArrayList(context: Context) :ArrayList<ProfileModel>{
        val arrayList:ArrayList<ProfileModel> = arrayListOf()

        sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        val allEntries: Map<String, *> = sharedPreferences.all
        for ((key, value) in allEntries) {
           arrayList.add(getObjectwithkey(key,context))
        }
        return arrayList
    }

    private fun getObjectwithkey(key:String,context:Context) : ProfileModel{
        sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(key, null)

        return if (json != null) {
            val gson = Gson()
            val type = object : TypeToken<ProfileModel>() {}.type
            gson.fromJson(json, type)
        } else {
            ProfileModel()
        }

    }


    override fun onResume() {
        super.onResume()

        data = getArrayList(this)
        //setRecyclerView(data)
        setAutoCompleteTextView(data)

        var newarray = arrayListOf<ProfileModel>()
        for(i in data){
            if(i.name == profileBinding.edtSearch.text.toString()){
                newarray.add(i)
            }
        }

        if(newarray.isEmpty()){
            newarray = data
        }
        setRecyclerView(newarray)

    }

    fun updateData() {
        onResume()
    }


}