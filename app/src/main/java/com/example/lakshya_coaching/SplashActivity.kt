package com.example.lakshya_coaching

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashActivity : AppCompatActivity()
{
    private lateinit var splash: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash = findViewById(R.id.Splash)

        splash.alpha = 0f
        splash.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }


}