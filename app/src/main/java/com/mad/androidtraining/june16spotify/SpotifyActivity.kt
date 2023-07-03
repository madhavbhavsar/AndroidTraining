package com.mad.androidtraining.june16spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.R
import com.mad.androidtraining.june16spotify.adapter.AdapterBig
import com.mad.androidtraining.june16spotify.adapter.AdapterCircle
import com.mad.androidtraining.june16spotify.adapter.AdapterSmall
import com.mad.androidtraining.june16spotify.adapter.AdapterSquare
import com.mad.androidtraining.june16spotify.model.ModelBig
import com.mad.androidtraining.june16spotify.model.ModelSmall
import com.mad.androidtraining.june16spotify.model.ModelSquare

class SpotifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spotify)


        val imgUrls = listOf<String>(
            "https://cdn.pixabay.com/photo/2018/06/17/20/35/chain-3481377_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/11/13/06/12/boy-529067_640.jpg",
            "https://cdn.pixabay.com/photo/2016/11/29/12/13/fence-1869401_640.jpg",
            "https://cdn.pixabay.com/photo/2018/08/27/10/11/radio-cassette-3634616_1280.png",
            "https://cdn.pixabay.com/photo/2021/06/03/00/06/sheet-music-6305620_640.jpg",
            "https://cdn.pixabay.com/photo/2022/08/02/10/59/piano-7360086_640.jpg",
            "https://cdn.pixabay.com/photo/2015/05/11/17/47/music-762842_640.jpg",
            "https://cdn.pixabay.com/photo/2017/03/16/18/17/music-2149880_640.jpg",
            "https://cdn.pixabay.com/photo/2019/06/21/20/23/robin-4290332_640.jpg",
            "https://cdn.pixabay.com/photo/2019/10/04/11/23/old-4525345_640.png",
            "https://cdn.pixabay.com/photo/2017/01/06/23/04/nature-1959229_640.jpg",
            "https://cdn.pixabay.com/photo/2014/03/23/20/52/cobblestones-293578_640.jpg"
        )




        val s1 = ModelSmall(imgUrls[4], "Liked Songs")
        val s2 = ModelSmall(imgUrls[5], "English")
        val s3 = ModelSmall(imgUrls[6], "Hindi")
        val s4 = ModelSmall(imgUrls[7], "Kpop")
        val s5 = ModelSmall(imgUrls[8], "NF")
        val s6 = ModelSmall(imgUrls[9], "Songs")

        val recyclerViewSmall: RecyclerView = findViewById(R.id.recyclerViewSmall)
        recyclerViewSmall.layoutManager = GridLayoutManager(this, 2)
        recyclerViewSmall.adapter = AdapterSmall(listOf(s1, s2, s3, s4, s5, s6),this)


        val sq1 = ModelSquare(imgUrls[5], "Indie", "Playlist")
        val sq2 = ModelSquare(imgUrls[6], "Top 100", "Podcast")
        val sq3 = ModelSquare(imgUrls[7], "Global", "Shows")
        val sq4 = ModelSquare(imgUrls[8], "English", "Open Mic")
        val sq5 = ModelSquare(imgUrls[9], "Hindi", "Songs")
        val sq6 = ModelSquare(imgUrls[0], "Others", "Hype")
        val sq7 = ModelSquare(imgUrls[1], "Mix", "Liked")
        val sq8 = ModelSquare(imgUrls[2], "Mix2", "Follow")

        val recyclerViewSquare: RecyclerView = findViewById(R.id.recyclerViewSquare)
        recyclerViewSquare.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSquare.adapter = AdapterSquare(listOf(sq1, sq2, sq3, sq4, sq5, sq6, sq7, sq8),this)


        val c1 = ModelSmall(imgUrls[10], "Hello")
        val c2 = ModelSmall(imgUrls[11], "World")
        val c3 = ModelSmall(imgUrls[0], "Space")
        val c4 = ModelSmall(imgUrls[1], "Alien")
        val c5 = ModelSmall(imgUrls[2], "Galaxy")
        val c6 = ModelSmall(imgUrls[3], "Stars")

        val recyclerViewCircle: RecyclerView = findViewById(R.id.recyclerViewCircle)
        recyclerViewCircle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCircle.adapter = AdapterCircle(listOf(c1, c2, c3, c4, c5, c6), this)


        val b1 = ModelBig(
            imgUrls[0],
            imgUrls[10],
            "Hip Hop Mix",
            "This is JVKE. The essential tracks"
        )
        val b2 = ModelBig(imgUrls[1], imgUrls[11], "Ashlyn", "This is Conan Gray")
        val b3 =
            ModelBig(imgUrls[2], imgUrls[9], "VISIONS", "Album The Neighbourhood")
        val b4 =
            ModelBig(imgUrls[3],imgUrls[8], "Are You Scared Yet", "EP Rory Webley")

        val recyclerViewBig: RecyclerView = findViewById(R.id.recyclerViewBig)
        //recyclerViewBig.isNestedScrollingEnabled = false
        recyclerViewBig.layoutManager = LinearLayoutManager(this)
        recyclerViewBig.adapter = AdapterBig(listOf(b1, b2, b3, b4), this)


    }
}