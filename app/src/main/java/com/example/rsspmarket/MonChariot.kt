package com.example.rsspmarket

import MonFragmentAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MonChariot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mon_chariot_activity)


        val fragmentAdapter=MonFragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(FragmentChariot1(),"Title1")
        fragmentAdapter.addFragment(FragmentChariot2(),"title2")
        val monViewPager= findViewById<ViewPager>(R.id.monViewPager)
        monViewPager.adapter=fragmentAdapter
        findViewById<TabLayout>(R.id.mesOnglets).setupWithViewPager(monViewPager)

    }
}