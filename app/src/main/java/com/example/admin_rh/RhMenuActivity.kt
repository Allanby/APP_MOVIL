package com.example.admin_rh.ui.rh

import android.graphics.Color

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
import com.example.admin_rh.ScrollingFragmentContratos
import com.example.admin_rh.ScrollingFragmentPermisos
import com.example.adminrh.EmpleadosRH
import com.example.adminrh.R
import com.google.android.material.navigation.NavigationView


class RhMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_rh)

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)

        // DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_rh)

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
        toggle.drawerArrowDrawable.color = Color.WHITE // ícono del menú blanco

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // NavigationView
        navigationView = findViewById(R.id.nav_view_rh)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.itemIconTintList = null // conserva colores originales de lo iconos

        ///navigationView.setItemTextColor(
            //ColorStateList.valueOf(ContextCompat.getColor(this, R.color.blue_navy))
      //  ) //para poner el color al texto de cada item del menu


        if (savedInstanceState == null) {
            val defaultFragment = EmpleadosRH()
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, defaultFragment)
                .commit()
            supportActionBar?.title = getString(R.string.namme_rrhh)

            // Seleccionar item en NavigationView
            navigationView.setCheckedItem(R.id.nav_empleados)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragment: Fragment? = when(item.itemId) {
            R.id.nav_empleados -> EmpleadosRH()
            R.id.nav_permisos -> ScrollingFragmentPermisos()
            R.id.nav_contratos -> ScrollingFragmentContratos()
            else -> null
        }

        val fragmentTitle: String = when(item.itemId) {
            R.id.nav_empleados -> getString(R.string.namme_rrhh)
            R.id.nav_permisos -> getString(R.string.namme_permisos)
            R.id.nav_contratos -> getString(R.string.namme_contratos)
            else -> getString(R.string.app_name)
        }

        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, it)
                .commit()
        }

        supportActionBar?.title = fragmentTitle
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
