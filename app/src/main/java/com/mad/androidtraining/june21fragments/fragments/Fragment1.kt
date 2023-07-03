package com.mad.androidtraining.june21fragments.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mad.androidtraining.R


class Fragment1 : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("TAGFragment", "Fragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TAGFragment", "Fragment onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("TAGFragment", "Fragment onCreateView")


        return inflater.inflate(R.layout.fragment_1,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TAGFragment", "Fragment onViewCreated")

    }

    override fun onStart() {
        super.onStart()
        Log.i("TAGFragment", "Fragment onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("TAGFragment", "Fragment onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.i("TAGFragment", "Fragment onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i("TAGFragment", "Fragment onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("TAGFragment", "Fragment onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAGFragment", "Fragment onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("TAGFragment", "Fragment onDetach")

    }




}