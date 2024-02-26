package com.poquiz.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val pref = getSharedPreferences("user_info", Context.MODE_PRIVATE)
//        val isAutoLogin = pref.getBoolean("autoLogin", false)

        val iv = findViewById<ImageView>(R.id.ivSplash)
        Glide
            .with(this)
            .load(R.raw.moving_ditto)
            .into(iv)
        Handler().postDelayed({

//            var intent = Intent(this, LoginActivity::class.java)
//            if (isAutoLogin)
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            finish()
        }, 2500)
    }
}