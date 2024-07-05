package com.example.zajecia5
import com.example.zajecia5.R

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var mlayout:DrawerLayout? = null
    private var mtoggle:ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mlayout = findViewById<View>(R.id.main_activity) as DrawerLayout
        mtoggle = ActionBarDrawerToggle(this, mlayout,R.string.open, R.string.close)
        mlayout!!.addDrawerListener(mtoggle!!)
        val mnawigacja = findViewById<View>(R.id.nawigacja) as NavigationView
        mtoggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        ustawContent(mnawigacja)
        if (savedInstanceState == null) {
            loadFragment(StronaGlowna())
            mnawigacja.setCheckedItem(R.id.strona_glowna)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (mtoggle?.onOptionsItemSelected(item) == true) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun ustawContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { item ->
            wybranyElementMenu(item)
            false
        }
    }

    fun wybranyElementMenu(menuItem: MenuItem) {
        var mFragment: Fragment? = null
        val fragmentClass: Class<*>
        fragmentClass = when (menuItem.itemId) {
            R.id.strona_glowna->StronaGlowna::class.java
            R.id.o_nas -> O_nas::class.java
            R.id.galeria -> Galeria::class.java
            R.id.menu -> Menu::class.java
            R.id.kontakt -> Kontakt::class.java

            else -> O_nas::class.java
        }
        try {
            mFragment =fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContent, mFragment!!).commit()
        menuItem.setChecked(true)
        title = menuItem.title
        mlayout?.closeDrawers()

    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContent, fragment)
            .commit()
    }
}