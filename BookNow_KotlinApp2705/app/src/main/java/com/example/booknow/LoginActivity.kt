
package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editSenha = findViewById<EditText>(R.id.editSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            //tem q fazer validação vindo do banco pra liberar esse cara....
            if (email == "livrariabooknow@gmail.com" && senha == "123456") {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, GeneroActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "E-mail ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
