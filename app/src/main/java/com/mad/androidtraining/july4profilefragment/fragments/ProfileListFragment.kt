package com.mad.androidtraining.july4profilefragment.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.FragmentAddProfileListBinding
import com.mad.androidtraining.july4profilefragment.ProfileFragmentActivity
import com.mad.androidtraining.july4profilefragment.adapter.ProfileFragmentAdapter
import com.mad.androidtraining.july4profilefragment.model.ProfileFragmentModel

class ProfileListFragment : Fragment() {

    var dataArrayList: ArrayList<ProfileFragmentModel> = arrayListOf()
    private lateinit var profileListBinding: FragmentAddProfileListBinding
    lateinit var profile: ProfileFragmentModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_profile_list, container, false);

        Log.i("fragmenttt", "oncreateview")
        Log.i("fragmenttt", "a "+arguments?.getString("name"))

        profileListBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (profileListBinding.edtSearch.text.toString().isEmpty()) {
                    //profileBinding.edtSearch.clearFocus()
                    dataArrayList = getArrayList()
                    setRecyclerView(dataArrayList)
                    setAutoCompleteTextView(dataArrayList)
                }

            }
        })




        return profileListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("fragmenttt", "veiw created")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("fragmenttt", "oncreate")

    }


    private fun setRecyclerView(data: ArrayList<ProfileFragmentModel>) {
        profileListBinding.rvProfiles.layoutManager = LinearLayoutManager(context)
        profileListBinding.rvProfiles.adapter = context?.let { ProfileFragmentAdapter(it, data) }
    }

    private fun setAutoCompleteTextView(data: ArrayList<ProfileFragmentModel>) {
        val arrayNames = arrayListOf<String>()
        for (i in data) {
            if (!arrayNames.contains(i.name)) {
                i.name?.let { arrayNames.add(it) }
            }
        }
        val arrayAdapter: ArrayAdapter<String>? = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                arrayNames
            )
        }
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged()
        }
        profileListBinding.edtSearch.setAdapter(arrayAdapter)
    }

    private fun getArrayList(): ArrayList<ProfileFragmentModel> {
        val arrayList: ArrayList<ProfileFragmentModel> = arrayListOf()

        for (i in dataArrayList) {
            if (i.name == profileListBinding.edtSearch.text.toString()) {
                arrayList.add(i)
            }
        }
        return arrayList
    }

//    override fun onResume() {
//        super.onResume()
//
//
//        setAutoCompleteTextView(dataArrayList)
//
//        var newarray = arrayListOf<ProfileFragmentModel>()
//        for(i in dataArrayList){
//            if(i.name == profileListBinding.edtSearch.text.toString()){
//                newarray.add(i)
//            }
//        }
//
//        if(newarray.isEmpty()){
//            newarray = dataArrayList
//        }
//        setRecyclerView(newarray)
//
//    }


    override fun onResume() {
        super.onResume()


        val aa  = arguments
        Log.i("fragmenttt","resume")

        if (aa != null) {
            Log.i("fragmenttt","get "+aa.getString("name"))
        } else {
            Log.i("fragmenttt","else")

        }
        val activity = requireActivity() as ProfileFragmentActivity
        dataArrayList = activity.getSavedData()

//        if(bundle?.getString("name") != null){
//            val profile = ProfileFragmentModel(
//                bundle?.getString("name")!!,
//                bundle?.getString("email")!!,
//                bundle?.getString("mobile")!!,
//                bundle?.getString("password")!!,
//                bundle?.getString("confpassword")!!,
//                bundle?.getString("dob")!!,
//                bundle?.getString("gender")!!,
//                bundle?.getString("hobbies")!!
//            )

//            dataArrayList.add(profile)
        profileListBinding.rvProfiles.layoutManager = LinearLayoutManager(requireActivity())
        profileListBinding.rvProfiles.adapter =
            ProfileFragmentAdapter(requireActivity(), dataArrayList)
        Log.i("fragmenttt", "resume2 ")
        setRecyclerView(dataArrayList)
    }

    fun updateData() {
        onResume()
    }

}

//    override fun onResume() {
//        super.onResume()
//        val bundle = arguments
//        Log.i("fragmenttt", "resume1 "+bundle?.getString("name"))
//
//        if(bundle?.getString("name")!=null){
//            val profile = ProfileFragmentModel(
//                bundle.getString("name")!!,
//                bundle.getString("email")!!,
//                bundle.getString("mobile")!!,
//                bundle.getString("password")!!,
//                bundle.getString("confpassword")!!,
//                bundle.getString("dob")!!,
//                bundle.getString("gender")!!,
//                bundle.getString("hobbies")!!,
//            )
//            dataArrayList.add(profile)
//            profileListBinding.rvProfiles.adapter = ProfileFragmentAdapter(requireActivity(),dataArrayList)
//            Log.i("fragmenttt", "resume2 ")
//            setRecyclerView(dataArrayList)
//            Log.i("fragmenttt", dataArrayList.toString())
//        }
//
//
//    }




//    override fun sendData(profile: ProfileFragmentModel) {
//        super.sendData(profile)
//        dataArrayList.add(profile)
//        //setRecyclerView(dataArrayList)
//        Log.i("fragmenttt", "send data frag " + dataArrayList.toString())
//    }

