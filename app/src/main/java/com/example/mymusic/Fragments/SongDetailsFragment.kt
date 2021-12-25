package com.example.mymusic.Fragments


import android.net.Uri
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mymusic.R
import com.example.mymusic.db.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_dance_song.view.*
import kotlinx.android.synthetic.main.fragment_song_details.*
import kotlinx.android.synthetic.main.fragment_song_details.view.*
import kotlinx.android.synthetic.main.song_item.view.*

/**
 * A simple [Fragment] subclass.
 */
class SongDetailsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root=inflater.inflate(R.layout.fragment_song_details, container, false)


        val b = arguments
        if (b != null) {
            val songName=b.getString("songName")
            val songAdder=b.getString("songAdder")
            val songType=b.getString("songType")
            val songImage=b.getString("songImage")
            val songWord=b.getString("songWord")

            root.details_song_name.text=songName
            root.details_song_adder_name.text=songAdder
            root.details_song_type.text=songType
            root.details_song_words.text = songWord
            root.details_song_image.setImageURI(Uri.parse(songImage))
        }
        val getcounter=b!!.getInt("counter")
        var counter=getcounter
        var isFavorite:String
        root.details_favorite.setOnClickListener {
            val  db= DatabaseHelper(activity!!)
                counter+=1
            if (counter%2!=0){
                isFavorite="true"
                root.details_favorite.setImageResource(R.drawable.ic_favorite_red)
                db.favorite(b.getInt("id"),isFavorite,counter)
            }
            else {
                isFavorite="false"
                root.details_favorite.setImageResource(R.drawable.ic_favorite_border)
                db.favorite(b.getInt("id"),isFavorite,counter)
            }
            b.putInt("counter",counter)
        }
        if (counter%2!=0)
            root.details_favorite.setImageResource(R.drawable.ic_favorite_red)
        else
            root.details_favorite.setImageResource(R.drawable.ic_favorite_border)


        root.back_to_type.setOnClickListener {
            if (root.details_song_type.text == "Romantic")
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, RomanticSongFragment()).commit()
            if (root.details_song_type.text=="Tragedy")
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, TragedySongFragment()).commit()
            if (root.details_song_type.text=="National")
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, NationalSongFragment()).commit()
            if (root.details_song_type.text=="Dance")
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, DanceSongFragment()).commit()


        }

        root.edit_song.setOnClickListener {
            val fragment =   EditSongFragment()
            val n=Bundle()
            n.putInt("id",b.getInt("id"))
            n.putString("songName",root.details_song_name.text.toString())
            n.putString("songAdder",root.details_song_adder_name.text.toString())
            n.putString("songType",root.details_song_type.text.toString())
            n.putString("songImage",root.details_song_image.toString())
            n.putString("songWord",root.details_song_words.text.toString())

            fragment.arguments=n
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
        }

        return root
    }


}
