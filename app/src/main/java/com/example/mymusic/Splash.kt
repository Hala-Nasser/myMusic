package com.example.mymusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlin.concurrent.thread

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler= Handler()
        handler.postDelayed(Runnable {
            val intent=Intent(this,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }
}
