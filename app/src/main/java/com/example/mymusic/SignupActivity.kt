package com.example.mymusic

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mymusic.Fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)




        btn_signup.setOnClickListener {
            var fullName = signup_full_name.text.toString()
            var email = signup_email.text.toString()
            var password = signup_pass.text.toString()
            var confirmPassword = signup_confirm_pass.text.toString()
            var gender: String
            if (rbFemale.isChecked)
                gender = "Female"
            else
                gender = "Male"

            if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                && confirmPassword.isNotEmpty() && password == confirmPassword && gender.isNotEmpty()
            ) {

                val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putInt("id", 1)
                editor.putString("fullName", fullName)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.putString("gender", gender)
                editor.putString("phoneNumber", "")
                editor.putString("birthday", "")
                val image=R.drawable.ic_account_circle.toString()
                editor.putString("image",image)
                editor.apply()

                val i = Intent(this, MainActivity::class.java)
                i.putExtra("fullName",fullName)
                i.putExtra("email",email)
                i.putExtra("gender",gender)
                startActivity(i)
                finish()

            } else {
                Toast.makeText(
                    applicationContext,
                    "Please check the requierment above",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        sign_in.setOnClickListener {
            val i = Intent(this, SigninActivity::class.java)
            startActivity(i)
            finish()
        }

    }
}
