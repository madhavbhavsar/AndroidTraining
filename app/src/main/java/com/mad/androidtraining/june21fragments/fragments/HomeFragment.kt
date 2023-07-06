package com.mad.androidtraining.june21fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mad.androidtraining.R


class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmet_home, container, false)


        val btnFrag1 = view.findViewById<Button>(R.id.btnFrag1)
        val btnFrag2 = view.findViewById<Button>(R.id.btnFrag2)

        val fragmentManager = childFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val defaultFragment = DefaultFragment()
        fragmentTransaction.add(R.id.fragmentContainerView, defaultFragment)
        fragmentTransaction.commit()


        btnFrag1.setOnClickListener {

            val fragManager = childFragmentManager
            val transaction = fragManager.beginTransaction()
            transaction.replace(
                R.id.fragmentContainerView,
                Fragment1()
            )
            transaction.addToBackStack(null)
            transaction.commit()

        }

        btnFrag2.setOnClickListener {
            val fragManager =childFragmentManager
            val transaction = fragManager.beginTransaction()
            transaction.replace(
                R.id.fragmentContainerView,
                Fragment2()
            )

            transaction.addToBackStack(null)
            transaction.commit()

        }


        return view
    }

    override fun onDestroyView() {
//        val fragmentManager = childFragmentManager
//        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
//        val defaultFragment = DefaultFragment()
//        fragmentTransaction.remove(defaultFragment);
//        //fragmentTransaction.add(R.id.fragmentContainerView, defaultFragment)
//        fragmentTransaction.commit()

//        try {
//            val transaction: FragmentTransaction = childFragmentManager
//                .beginTransaction()
//            transaction.remove(Fragment1())
//            transaction.remove(Fragment2())
//            transaction.commit()
//        } catch (e: Exception) {
//        }
        super.onDestroyView()
    }

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        val fragmentManager = requireActivity().supportFragmentManager
////        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
////        val defaultFragment = DefaultFragment()
////        fragmentTransaction.add(R.id.fragmentContainerView, defaultFragment)
////        fragmentTransaction.commit()
//
//
//    }
}
