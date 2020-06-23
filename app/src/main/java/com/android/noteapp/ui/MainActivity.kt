package com.android.noteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.noteapp.R
import com.android.noteapp.R.id.action_listFragment_to_addFragment
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController=findNavController(R.id.container)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle=ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener (this)
        fab.setOnClickListener{
            navController.navigate(action_listFragment_to_addFragment)
            fab.hide()
        }

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when (item.itemId) {
        R.id.nav_profile -> {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
        }
        R.id.share_screen-> {
//            val shareIntent = Intent()
//            shareIntent.action = Intent.ACTION_SEND
//            shareIntent.type = "text/plain"
//            shareIntent.putExtra(Intent.EXTRA_TEXT, s)
//            startActivity(Intent.createChooser(shareIntent, s))

            Toast.makeText(this, "Note forwarded", Toast.LENGTH_SHORT).show()
        }
        R.id.nav_record-> {
//            navController.navigate(action_listFragment_to_addFragment)
        }
        R.id.nav_slide-> {
            navView.setNavigationItemSelectedListener (this)

            navController.navigate(action_listFragment_to_addFragment)

        }
        R.id.nav_logout -> {
            Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
        }
    }
    drawerLayout.closeDrawer(GravityCompat.START)
    return true
}


    fun showFloatingButton()
    {
        fab.show()
        fab.visibility=View.VISIBLE
    }


}