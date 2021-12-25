package com.example.mymusic.Fragments


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.mymusic.R
import com.example.mymusic.SigninActivity
import com.example.mymusic.db.DatabaseHelper
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_edit_song.*
import kotlinx.android.synthetic.main.fragment_edit_song.view.*
import kotlinx.android.synthetic.main.fragment_song_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class EditSongFragment : Fragment() {

    var imageURI: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root=inflater.inflate(R.layout.fragment_edit_song, container, false)

        val n = arguments
        if (n != null) {
            val songName=n.getString("songName")
            val songAdder=n.getString("songAdder")
            val songType=n.getString("songType")
            val songImage=n.getString("songImage")
            var songWord=n.getString("songWord")

            root.edit_song_name.setText(songName)
            root.edit_song_type.text=songType
            root.edit_song_adder_name.text=songAdder
            root.edit_song_words.setText(songWord)
            root.edit_song_image.setImageURI(Uri.parse(songImage))
        }

        root.edit_song_type.setOnClickListener {
            val popup = PopupMenu(activity, edit_song_type)
            popup.menuInflater.inflate(R.menu.type_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.romantic -> edit_song_type.text = "Romantic"
                    R.id.tragedy -> edit_song_type.text = "Tragedy"
                    R.id.national -> edit_song_type.text = "National"
                    R.id.dance -> edit_song_type.text = "Dance"
                }
                true
            }
            popup.show()
        }

        root.edit_upload_song_image.setOnClickListener{
            PickImageDialog.build(PickSetup())
                .setOnPickResult { r ->
                    root.add_song_image.setImageURI(r.uri)
                    imageURI = r.uri.toString()
                }
                .setOnPickCancel {
                    Toast.makeText(activity!!, "Faild", Toast.LENGTH_SHORT).show()
                }.show(activity!!.supportFragmentManager)
        }
        root.check_edit_song.setOnClickListener {

            val newSongName=root.edit_song_name.text.toString()
            val newSongType=root.edit_song_type.text.toString()
            val newSongWord=root.edit_song_words.text.toString()
           // val newSongImage=root.edit_song_image.setImageURI(Uri.parse(imageURI))


            val db= DatabaseHelper(activity!!)
            db.updateSong(n!!.getInt("id"),newSongName,newSongType,newSongWord,imageURI)

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,HomeFragment()).commit()
        }


        root.cancel_edit_song.setOnClickListener {
            root.edit_song_name.setText(n!!.getString("songName"))
            root.edit_song_type.text=n.getString("songType")
            root.edit_song_adder_name.text=n.getString("songAdder")
            root.edit_song_words.setText(n.getString("songWord"))
            root.edit_song_image.setImageURI(Uri.parse(n.getString("songImage")))
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,HomeFragment()).commit()
        }

        root.delete_song.setOnClickListener {

            val alertDialog = AlertDialog.Builder(activity!!)
            alertDialog.setTitle("Delete song")
            alertDialog.setMessage("Are you sure?")
            alertDialog.setCancelable(false)
            alertDialog.setIcon(R.drawable.ic_delete)

            alertDialog.setPositiveButton("Yes") { _, _ ->
                val db= DatabaseHelper(activity!!)
                db.deleteSong(n!!.getInt("id"))
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,HomeFragment()).commit()

            }

            alertDialog.setNegativeButton("No") { _, _  ->
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,HomeFragment()).commit()
            }

            alertDialog.create().show()

        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode==100){
            imageURI = data!!.data.toString()
            Log.e("hzm",imageURI)
            edit_song_image.setImageURI(data.data)
        }
    }


}
