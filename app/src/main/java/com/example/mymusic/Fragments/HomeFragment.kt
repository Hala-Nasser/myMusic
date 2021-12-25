package com.example.mymusic.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mymusic.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root=inflater.inflate(R.layout.fragment_home, container, false)

        root.romantic_songs.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, RomanticSongFragment()).addToBackStack(null).commit()
        }
        root.tragedy_songs.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, TragedySongFragment()).addToBackStack(null).commit()
        }
        root.national_songs.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, NationalSongFragment()).addToBackStack(null).commit()
        }
        root.dance_songs.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, DanceSongFragment()).addToBackStack(null).commit()
        }


        return root
    }


}
