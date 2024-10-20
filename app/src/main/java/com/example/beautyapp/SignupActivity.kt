package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.beautyapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.signupButton.setOnClickListener {
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            val securityQuestion = binding.signupSecurityQuestion.text.toString()
            val securityAnswer = binding.signupSecurityAnswer.text.toString()

            // Validate inputs
            if (signupUsername.isEmpty() || signupPassword.isEmpty() ||
                securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isPasswordValid(signupPassword)) {
                Toast.makeText(this, "Password does not meet requirements", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            signupDatabase(signupUsername, signupPassword, securityQuestion, securityAnswer)
        }

        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#\$%^&+=!]).{8,}$"
        return password.matches(passwordPattern.toRegex())
    }

    private fun signupDatabase(username: String, password: String, securityQuestion: String, securityAnswer: String) {
        val insertedRowId = databaseHelper.insertUser(username, password, securityQuestion, securityAnswer)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }


}
