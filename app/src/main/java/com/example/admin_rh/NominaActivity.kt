package com.example.admin_rh

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.adminrh.R
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
        // composeview



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
        navigationView.itemIconTintList = null

        val defaultFragment = Fragment_item_nomina()
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame_nomina, defaultFragment)
            .commit()
        supportActionBar?.title = getString(R.string.namme_nomina)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragment: Fragment? = when(item.itemId) {
            R.id.nav_nomina -> Fragment_item_nomina()
            R.id.nav_reporte_nomina -> Fragment_item_reportes_nomina()
            else -> null
        }

        val fragmentTitle: String = when(item.itemId) {
            R.id.nav_nomina -> getString(R.string.namme_nomina)
            R.id.nav_reporte_nomina -> getString(R.string.menunomina_reportes)
            else -> getString(R.string.app_name)
        }

        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_nomina, it)
                .commit()
        }


        supportActionBar?.title = fragmentTitle

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
