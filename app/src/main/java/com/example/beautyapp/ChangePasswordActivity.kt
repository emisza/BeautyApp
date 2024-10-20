package com.example.beautyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.beautyapp.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.changePasswordButton.setOnClickListener {
            val username = binding.username.text.toString()
            val oldPassword = binding.oldPassword.text.toString()
            val newPassword = binding.newPassword.text.toString()

            if (databaseHelper.readUser(username, oldPassword)) {
                databaseHelper.updatePassword(username, newPassword)
                Toast.makeText(this, "Password Changed Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Old Password Incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
