package com.mad.androidtraining.july4ProfileFragment.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityProfileIntentBinding
import com.mad.androidtraining.databinding.FragmentAddProfileListBinding
import com.mad.androidtraining.july3ProfileIntent.adapter.ProfileIntentAdapter
import com.mad.androidtraining.july3ProfileIntent.model.ProfileIntentModel
import com.mad.androidtraining.july4ProfileFragment.adapter.ProfileFragmentAdapter
import com.mad.androidtraining.july4ProfileFragment.interfaces.SendProfileData
import com.mad.androidtraining.july4ProfileFragment.model.ProfileFragmentModel

class ProfileListFragment : Fragment(), SendProfileData {

    var dataArrayList : ArrayList<ProfileFragmentModel> = arrayListOf()
    private lateinit var profileListBinding: FragmentAddProfileListBinding
    lateinit var profile :ProfileFragmentModel


    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_profile_list , container, false);

        profileListBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(profileListBinding.edtSearch.text.toString().isEmpty()){
                    //profileBinding.edtSearch.clearFocus()
                    dataArrayList = getArrayList()
                    setRecyclerView(dataArrayList)
                    setAutoCompleteTextView(dataArrayList)
                }

            }
        })




        return profileListBinding.root
    }
    private fun setRecyclerView(data:ArrayList<ProfileFragmentModel>) {
        profileListBinding.rvProfiles.layoutManager = LinearLayoutManager(context)
        profileListBinding.rvProfiles.adapter = context?.let { ProfileFragmentAdapter(it,data) }
    }

    private fun setAutoCompleteTextView(data : ArrayList<ProfileFragmentModel>) {
        val arrayNames = arrayListOf<String>()
        for (i in data){
            if(!arrayNames.contains(i.name)){
                i.name?.let { arrayNames.add(it) }
            }
        }
        val arrayAdapter: ArrayAdapter<String>? = activity?.let { ArrayAdapter<String>(it,android.R.layout.simple_spinner_dropdown_item,arrayNames) }
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged()
        }
        profileListBinding.edtSearch.setAdapter(arrayAdapter)
    }

    private fun getArrayList() :ArrayList<ProfileFragmentModel>{
        val arrayList:ArrayList<ProfileFragmentModel> = arrayListOf()

        for (i in dataArrayList) {
            if(i.name==profileListBinding.edtSearch.text.toString()){
                arrayList.add(i)
            }
        }
        return arrayList
    }

    override fun onResume() {
        super.onResume()


        setAutoCompleteTextView(dataArrayList)

        var newarray = arrayListOf<ProfileFragmentModel>()
        for(i in dataArrayList){
            if(i.name == profileListBinding.edtSearch.text.toString()){
                newarray.add(i)
            }
        }

        if(newarray.isEmpty()){
            newarray = dataArrayList
        }
        setRecyclerView(newarray)

    }



    fun updateData() {
        onResume()
    }


    override fun sendData(profile: ProfileFragmentModel) {
        super.sendData(profile)
        dataArrayList.add(profile)
        Log.i("helloworld",dataArrayList.toString())
        onCreate()
    }

}