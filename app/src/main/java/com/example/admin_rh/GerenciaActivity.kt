package com.example.admin_rh

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.adminrh.R
import com.example.adminrh.WelcomeFragment

/**
 * GerenciaActivity ahora actúa como un simple contenedor para el dashboard.
 * Se ha eliminado toda la lógica del NavigationDrawer.
 */
class GerenciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gerencia) // Asegúrate que este layout también esté simplificado

        // 1. Configurar el Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        // 2. Activar y mostrar el botón de "Atrás" (la flecha) en el Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.namme_gerencia) // Establece el título "Gerencia"

        // 3. Cargar el fragmento del dashboard (WelcomeFragment) solo si es la primera vez que se crea la actividad.
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame_gerencia, WelcomeFragment())
                .commit()
        }
    }

    /**
     * Este método ahora solo se encarga de manejar el clic en el botón de "Atrás".
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Comprueba si el botón presionado es el botón "home" (la flecha de atrás en el Toolbar).
        if (item.itemId == android.R.id.home) {
            finish() // Cierra la GerenciaActivity y regresa a la pantalla anterior (RolesSeleccionActivity).
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // --- ¡Toda la lógica anterior del DrawerLayout ha sido eliminada! ---
    // - No se implementa NavigationView.OnNavigationItemSelectedListener.
    // - Se eliminó la variable 'toggle' (ActionBarDrawerToggle).
    // - Se eliminó el método onNavigationItemSelected.
}
