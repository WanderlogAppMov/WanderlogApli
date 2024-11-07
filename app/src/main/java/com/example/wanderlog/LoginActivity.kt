package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wanderlog.data.network.UserService
import com.example.wanderlog.io.response.LoginResponse
import com.example.wanderlog.io.response.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val apiService: UserService by lazy {
        UserService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val preferences = PreferenceHelper.defaultPrefs(this)
        val jwt = preferences.getString("jwt", "")
        if (jwt?.contains(".") == true) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Referencia al botón Sign In
        val signInButton = findViewById<Button>(R.id.button2)

        // Listener para redirigir a la vista principal
        signInButton.setOnClickListener {
            performLogin()
        }

        val ivEditPackage1 = findViewById<TextView>(R.id.tvSignUp)
        ivEditPackage1.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }

    private fun createSessionPreference(jwt: String) {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences.edit().putString("jwt", jwt).apply()
    }

    private fun performLogin() {
        val etUser = findViewById<EditText>(R.id.itUsername).text.toString()
        val etPassword = findViewById<EditText>(R.id.itPassword).text.toString()
        val call = apiService.postLogin(etUser, etPassword)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse == null) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error en la autenticación",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    if (loginResponse.success) {
                        createSessionPreference(loginResponse.jwt)
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Las Credenciales son incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error en la autenticación",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error en la autenticación", Toast.LENGTH_SHORT).show()
            }
        })
    }
}