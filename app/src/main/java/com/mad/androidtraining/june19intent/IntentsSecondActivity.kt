package com.mad.androidtraining.june19intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mad.androidtraining.R

class IntentsSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_intents)

        val txtView = findViewById<TextView>(R.id.txtView)
        val setString:String = "Email - "+ intent.getStringExtra("Email")+
                "\nMobile No - "+ intent.getStringExtra("Mobile")+
                "\nWebsite - "+ intent.getStringExtra("Site")

        txtView.text = setString

    }
}