package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class GeneroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genero)

// Referencia os botões da tela de escolha de gênero
        val btnFiccao = findViewById<Button>(R.id.btnFiccao)
        val btnComedia = findViewById<Button>(R.id.btnComedia)
        val btnBiografia = findViewById<Button>(R.id.btnBiografia)
        val btnSuspense = findViewById<Button>(R.id.btnSuspense)
        val btnRomance = findViewById<Button>(R.id.btnRomance)
        val btnAcao = findViewById<Button>(R.id.btnAcao)
        val btnHq = findViewById<Button>(R.id.btnHq)
        val btnCientifico = findViewById<Button>(R.id.btnCientifico)

        //aplica a msm ação (de clique) pra todos os butões
        val buttons = listOf(btnFiccao, btnComedia, btnBiografia, btnSuspense, btnRomance, btnAcao, btnHq, btnCientifico)

        //pra cada botao, define uma ação
        buttons.forEach { button ->
            button.setOnClickListener {
                val genero = button.text.toString()
                Toast.makeText(this, "Você escolheu $genero", Toast.LENGTH_SHORT).show()

                // Aqui da pra abrir a próxima tela tipo: Lista de Livros do genero escolhido
                // startActivity(Intent(this, ListaLivrosActivity::class.java))
                //pesquisar sobre  uma variavel intent pra abrir a tela de lista de livros e nao ficar fazendo varios layouts pra mesma coisa
            }
        }
    }
}
