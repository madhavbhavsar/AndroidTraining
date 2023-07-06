package com.mad.androidtraining.july4profilefragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mad.androidtraining.july4profilefragment.fragments.AddProfileFragment
import com.mad.androidtraining.july4profilefragment.fragments.ProfileListFragment

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