package com.eliana.dailymemedigest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragments : ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments.add(MemesFragment())

//        val adapter = MyAdapter(this, fragments)
//        viewPager.adapter = adapter
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//                bottomNavbar.selectedItemId = bottomNavbar.menu[position].itemId
//            }
//        })

//        bottomNavbar.setOnItemSelectedListener {
//            viewPager.currentItem = when(it.itemId) {
//                R.id -> 0
//                R.id.itemInbox -> 1
//                R.id.itemProfile -> 2
//                else -> 0 // default to home
//            }
//            true
//        }
    }
}