package com.example.admin_rh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminrh.R

class LoginActivity : AppCompatActivity() {
    private val user = "sexo"
    private val pass = "sexo"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val btnLogin : Button = findViewById(R.id.btnLogin)
        val txtUser: EditText = findViewById(R.id.editTextUser)
        val txtPass: EditText = findViewById(R.id.editTextPassword)

        btnLogin.setOnClickListener {
            val enteredUser = txtUser.text.toString().trim()
            val enteredPass = txtPass.text.toString().trim()

            if (enteredUser == user && enteredPass == pass) {
                val intent = Intent(this, RolesSeleccionActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
