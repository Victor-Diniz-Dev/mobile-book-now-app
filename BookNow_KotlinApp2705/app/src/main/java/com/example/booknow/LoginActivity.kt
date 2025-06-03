package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.booknow.data.AppDatabase
import com.example.booknow.util.hashSenha
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.editEmail)
        val senhaInput = findViewById<EditText>(R.id.editSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val linkCadastro = findViewById<TextView>(R.id.linkCadastro)

        /*ver todos os usuarios no logcat*/
        val db = AppDatabase.getDatabase(this)
        val dao = db.usuarioDao() //usuario dao eh o cara repsonsavel pelo acesso ao banco de dados
        lifecycleScope.launch {
            val usuarios = dao.listarTodos()
            usuarios.forEach {
                Log.d("USUARIOS_TESTE", "ID: ${it.id} | Nome: ${it.nome} | Email: ${it.email} | senha: ${it.senhaHash}")
            }
        }

        //update no meu login
//        lifecycleScope.launch {
//            val usuario = dao.buscarPorEmail("joaolima")
//            if (usuario != null) {
//                val atualizado = usuario.copy(email = "joaolima@gmail.com")
//                dao.atualizar(atualizado)
//                Log.d("USUARIO_ATUALIZADO", "Novo e-mail: ${atualizado.email}")
//            }
//        }



        btnLogin.setOnClickListener {
            val email = emailInput.text.toString()
            val senha = hashSenha(senhaInput.text.toString())

            lifecycleScope.launch {
                val usuario = dao.login(email, senha)
                if (usuario != null) {
                    startActivity(Intent(this@LoginActivity, GeneroActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login inválido", Toast.LENGTH_SHORT).show()
                }
            }
        }

        linkCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }
}
