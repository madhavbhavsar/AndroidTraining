package com.mad.androidtraining.june21fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.R
import com.mad.androidtraining.june15recyclerview.adapter.RecyclerAdapter
import com.mad.androidtraining.june15recyclerview.model.User


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        val u1 = User(R.drawable.img_6,"Madhav", "Bhavsar", "madhavbhavsar21@gmail.com", "12312 31232")
        val u2 = User(R.drawable.img_5,"Hello", "World", "helloWorld@gmail.com", "66666 66666")
        val u3 = User(R.drawable.img_4,"Foo", "Bar", "foobar@gmail.com", "78678 96786")
        val u4 = User(R.drawable.img_3,"World", "Space", "space@gmail.com", "99999 89999")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = RecyclerAdapter(listOf(u1,u2,u3,u4,u1,u2,u3,u4,u1))

        return view
    }


}