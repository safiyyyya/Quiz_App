package com.example.lakshya_coaching

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.selects.select
import java.nio.channels.AsynchronousFileChannel.open

open class NavDrawerActivity : AppCompatActivity()
{
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (drawerToggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    private fun navMenuItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.menuProfile ->
            {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.menuLogout ->
            {
                val sharedPrefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
                val spEditor = sharedPrefs.edit()

                spEditor.remove("username")
                spEditor.remove("Id")
                spEditor.apply()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else -> return false
        }

        return true
    }

    protected fun setupNavDrawer(drawerLayout: DrawerLayout)
    {
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener(::navMenuItemSelected)
    }

    protected fun logout(){

    }
}