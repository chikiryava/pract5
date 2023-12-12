
package com.example.collageapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collageapp.databinding.ActivityRegisterBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        binding.registerButtonRegister.setOnClickListener {
            val login = binding.usernameEditTextRegister.text.toString()
            val password = binding.passwordEditTextRegister.text.toString()

            if (isValidCredentials(login, password)) {
                with(sharedPreferences.edit()) {
                    putString("login", login)
                    putString("password", password)
                    apply()
                }

                showToast("Регистрация успешна")
                startActivity(Intent(this@RegistrationActivity,LoginActivity::class.java))
            }
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

