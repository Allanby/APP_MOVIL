package com.example.admin_rh   // 👈 corregido con guion bajo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.admin_rh.ui.rh.RhMenuActivity
//import com.example.admin_rh.ui.rh.RhMenuFragment
import com.example.adminrh.R

class RolesSeleccionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_rol)

        val btnRRHH: ImageButton = findViewById(R.id.btnRRHH)
        val btnNomina: ImageButton = findViewById(R.id.btnNomina)
        val btnGerencia: ImageButton = findViewById(R.id.btnGerencia)

        // Abrir Recursos Humanos
        btnRRHH.setOnClickListener {
            val intent = Intent(this, RhMenuActivity::class.java) // RH
            startActivity(intent)
        }

        // Abrir Nómina
        btnNomina.setOnClickListener {
            val intent = Intent(this, NominaActivity::class.java) // Nómina
            startActivity(intent)
        }

        // Abrir Gerencia
        btnGerencia.setOnClickListener {
            val intent = Intent(this, GerenciaActivity::class.java) //  Gerencia
            startActivity(intent)
        }
    }
}
