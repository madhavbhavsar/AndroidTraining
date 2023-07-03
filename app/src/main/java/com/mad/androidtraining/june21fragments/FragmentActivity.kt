package com.mad.androidtraining.june21fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mad.androidtraining.R

class FragmentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        Log.i("TAGFragment", "Activity1 onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.i("TAGFragment", "Activity1 onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("TAGFragment", "Activity1 onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.i("TAGFragment", "Activity1 onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i("TAGFragment", "Activity1 onStop")

    }

    override fun onRestart() {
        super.onRestart()
            Log.i("TAGFragment", "Activity1 onRestart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAGFragment", "Activity1 onDestroy")

    }
}