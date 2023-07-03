package com.mad.androidtraining.june20lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.mad.androidtraining.R

class LifeCycleSecondActivity : AppCompatActivity() {

//    lateinit var txtLogs: TextView
//    var str:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_second)
        
        Log.i("Activity"," SecondActivity onCreate()")

//        txtLogs= findViewById<TextView>(R.id.txtLogs2)
//        str += intent.getStringExtra("Logs").toString()
//        txtLogs.text =str
//        str += "\nLogs: Second OnCreate() "
//        txtLogs.text =str

        var btnFirstActivity = findViewById<Button>(R.id.btnFirstActivity)
        btnFirstActivity.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        
    }

    override fun onBackPressed() {
//        str += "\nLogs: Second OnBackPressed() "
//        txtLogs.text =str
        Log.i("Activity"," SecondActivity onBackPressed()")
        super.onBackPressed()
    }

    override fun onStart() {
//        str += "\nLogs: Second OnStart() "
//        txtLogs.text =str
        Log.i("Activity"," SecondActivity onStart()")
        super.onStart()
    }

    override fun onResume() {
//        str += "\nLogs: Second OnResume() "
//        txtLogs.text =str
        Log.i("Activity"," SecondActivity onResume()")
        super.onResume()
    }

    override fun onPause() {
//        str += "\nLogs: Second OnPause() "
//        txtLogs.text =str
        Log.i("Activity"," SecondActivity onPause()")
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity"," SecondActivity onRestart()")
    }

    override fun onStop() {
//        str += "\nLogs: Second OnStop() "
//        txtLogs.text =str
        Log.i("Activity"," SecondActivity onStop()")
        super.onStop()
    }

    override fun onDestroy() {
//        str += "\nLogs: Second OnDestroy() "
//        txtLogs.text =str
        Log.i("Activity"," SecondActivity onDestroy()")
        super.onDestroy()
    }

}