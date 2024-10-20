package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.beautyapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        // Handle login button click
        binding.loginButton.setOnClickListener {
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername, loginPassword)
        }

        // Navigate to ChangePasswordActivity when "Change Password" is clicked
        binding.changePasswordRedirect.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        // Redirect to SignupActivity if the user wants to sign up
        binding.signupRedirect.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Redirect to ResetPasswordActivity for password recovery
        binding.resetPasswordRedirect.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

         // Function to check login credentials
        private fun loginDatabase(username: String, password: String) {
        val userExists = databaseHelper.readUser(username, password)
        if (userExists) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
        }
}
