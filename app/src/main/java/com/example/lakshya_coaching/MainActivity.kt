package com.example.lakshya_coaching

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lakshya_coaching.adapters.MainTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : NavDrawerActivity()
{
    private lateinit var fragments: Array<Fragment>
    private lateinit var tabTitles: Array<String>

    private lateinit var tabs: TabLayout
    private lateinit var tabIcons: Array<Int>
    private lateinit var tabLayoutMediator: TabLayoutMediator

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavDrawer(findViewById(R.id.drawerLayout))
        setupViewPager()
    }

    private fun setupViewPager()
    {
        fragments = arrayOf(
            ExamFragment(),
            ResultFragment()
        )

        tabTitles = arrayOf("Exam", "Results")
        tabIcons = arrayOf(R.drawable.mission, R.drawable.test)

        tabs = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view)

        viewPager.adapter = MainTabAdapter(this, fragments)
        viewPager.isUserInputEnabled = true

        tabLayoutMediator = TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = tabTitles[position]
            tab.setIcon(tabIcons[position])
        }
        tabLayoutMediator.attach()
    }
}