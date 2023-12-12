// LoginActivity.kt
package com.example.collageapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collageapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        binding.loginButton.setOnClickListener {
            val login = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (isValidCredentials(login, password)) {
                val savedLogin = sharedPreferences.getString("login", "")
                val savedPassword = sharedPreferences.getString("password", "")

                if (login == savedLogin && password == savedPassword) {
                    startActivity(Intent(this,ModeSectionActivity::class.java))
                } else {
                    showToast("Неверный логин или пароль")
                }
            }
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidCredentials(login: String, password: String): Boolean {
        return if (login.length > 3 && password.length > 3 && !login.contains(" ") && !password.contains(" ")) {
            true
        } else {
            showToast("Логин и пароль должны быть длиннее 3 символов и не содержать пробелов")
            false
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
