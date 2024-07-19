package com.example.lakshya_coaching

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

open class NavDrawerActivity : AppCompatActivity()
{
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
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
            R.id.menuProfile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            R.id.menuSettings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.menuLogout -> Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
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
}