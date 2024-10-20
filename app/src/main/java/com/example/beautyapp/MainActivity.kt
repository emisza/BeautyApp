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
        val cartButton: Button = findViewById(R.id.cart_button)

        // Set click listener to open CartActivity
        cartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
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
}
