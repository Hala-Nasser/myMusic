package com.example.mymusic.Fragments


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible

import com.example.mymusic.R
import com.example.mymusic.SigninActivity
import com.example.mymusic.SignupActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root=LayoutInflater.from(activity).inflate(R.layout.fragment_profile,null,false)

        val sharedPref=activity!!.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val getEmail=sharedPref.getString("email","")
        val getFullName=sharedPref.getString("fullName","")
        val getGender=sharedPref.getString("gender", "")
        val getPhone=sharedPref.getString("phoneNumber", "")
        val getBirthday=sharedPref.getString("birthday","")
        val getImage=sharedPref.getString("image","")



        root.profile_email.setText(getEmail)
        root.profile_username.setText(getFullName)
        root.profile_gender.setText(getGender)
        root.profile_phone.setText(getPhone)
        root.profile_birthday.setText(getBirthday)
        root.profile_image.setImageURI(Uri.parse(getImage))

        root.edit_profile.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,EditProfileFragment()).commit()
        }
        root.log_out.setOnClickListener {

            val alertDialog = AlertDialog.Builder(activity!!)
            alertDialog.setTitle("Log out")
            alertDialog.setMessage("Are you sure?")
            alertDialog.setCancelable(false)
            alertDialog.setIcon(R.drawable.log_out)

            alertDialog.setPositiveButton("Yes") { _, _ ->
                val i= Intent(activity, SigninActivity::class.java)
                startActivity(i)
                activity!!.finish()
            }

            alertDialog.setNegativeButton("No") { _, _  ->
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer,ProfileFragment()).commit()
            }

            alertDialog.create().show()
        }
        return root
    }



}
