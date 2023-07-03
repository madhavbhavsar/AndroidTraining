package com.mad.androidtraining.june20lifecycle

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mad.androidtraining.R


class LifeCycleActivity : AppCompatActivity() {

    lateinit var txtLogs:TextView
    lateinit var alert11: AlertDialog
    var str:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        Log.i("Activity"," MainActivity onCreate()")
        
//        txtLogs= findViewById<TextView>(R.id.txtLogs)
//        str += intent.getStringExtra("Logs").toString()
//        txtLogs.text =str
//
//        str += "\nLogs: OnCreate() "
//        txtLogs.text =str


        val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
        builder1.setMessage("Press YES to Go Back.")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener { dialog, id ->
//                str += "\nLogs: OnBackPressed() Yes"
//                txtLogs.text =str
                Log.i("Activity"," MainActivity onBackPressed() Yes")
                
                dialog.cancel()
                super.onBackPressed()

            })

        builder1.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialog, id ->
//                str += "\nLogs: OnBackPressed() No"
//                txtLogs.text =str
                Log.i("Activity"," MainActivity onBackPressed() No")
                dialog.cancel() })

        alert11 = builder1.create()



        val btnSecondActivity = findViewById<Button>(R.id.btnSecondActivity)
        btnSecondActivity.setOnClickListener {
            val i = Intent(this,LifeCycleSecondActivity::class.java)
            i.putExtra("Logs",str)
            startActivity(i)
        }

    }

    override fun onBackPressed() {
//        str += "\nLogs: OnBackPressed() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onBackPressed()")
        alert11.show()

    }

    override fun onStart() {
//        str += "\nLogs: OnStart() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onStart()")
        super.onStart()

    }

    override fun onResume() {
//        str += "\nLogs: OnResume() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onResume()")
        super.onResume()
    }

    override fun onRestart() {
//        str += "\nLogs: OnRestart() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onRestart()")
        super.onRestart()
    }

    override fun onPause() {
//        str += "\nLogs: OnPause() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onPause()")
        super.onPause()
    }

    override fun onStop() {
//        str += "\nLogs: OnStop() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onStop()")
        super.onStop()
    }

    override fun onDestroy() {
//        str += "\nLogs: OnDestroy() "
//        txtLogs.text =str
        Log.i("Activity"," MainActivity onDestroy()")
        super.onDestroy()
    }
}