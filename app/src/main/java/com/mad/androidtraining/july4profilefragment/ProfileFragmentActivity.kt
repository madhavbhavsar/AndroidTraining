package com.mad.androidtraining.july4profilefragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityProfileFragmentBinding
import com.mad.androidtraining.july4profilefragment.adapter.ProfileViewPagerAdapter
import com.mad.androidtraining.july4profilefragment.fragments.ProfileListFragment
import com.mad.androidtraining.july4profilefragment.model.ProfileFragmentModel


class ProfileFragmentActivity : AppCompatActivity() {

    lateinit var profileFragmentBinding: ActivityProfileFragmentBinding
    private val listOfTitles = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileFragmentBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_profile_fragment)

        var isShow = true
        var scrollRange = -1
        profileFragmentBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                profileFragmentBinding.collapsingToolbar.title = "Profiles"
                profileFragmentBinding.imgToolbar.visibility = View.VISIBLE
                isShow = true
            } else if (isShow) {
                profileFragmentBinding.collapsingToolbar.title = " "
                profileFragmentBinding.imgToolbar.visibility = View.INVISIBLE
                isShow = false
            }
        })

        Glide.with(this)
            .load("https://cdn.pixabay.com/photo/2017/11/06/13/45/cap-2923682_1280.jpg")
            .placeholder(R.drawable.ic_loading_spinner)
            .centerCrop()
            .into(findViewById(R.id.imgProfileImage))

        Glide.with(this)
            .load("https://cdn.pixabay.com/photo/2017/11/06/13/45/cap-2923682_1280.jpg")
            .placeholder(R.drawable.ic_loading_spinner)
            .centerCrop()
            .into(findViewById(R.id.imgToolbar))


        profileFragmentBinding.imgProfileImage.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }


        listOfTitles.add("Profile List")
        listOfTitles.add("Add/Edit")


        val pagerAdapter = ProfileViewPagerAdapter(this, listOfTitles)
        profileFragmentBinding.vpProfiles.adapter = pagerAdapter

        TabLayoutMediator(
            profileFragmentBinding.tlProfileTabsList,
            profileFragmentBinding.vpProfiles
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitles[position]
        }.attach()


    }


    fun getPathFromURI(contentUri: Uri?): String {
        var res: String = ""
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        Log.i("imgggg", "res  " + res)
        return res
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String?
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentURI, filePathColumn, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(filePathColumn[0])
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        try {
//            if (resultCode == RESULT_OK) {
//                if (requestCode == 3) {
//                    var selectedImageUri: Uri? = data?.data
//                    // Get the path from the Uri
//                    val path: String = getPathFromURI(selectedImageUri)
//                    if (path != null) {
//                        Log.i("imgggg","path null")
//                        val f = File(path)
//                        selectedImageUri = Uri.fromFile(f)
//                    } else {
//                        Log.i("imgggg","path null")
//
//                    }
//                    // Set the image in ImageView
//                    profileFragmentBinding.imgProfileImage.setImageURI(
//                        selectedImageUri
//                    )
//                    profileFragmentBinding.imgToolbar.setImageURI(
//                        selectedImageUri
//                    )
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("FileSelectorActivity", "File select error", e)
//        }
//    }

    private var list: ArrayList<ProfileFragmentModel> = arrayListOf()
    private var dataaa: Bundle? = null
    fun saveData(id: Int, data: Bundle) {
        dataaa = data
    }

    fun getSavedDataBundle():Bundle?{
        return dataaa
    }

    fun getSavedData(): ArrayList<ProfileFragmentModel> {
        if (dataaa?.getString("name") != null) {
            val profile = ProfileFragmentModel(
                dataaa?.getString("name")!!,
                dataaa?.getString("email")!!,
                dataaa?.getString("mobile")!!,
                dataaa?.getString("password")!!,
                dataaa?.getString("confpassword")!!,
                dataaa?.getString("dob")!!,
                dataaa?.getString("gender")!!,
                dataaa?.getString("hobbies")!!
            )

            if(dataaa?.getInt("update")==null || dataaa?.getInt("update")==-1 ){
                list.add(profile)
            } else {
                list[dataaa?.getInt("update")!!] = profile
            }


            dataaa=null
        }

        return list
    }

    fun deleteData(position: Int) {
        list.removeAt(position)
        val pagerAdapter = ProfileViewPagerAdapter(this, listOfTitles)
        profileFragmentBinding.vpProfiles.adapter = pagerAdapter

        TabLayoutMediator(
            profileFragmentBinding.tlProfileTabsList,
            profileFragmentBinding.vpProfiles
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitles[position]
        }.attach()
    }

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data

                profileFragmentBinding.imgProfileImage.setImageURI(imgUri)
                profileFragmentBinding.imgProfileImage.adjustViewBounds = true
                profileFragmentBinding.imgToolbar.setImageURI(imgUri)
            }
        }
}