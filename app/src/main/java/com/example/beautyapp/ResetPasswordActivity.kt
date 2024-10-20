package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.beautyapp.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.submitButton.setOnClickListener {
            val username = binding.username.text.toString()
            val securityAnswer = binding.securityAnswer.text.toString()
            val newPassword = binding.newPassword.text.toString()

            if (databaseHelper.readSecurityAnswer(username, securityAnswer)) {
                databaseHelper.updatePassword(username, newPassword)
                Toast.makeText(this, "Password Reset Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Incorrect Security Answer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
