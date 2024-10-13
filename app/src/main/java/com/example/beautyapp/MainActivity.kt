package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val skincareButton: Button = findViewById(R.id.skincareButton)
        val makeupButton: Button = findViewById(R.id.makeupButton)

        skincareButton.setOnClickListener {
            val intent = Intent(this, SkincareActivity::class.java)
            startActivity(intent)
        }

        makeupButton.setOnClickListener {
            val intent = Intent(this, MakeupActivity::class.java)
            startActivity(intent)
        }
    }
}
