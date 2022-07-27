package com.example.sceneformar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var appBar: AppBarLayout
    var prev: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        //set status bar color to white
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        super.onCreate(savedInstanceState)
        //inflating the activity_main layout
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.lytDrawer)
        coordinatorLayout = findViewById(R.id.lytCoordinator)
        toolbar = findViewById(R.id.wdgToolbar)
        frameLayout = findViewById(R.id.lytFrame)
        navigationView = findViewById(R.id.vwNavigation)
        //setting up custom toolbar
        setUpToolbar()
        appBar = findViewById(R.id.lytAppBar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        //syncing the hamburger and back button during drawer motion.
        actionBarDrawerToggle.syncState()
        //replacing the home fragment during app launch
        supportFragmentManager.beginTransaction().replace(R.id.lytFrame, HomeFragment(), "Home")
            .commit()
        supportActionBar?.title = "Home"
        //listener for navigationView items
        navigationView.setNavigationItemSelectedListener {
            appBar.setExpanded(true)

            prev?.isChecked = false
            it.isCheckable = true
            it.isChecked = true
            prev = it

            when (it.itemId) {

                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.lytFrame, HomeFragment(), "Home")
                        .commit()
                    supportActionBar?.title = "Home"
                    drawerLayout.closeDrawers()

                }
                R.id.website -> {

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://studybear-79c4e.web.app/contact")
                    drawerLayout.closeDrawers()
                    startActivity(intent)
                }

                R.id.ar -> {

                    val intent = Intent(this, ARActivity::class.java)
                    startActivity(intent)

                }
            }
            return@setNavigationItemSelectedListener true
        }


    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Study Bear"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}