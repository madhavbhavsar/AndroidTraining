package com.mad.androidtraining.june21fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mad.androidtraining.R
import com.mad.androidtraining.june21fragments.adapter.ViewPagerAdapter
import com.mad.androidtraining.june21fragments.interfaces.ChangeViewPagerInterface

class FragmentSecondActivity : AppCompatActivity(), ChangeViewPagerInterface {

    //lateinit var fragmentSecondBinding : ActivityFragmentSecondBinding
    private val listOfTitles = arrayListOf<String>()
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fragmentSecondBinding = ActivityFragmentSecondBinding.inflate(layoutInflater)
        //val view = fragmentSecondBinding.root
        //setContentView(view)

        setContentView(R.layout.activity_fragment_second)

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)


        listOfTitles.add("Home")
        listOfTitles.add("Profile")
        listOfTitles.add("Notification")


        val pagerAdapter = ViewPagerAdapter(this, listOfTitles)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitles[position]
        }.attach()

    }

    override fun changeViewPager(position:Int) {
        viewPager.currentItem  = position
    }

}
