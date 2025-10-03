package com.example.admin_rh   // 👈 mantené el mismo paquete con guion bajo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.example.adminrh.R
import com.example.adminrh.WelcomeFragment

class GerenciaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_gerencia)  // 👈 usa el layout de gerencia

        drawerLayout = findViewById(R.id.drawer_layout_gerencia)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main) // 👈 viene de app_bar_main.xml
        setSupportActionBar(toolbar)

        // Configuración del Drawer
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Ajuste de insets (pantallas edge-to-edge)
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del NavigationView
        val navigationView: NavigationView = findViewById(R.id.nav_view_gerencia)
        navigationView.setNavigationItemSelectedListener(this)

        // Fragment por defecto
        if (savedInstanceState == null) {
            val defaultFragment = WelcomeFragment.newInstance(
                getString(R.string.welcome),
                getString(R.string.welcome_message)
            )
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, defaultFragment) // 👈 asegurate que tengas un container
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_cargo -> Toast.makeText(this, "Cargos de Gerencia", Toast.LENGTH_SHORT).show()
            R.id.nav_jornada -> Toast.makeText(this, "Jornadas", Toast.LENGTH_SHORT).show()
            R.id.nav_departamento -> Toast.makeText(this, "Departamentos", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}
