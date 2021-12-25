package com.example.mymusic.Adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mymusic.Fragments.SongDetailsFragment
import com.example.mymusic.R
import com.example.mymusic.SigninActivity
import com.example.mymusic.model.Song
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.song_item.view.*
import java.net.URI
import kotlin.math.log

class SongAdapter(var activity: Activity, var data: ArrayList<Song>, var clickListener: onSongItemClickListener) :
    RecyclerView.Adapter<SongAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val song_image=itemView.song_image
        val song_name=itemView.song_name
        val song_adder=itemView.song_adder


        fun initialize(data: Song, action:onSongItemClickListener){
            song_image.setImageURI(Uri.parse(data.songImage))
            song_name.text=data.songName
            song_adder.text=data.songAdder


            itemView.setOnClickListener {
                action.onItemClick(data,adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.song_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       //holder.song_image.setImageURI(Uri.parse(data[position].songImage))
        //holder.song_name.text=data[position].songName
       // holder.song_adder.text=data[position].songAdder
        holder.initialize(data[position], clickListener)
    }

    interface onSongItemClickListener{
        fun onItemClick(data:Song, position: Int)
    }
    }
