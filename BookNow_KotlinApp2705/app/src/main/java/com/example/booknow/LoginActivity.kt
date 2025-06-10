package com.example.booknow.model
import com.example.booknow.model.GeneroActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.model.CadastroActivity
import com.example.booknow.R
import com.example.booknow.databinding.ActivityLoginBinding
import com.example.booknow.data.ApiService
import com.example.booknow.data.RetrofitClient
import com.example.booknow.data.LoginRequest
import com.example.booknow.data.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editSenha = findViewById<EditText>(R.id.editSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnCadastrar = findViewById<TextView>(R.id.btnCadastrar)
        btnCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


        apiService = RetrofitClient.retrofit.create(ApiService::class.java)

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email = email, senha = senha)

            apiService.login(loginRequest).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        Toast.makeText(this@LoginActivity, "Bem-vindo(a) ${body?.nome}", Toast.LENGTH_SHORT).show()
                        // Trocar para a próxima tela se necessário
                        startActivity(Intent(this@LoginActivity, GeneroActivity::class.java))
                        finish()

                    } else {
                        Toast.makeText(this@LoginActivity, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText( this@LoginActivity, "Erro de conexão: ${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}
