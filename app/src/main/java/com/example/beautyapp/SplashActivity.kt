package com.example.beautyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Splash screen delay duration
        val splashDuration = 2000L // 2 seconds

        Handler(Looper.getMainLooper()).postDelayed({
            // Start the main activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // Finish the splash activity
            finish()
        }, splashDuration)
    }
}
