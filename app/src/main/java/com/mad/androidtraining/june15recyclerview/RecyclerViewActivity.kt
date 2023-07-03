package com.mad.androidtraining.june15recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.june15recyclerview.adapter.RecyclerAdapter
import com.mad.androidtraining.R
import com.mad.androidtraining.june15recyclerview.model.User

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val u1 = User(R.drawable.img_6,"Madhav", "Bhavsar", "madhavbhavsar21@gmail.com", "12312 31232")
        val u2 = User(R.drawable.img_5,"Hello", "World", "helloWorld@gmail.com", "66666 66666")
        val u3 = User(R.drawable.img_4,"Foo", "Bar", "foobar@gmail.com", "78678 96786")
        val u4 = User(R.drawable.img_3,"World", "Space", "space@gmail.com", "99999 89999")

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(listOf(u1,u2,u3,u4,u1,u2,u3,u4,u1))

    }
}