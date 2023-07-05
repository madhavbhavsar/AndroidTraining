package com.mad.androidtraining.july4ProfileFragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mad.androidtraining.july4ProfileFragment.fragments.AddProfileFragment
import com.mad.androidtraining.july4ProfileFragment.fragments.ProfileListFragment
import com.mad.androidtraining.june21fragments.fragments.HomeFragment
import com.mad.androidtraining.june21fragments.fragments.NotificationFragment
import com.mad.androidtraining.june21fragments.fragments.ProfileFragment

class ProfileViewPagerAdapter(fa:FragmentActivity, private val list:List<String>) :
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ProfileListFragment()
            1 -> return AddProfileFragment()

        }
        return ProfileListFragment()
    }


}