package com.example.mymusic.Fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast

import com.example.mymusic.R
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.song_item.*

/**
 * A simple [Fragment] subclass.
 */
class EditProfileFragment : Fragment() {

    var imageURI: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root=LayoutInflater.from(activity).inflate(R.layout.fragment_edit_profile,null,false)


        val sharedPref=activity!!.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val getEmail=sharedPref.getString("email","")
        val getFullName=sharedPref.getString("fullName","")
        val getGender=sharedPref.getString("gender", "")
        val getPhone=sharedPref.getString("phoneNumber", "")
        val getBirthday=sharedPref.getString("birthday","")
        val getImage=sharedPref.getString("image","")


        val editor=sharedPref.edit()
        root.editprofile_email.setText(getEmail)
        root.editprofile_username.setText(getFullName)
        root.editprofile_gender.setText(getGender)
        root.editprofile_phone.setText(getPhone)
        root.editprofile_birthday.setText(getBirthday)
        root.editprofile_image.setImageURI(Uri.parse(getImage))


        root.editprofile_image.setOnClickListener {

            PickImageDialog.build(PickSetup())
                .setOnPickResult { r ->
                    root.editprofile_image.setImageURI(r.uri)
                    imageURI = r.uri.toString()
                    editor.putString("image",imageURI)
                }
                .setOnPickCancel {
                    Toast.makeText(activity!!, "Faild", Toast.LENGTH_SHORT).show()
                }.show(activity!!.supportFragmentManager)
        }

        root.change_pass.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, ChangePassFragment()).commit()
        }

        root.editprofile_gender.setOnClickListener {
            val popup = PopupMenu(activity, editprofile_gender)
            popup.menuInflater.inflate(R.menu.gender_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.male -> editprofile_gender.setText("Male")
                    R.id.female -> editprofile_gender.setText("Female")
                }
                true
            }
            popup.show()
        }



        root.check.setOnClickListener(){
            val fullName=root.editprofile_username.text.toString()
            val gender=root.editprofile_gender.text.toString()
            val phoneNumber=root.editprofile_phone.text.toString()
            val bithday=root.editprofile_phone.text.toString()

            editor.putString("fullName",fullName)
            editor.putString("gender",gender)
            editor.putString("phoneNumber",phoneNumber)
            editor.putString("birthday",bithday)
            editor.putString("image",imageURI)
            editor.commit()

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,ProfileFragment()).commit()
        }
        root.cancel.setOnClickListener {
            editor.putString("email",getEmail)
            editor.putString("fullName",getFullName)
            editor.putString("gender",getGender)
            editor.putString("phoneNumber",getPhone)
            editor.putString("birthday",getBirthday)
            editor.putString("image",getImage)

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,ProfileFragment()).commit()
        }

        return root
    }




}
