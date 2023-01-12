package com.eliana.dailymemedigest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_meme.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_meme.*
import kotlinx.android.synthetic.main.card_meme.btnKomentarCard
import kotlinx.android.synthetic.main.card_meme.view.*
import kotlinx.android.synthetic.main.drawer_header.*
import kotlinx.android.synthetic.main.drawer_layout.*

class MainActivity : AppCompatActivity() {
    companion object{
        val IDUSER = "IDUSER"
    }

    val fragments : ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.drawer_layout)

//        val btnKomen = findViewById<Button>(R.id.btnKomentarCard)
//        btnKomen.setOnClickListener(View.OnClickListener() {
//            val intent = Intent(this, DetailMeme::class.java)
//            startActivity(intent)
//            finish()
//        })

        var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        var drawerToggle = ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()
        drawerLayout.closeDrawer(GravityCompat.START)
        true

        drawerLayout.addDrawerListener(DrawerListener())


        navView.setNavigationItemSelectedListener {

                when(it.itemId) {
                    R.id.ItemHome -> {
                        viewPager.setCurrentItem(0)
                        true
                    }
                    R.id.ItemCreationDrawer ->{
                        viewPager.setCurrentItem(1)
                        true
                    }
                    R.id.ItemLeaderboard -> {
                        viewPager.setCurrentItem(2)
                        true
                    }
                    R.id.ItemSettings -> {
                        val intent = Intent(this,Settings::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> ""
                }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        fragments.add(MemesFragment())
        fragments.add(MyCreationFragment())
        fragments.add(LeaderboardFragment())
        //fragments.add(SettingFragment())

        val adapter = MyAdapter(this, fragments)
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                bottomNavbar.selectedItemId = bottomNavbar.menu[position].itemId
            }
        })

        fab.setOnClickListener {
            val intent = Intent(this,CreateMeme::class.java)
            startActivity(intent)
            finish()
        }

        bottomNavbar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ItemHome -> {
                    viewPager.setCurrentItem(0)
                    true
                }
                R.id.ItemCreation -> {
                    viewPager.setCurrentItem(1)
                    true
                }
                R.id.ItemLeaderboard -> {
                    viewPager.setCurrentItem(2)
                    true
                }
                R.id.ItemSetting -> {
                    val intent = Intent(this,Settings::class.java)
                    startActivity(intent)
                    true
                }

                else -> {
                    true
                }
            }
    }
}

    private inner class DrawerListener(): DrawerLayout.DrawerListener {
        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

        }

        override fun onDrawerOpened(drawerView: View) {
            var shared: SharedPreferences = getSharedPreferences("com.eliana.dailymemedigest", Context.MODE_PRIVATE )
            var editor: SharedPreferences.Editor = shared.edit()

            var imageBackground = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            var imageUser = "https://png.pngtree.com/png-vector/20190704/ourlarge/pngtree-businessman-user-avatar-free-vector-png-image_1538405.jpg"
            Picasso.get().load(shared.getString("LINK", "")).into(imageUserHeaderDrawer)
            Picasso.get().load(imageBackground).into(backgroundDrawer)
            txtNamaDrawer.text = shared.getString("FIRSTNAME", "") + " " + shared.getString("LASTNAME", "")
            txtUsernameDrawer.text = shared.getString("USERNAME", "")
            var idUser = shared.getInt("IDUSER", 0).toString()
            print("ini id $idUser")
        }

        override fun onDrawerClosed(drawerView: View) {

        }

        override fun onDrawerStateChanged(newState: Int) {

        }

    }


}