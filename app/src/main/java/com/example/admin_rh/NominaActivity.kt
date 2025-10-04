package com.example.admin_rh

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.adminrh.R
import com.example.adminrh.WelcomeFragment
import com.google.android.material.navigation.NavigationView

class NominaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_nomina) // usalayo ut limpio

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_nomina)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Gestión de nómina"

        // DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_nomina)

        // Toggle
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // NavigationView
        val navigationView: NavigationView = findViewById(R.id.nav_view_nomina)
        navigationView.setNavigationItemSelectedListener(this)

        // Default fragment (WelcomeFragment)
        if (savedInstanceState == null) {
            val defaultFragment = WelcomeFragment.newInstance(
                getString(R.string.welcome),
                getString(R.string.welcome_message)
            )
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_nomina, defaultFragment) //
                .commit()
            supportActionBar?.title = getString(R.string.namme_nomina)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var selectedFragment: Fragment? = null
        var fragmentTitle = getString(R.string.app_name)

        when (item.itemId) {
            R.id.nav_nomina -> {
                Toast.makeText(this, "Planillas", Toast.LENGTH_SHORT).show()
                // para abrir un fragment de nomina o un activity layout
            }
            R.id.nav_reportes -> {
                Toast.makeText(this, "Descuentos", Toast.LENGTH_SHORT).show()

            }
        }

        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_nomina, selectedFragment)
                .commit()
            supportActionBar?.title = fragmentTitle
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
