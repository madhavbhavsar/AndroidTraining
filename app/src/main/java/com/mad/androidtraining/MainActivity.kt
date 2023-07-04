package com.mad.androidtraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mad.androidtraining.cardgame.CardGameActivity
import com.mad.androidtraining.july3Profile.ProfileIntentActivity
import com.mad.androidtraining.june14patterns.PatternsActivity
import com.mad.androidtraining.june15recyclerview.RecyclerViewActivity
import com.mad.androidtraining.june16spotify.SpotifyActivity
import com.mad.androidtraining.june19intent.IntentsActivity
import com.mad.androidtraining.june20lifecycle.LifeCycleActivity
import com.mad.androidtraining.june21fragments.FragmentActivity
import com.mad.androidtraining.june21fragments.FragmentSecondActivity
import com.mad.androidtraining.june28dialogs.DialogsActivity
import com.mad.androidtraining.june30Profile.ProfileActivity
import com.mad.androidtraining.june9instagram.InstagramActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInsta = findViewById<Button>(R.id.btnInsta)
        btnInsta.setOnClickListener {
            val i = Intent(this,InstagramActivity::class.java)
            startActivity(i)
        }

        val btnPattern = findViewById<Button>(R.id.btnPattern)
        btnPattern.setOnClickListener {
            val i = Intent(this,PatternsActivity::class.java)
            startActivity(i)
        }

        val btnRecyclerView = findViewById<Button>(R.id.btnRecycler)
        btnRecyclerView.setOnClickListener {
            val i = Intent(this,RecyclerViewActivity::class.java)
            startActivity(i)
        }

        val btnSpotify = findViewById<Button>(R.id.btnSpotify)
        btnSpotify.setOnClickListener {
            val i = Intent(this,SpotifyActivity::class.java)
            startActivity(i)
        }


        val btnIntent = findViewById<Button>(R.id.btnIntent)
        btnIntent.setOnClickListener {
            val i = Intent(this,IntentsActivity::class.java)
            startActivity(i)
        }

        val btnLifeCycle = findViewById<Button>(R.id.btnLifeCycle)
        btnLifeCycle.setOnClickListener {
            val i = Intent(this,LifeCycleActivity::class.java)
            startActivity(i)
        }

        val btnFragment = findViewById<Button>(R.id.btnFragment)
        btnFragment.setOnClickListener {
            val i = Intent(this,FragmentSecondActivity::class.java)
            startActivity(i)
        }


        val btnCard = findViewById<Button>(R.id.btnCardGame)
        btnCard.setOnClickListener {
            val i = Intent(this,CardGameActivity::class.java)
            startActivity(i)
        }

        val btnDialogs = findViewById<Button>(R.id.btnDialogs)
        btnDialogs.setOnClickListener {
            val i = Intent(this,DialogsActivity::class.java)
            startActivity(i)
        }

        val btnProfile = findViewById<Button>(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val i = Intent(this,ProfileActivity::class.java)
            startActivity(i)
        }

        val btnProfileIntent = findViewById<Button>(R.id.btnProfileIntent)
        btnProfileIntent.setOnClickListener {
            val i = Intent(this, ProfileIntentActivity::class.java)
            startActivity(i)
        }
    }
}