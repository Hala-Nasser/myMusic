package com.example.mymusic.Fragments


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
import kotlinx.android.synthetic.main.fragment_dance_song.*
import kotlinx.android.synthetic.main.fragment_dance_song.view.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.song_item.view.*


/**
 * A simple [Fragment] subclass.
 */
class DanceSongFragment : Fragment(), SongAdapter.onSongItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root=inflater.inflate(R.layout.fragment_dance_song, container, false)

        val db= DatabaseHelper(activity!!)
        val data=db.getDanceSongs()
        val songAdapter = SongAdapter(activity!!,data,this)
        root.rvDanceSong.layoutManager = GridLayoutManager(activity!!,2)
        root.rvDanceSong.setHasFixedSize(true)
        root.rvDanceSong.adapter=songAdapter






        root.back_to_home.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,HomeFragment()).commit()
        }

        return root
    }

    override fun onItemClick(data: Song, position: Int) {
        val fragment =   SongDetailsFragment()
        val b=Bundle()
        b.putInt("id",data.id)
        b.putString("songName",data.songName.toString())
        b.putString("songType",data.songType.toString())
        b.putString("songAdder",data.songAdder.toString())
        b.putString("songImage",data.songImage.toString())
        b.putString("songWord",data.songWord.toString())
        b.putString("isFavorite",data.isFavorite.toString())
        b.putInt("counter",data.counter)

        fragment.arguments=b


        activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,fragment).commit()
    }


}
