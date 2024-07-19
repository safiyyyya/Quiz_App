package com.example.lakshya_coaching

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lakshya_coaching.models.Student
import com.example.lakshya_coaching.services.AuthService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection

class LoginActivity : AppCompatActivity()
{
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        val sharedPrefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
        if (sharedPrefs.getString("Id", null) != null)
        {
            goToTab()
            return
        }

        authService = AuthService()

        val txtUserName = findViewById<EditText>(R.id.txt_username)
        val txtPassword = findViewById<EditText>(R.id.txt_password)

        findViewById<Button>(R.id.btn_login).setOnClickListener{

            val username = txtUserName.text.toString()
            val password = txtPassword.text.toString()
            val students = Student(Username = username, PasswordHash = password)

            CoroutineScope(Dispatchers.IO).launch {
                val response = authService.login(students)
                when (response.code)
                {
                    HttpURLConnection.HTTP_OK ->
                    {
                        val loggedInUser = Gson().fromJson(response.message, Student::class.java)
                        val spEditor = sharedPrefs.edit()

                        spEditor.putString("username", username)
                        spEditor.putString("Id", loggedInUser.id.toString())
                        spEditor.apply()

                        withContext(Dispatchers.Main) { goToTab() }
                    }
                    HttpURLConnection.HTTP_NOT_FOUND ->
                    {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Wrong email or password",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
    private fun goToTab()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }}