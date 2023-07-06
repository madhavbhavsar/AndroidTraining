package com.mad.androidtraining.june21fragments.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mad.androidtraining.R
import com.mad.androidtraining.june21fragments.FragmentSecondActivity
import com.mad.androidtraining.june21fragments.interfaces.ChangeViewPagerInterface


class ProfileFragment:Fragment(),ChangeViewPagerInterface {

    lateinit var btnNoti : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnNotification = view.findViewById<Button>(R.id.btnGotoNotification)

        btnNotification.setOnClickListener {



            val fragmentSecondActivity = requireActivity() as FragmentSecondActivity
            fragmentSecondActivity.viewPager.currentItem = 2
        }

        return view
    }

    override fun changeViewPager(position: Int) {

    }


}