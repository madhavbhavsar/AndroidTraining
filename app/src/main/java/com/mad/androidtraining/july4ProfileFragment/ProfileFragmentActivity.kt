package com.mad.androidtraining.july4ProfileFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityProfileFragmentBinding
import com.mad.androidtraining.july4ProfileFragment.adapter.ProfileViewPagerAdapter
import com.mad.androidtraining.june21fragments.adapter.ViewPagerAdapter

class ProfileFragmentActivity : AppCompatActivity() {

    lateinit var profileFragmentBinding: ActivityProfileFragmentBinding
    private val listOfTitles = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileFragmentBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile_fragment)

        var isShow = true
        var scrollRange = -1
        profileFragmentBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                profileFragmentBinding.collapsingToolbar.title = "Profiles"
                profileFragmentBinding.imgToolbar.visibility = View.VISIBLE
                isShow = true
            } else if (isShow){
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


        listOfTitles.add("Profile List")
        listOfTitles.add("Add/Edit")


        val pagerAdapter = ProfileViewPagerAdapter(this, listOfTitles)
        profileFragmentBinding.vpProfiles.adapter = pagerAdapter

        TabLayoutMediator(profileFragmentBinding.tlProfileTabsList, profileFragmentBinding.vpProfiles) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitles[position]
        }.attach()


    }


}