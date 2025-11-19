package com.example.admin_rh

import android.annotation.SuppressLint
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
import com.example.adminrh.CargoFragment
import com.example.adminrh.DepartamentoFragment
import com.example.adminrh.JornadaFragment
import com.google.android.material.navigation.NavigationView
import com.example.adminrh.R
import com.example.adminrh.WelcomeFragment
import com.example.gerencia.ui.GerenciaScreen
import com.example.gerencia.components.TarjetaInformativa

class GerenciaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_gerencia)  // layout de gerencia

        drawerLayout = findViewById(R.id.drawer_layout_gerencia)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main) // app_bar_main.xml
        setSupportActionBar(toolbar)

        // Configuración del Drawer
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del NavigationView
        val navigationView: NavigationView = findViewById(R.id.nav_view_gerencia)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.itemIconTintList = null

        // Fragment por defecto
        if (savedInstanceState == null) {
            // 👇 CAMBIO AQUÍ: Crea el fragmento directamente.
            val defaultFragment = WelcomeFragment()

            supportFragmentManager.beginTransaction()

                .replace(R.id.content_frame_gerencia, defaultFragment)
                .commit()

            supportActionBar?.title = getString(R.string.namme_gerencia)
        }
    }

    @SuppressLint("CommitTransaction")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val CargoFragment = CargoFragment()
        val JornadaFragment = JornadaFragment()
        val DepartamentoFragment = DepartamentoFragment()


        when (item.itemId) {
            R.id.nav_cargo -> supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_gerencia, CargoFragment).commit()
            R.id.nav_jornada -> supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_gerencia, JornadaFragment).commit()
            R.id.nav_departamento -> supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_gerencia, DepartamentoFragment).commit()

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}
