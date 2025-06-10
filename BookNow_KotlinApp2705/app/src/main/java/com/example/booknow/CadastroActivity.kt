package com.example.booknow.model
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.R
import com.example.booknow.data.ApiResponse
import com.example.booknow.data.ApiService
import com.example.booknow.data.RetrofitClient
import com.example.booknow.data.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest

class CadastroActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        apiService = RetrofitClient.retrofit.create(ApiService::class.java)

        val nomeInput = findViewById<EditText>(R.id.editNome)
        val emailInput = findViewById<EditText>(R.id.editEmail)
        val senhaInput = findViewById<EditText>(R.id.editSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nome = nomeInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val senha = senhaInput.text.toString().trim()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = Usuario(nome = nome, email = email, senha = senha)

            apiService.cadastrarUsuario(usuario).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        finish() // ou redirecione para a tela de login
                    } else {
                        Toast.makeText(this@CadastroActivity, "Erro ao cadastrar: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@CadastroActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun senha(senha: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(senha.toByteArray())
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}
