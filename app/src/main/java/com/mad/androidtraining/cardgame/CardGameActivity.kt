package com.mad.androidtraining.cardgame

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.ActivityCardGameBinding


class CardGameActivity : AppCompatActivity() {

    lateinit var cardGameBinding: ActivityCardGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_card_game)
        cardGameBinding = ActivityCardGameBinding.inflate(layoutInflater)
        val view = cardGameBinding.root
        setContentView(view)


        var cards = listOf(
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/other/png_96_dpi/spades_ace_simple.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/spades_king.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/spades_queen.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/spades_jack.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/hearts_ace.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/joker_red.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/hearts_king.png",
            "https://tekeye.uk/playing_cards/images/svg_playing_cards/fronts/png_96_dpi/diamonds_king.png"
        )

        var i = 0
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                if (i < 5) {
                    Log.d("LOG", "i: $i")
                    val rnds = (0..cards.size-1).random()
                    Glide
                        .with(this@CardGameActivity)
                       .load(cards[rnds])
                       .centerCrop()
                       .placeholder(R.drawable.ic_loading_spinner)
                       .into(cardGameBinding.imgCards)


                    i++
                } else handler.removeCallbacks(this)
                handler.postDelayed(this, 200)
            }
        }
        //Start Handler
        //Start Handler
//        val th = Thread {
//            try {
//                for (i in 0..9) {
//                    Thread.sleep(1000)
//                    Log.d("LOG", "i: $i")
//                    Toast.makeText(this@CardGameActivity,i,Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: Exception) {
//            }
//        }
        cardGameBinding.btnShuffle.setOnClickListener {

            handler.postDelayed(runnable, 200)

           // th.start()
        }

        //cardGameBinding.btnShuffle.setOnClickListener {
//            val sharedOptions = RequestOptions().placeholder(R.drawable.ic_loading_spinner).centerCrop()
//
//
//            for (i in 1..5){
//
//                Timer().schedule(object : TimerTask() {
//                    override fun run() {
//
//
//                    }
//                }, 2000)
//
////                Handler().postDelayed({
////                    //doSomethingHere
////
//////
////                }, 1000)
//            }
//        }


    }
}