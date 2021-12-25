package com.example.mymusic

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signup.*

class SigninActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)



        btn_signin.setOnClickListener {
            var email=signin_email.text.toString()
            var password=signin_pass.text.toString()
            val sharedPref=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val getEmail=sharedPref.getString("email","")
            val getPassword=sharedPref.getString("password","")
            if (email.isNotEmpty() && password.isNotEmpty() && email == getEmail && password == getPassword) {

                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Error in the email or password or both", Toast.LENGTH_SHORT).show()
            }
        }
        sign_up.setOnClickListener {
            val i= Intent(this,SignupActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
