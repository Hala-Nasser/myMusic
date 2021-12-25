package com.example.mymusic.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymusic.Adapter.SongAdapter

import com.example.mymusic.R
import com.example.mymusic.db.DatabaseHelper
import com.example.mymusic.model.Song
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), SongAdapter.onSongItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root=inflater.inflate(R.layout.fragment_search, container, false)


        root.search_icon.setOnClickListener {
            val db= DatabaseHelper(activity!!)
            val searchStatement=root.search.text.toString()
            val data= db.search(searchStatement)
            val songAdapter = SongAdapter(activity!!,data,this)
            root.rv_search_Song.layoutManager = GridLayoutManager(activity!!,2)
            root.rv_search_Song.setHasFixedSize(true)
            root.rv_search_Song.adapter=songAdapter
        }


        return root
    }
    override fun onItemClick(data: Song, position: Int) {
        Toast.makeText(activity,data.songName, Toast.LENGTH_SHORT).show()
    }

}
