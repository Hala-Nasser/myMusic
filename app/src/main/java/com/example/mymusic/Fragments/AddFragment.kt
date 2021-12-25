package com.example.mymusic.Fragments


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.net.toUri

import com.example.mymusic.R
import com.example.mymusic.db.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.song_item.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    var imageURI: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_add, container, false)


        val sharedPref = activity!!.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val getFullName = sharedPref.getString("fullName", "")
        val db = DatabaseHelper(activity!!)


        root.add_song_adder_name.setText(getFullName)

        root.add_song_type.setOnClickListener {
            val popup = PopupMenu(activity, add_song_type)
            popup.menuInflater.inflate(R.menu.type_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.romantic -> add_song_type.setText("Romantic")
                    R.id.tragedy -> add_song_type.setText("Tragedy")
                    R.id.national -> add_song_type.setText("National")
                    R.id.dance -> add_song_type.setText("Dance")
                }
                true
            }
            popup.show()
        }

        root.add_song_image.setOnClickListener {
            PickImageDialog.build(PickSetup())
                .setOnPickResult { r ->
                    root.add_song_image.setImageURI(r.uri)
                    imageURI = r.uri.toString()
                }
                .setOnPickCancel {
                    Toast.makeText(activity!!, "Faild", Toast.LENGTH_SHORT).show()
                }.show(activity!!.supportFragmentManager)
        }




        root.add_song.setOnClickListener {


            if (root.add_song_name.text.isNotEmpty() && root.add_song_type.text.isNotEmpty() && root.add_song_adder_name.text.isNotEmpty() && root.add_song_words.text.isNotEmpty() && root.add_song_image.toString().isNotEmpty()) {
                if (db.insertSong(
                        root.add_song_name.text.toString(),
                        root.add_song_type.text.toString(),
                        root.add_song_adder_name.text.toString(),
                        root.add_song_words.text.toString(),
                        imageURI
                    )
                ) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    root.add_song_name.text.clear()
                    root.add_song_type.text=""
                    root.add_song_adder_name.text =""
                    root.add_song_words.text.clear()
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, HomeFragment()).commit()
                } else
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(context, "Fill fields", Toast.LENGTH_SHORT).show()
        }
        root.cancel_song.setOnClickListener {
            root.add_song_adder_name.setText("")
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, HomeFragment()).commit()
        }


        return root
    }


}
