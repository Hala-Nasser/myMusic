package com.example.mymusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcome_signin.setOnClickListener {
            val i=Intent(this, SigninActivity::class.java)
            startActivity(i)
            finish()
        }
        welcome_signup.setOnClickListener {
            val i=Intent(this, SignupActivity::class.java)
            startActivity(i)
            finish()
        }


    }
}
