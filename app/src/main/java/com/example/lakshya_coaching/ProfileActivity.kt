package com.example.lakshya_coaching

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBar


class ProfileActivity : AppCompatActivity()
{
    private lateinit var name: TextView
    private lateinit var mail: TextView
    private lateinit var password: TextView
    private lateinit var contact: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else->super.onContextItemSelected(item)
        }

    }
}