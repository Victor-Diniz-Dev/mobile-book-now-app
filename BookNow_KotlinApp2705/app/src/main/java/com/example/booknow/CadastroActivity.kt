package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.booknow.data.AppDatabase
import com.example.booknow.data.entity.Usuario
import com.example.booknow.util.hashSenha
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val nomeInput = findViewById<EditText>(R.id.editNome)
        val emailInput = findViewById<EditText>(R.id.editEmail)
        val senhaInput = findViewById<EditText>(R.id.editSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nome = nomeInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val senha = senhaInput.text.toString()

            if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val senhaCriptografada = hashSenha(senha)
            val db = AppDatabase.getDatabase(this)

            lifecycleScope.launch {
                val usuarioExistente = db.usuarioDao().buscarPorEmail(email)
                if (usuarioExistente != null) {
                    Toast.makeText(this@CadastroActivity, "Email já cadastrado", Toast.LENGTH_SHORT).show()
                } else {
                    val novoUsuario = Usuario(nome = nome, email = email, senhaHash = senhaCriptografada)
                    db.usuarioDao().inserir(novoUsuario)
                    Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@CadastroActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
