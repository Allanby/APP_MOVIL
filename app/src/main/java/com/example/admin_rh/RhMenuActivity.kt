package com.example.admin_rh.ui.rh

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
import com.example.admin_rh.RolesSeleccionActivity
import com.example.adminrh.EmpleadosRH
import com.example.adminrh.R
import com.google.android.material.navigation.NavigationView
import com.example.adminrh.WelcomeFragment

class RhMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_rh) // You'll need to create this layout file

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_main) // Assuming toolbar ID is in activity_rh_menu.xml
        setSupportActionBar(toolbar)

        // DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_rh) // Assuming drawer ID is in activity_rh_menu.xml

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

        // Insets - Apply to the root view of your activity layout if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // NavigationView
        val navigationView: NavigationView = findViewById(R.id.nav_view_rh) // Assuming nav view ID is in activity_rh_menu.xml
        navigationView.setNavigationItemSelectedListener(this)

        // Default fragment
        // Load WelcomeFragment by default
        if (savedInstanceState == null) {
            val defaultFragment = WelcomeFragment.newInstance(
                getString(R.string.welcome),
                getString(R.string.welcome_message)
            )
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, defaultFragment)
                .commit()
            supportActionBar?.title = getString(R.string.namme_rrhh)

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var selectedFragment: Fragment? = null
        var fragmentTitle = getString(R.string.app_name)
        when (item.itemId) {
            R.id.nav_empleados -> selectedFragment = EmpleadosRH()
            R.id.nav_permisos -> selectedFragment = WelcomeFragment()
            R.id.nav_contratos -> Toast.makeText(this, "Contratos", Toast.LENGTH_SHORT).show()
            R.id.nav_reportes -> Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show()
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, selectedFragment)
                .commit()
            supportActionBar?.title = fragmentTitle // Update toolbar title
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // It's good practice to also handle the Up button in the toolbar if you have a drawer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Close the drawer when the back button is pressed if it's open
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
