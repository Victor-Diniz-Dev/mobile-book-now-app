package com.example.booknow.model

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.ListaLivrosActivity
import com.example.booknow.R

class GeneroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genero)

        // Mapeia os botões com seus respectivos IDs
        val btnFiccao = findViewById<Button>(R.id.btnFiccao)
        val btnComedia = findViewById<Button>(R.id.btnComedia)
        val btnBiografia = findViewById<Button>(R.id.btnBiografia)
        val btnSuspense = findViewById<Button>(R.id.btnSuspense)
        val btnRomance = findViewById<Button>(R.id.btnRomance)
        val btnAcao = findViewById<Button>(R.id.btnAcao)
        val btnHq = findViewById<Button>(R.id.btnHq)
        val btnCientifico = findViewById<Button>(R.id.btnCientifico)

        // Opcional: Botão Continuar (ainda não programado)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)

        // Define ação para cada botão (envia id fictício do gênero)
        btnFiccao.setOnClickListener { abrirListaLivros(1) }
        btnComedia.setOnClickListener { abrirListaLivros(2) }
        btnBiografia.setOnClickListener { abrirListaLivros(3) }
        btnSuspense.setOnClickListener { abrirListaLivros(4) }
        btnRomance.setOnClickListener { abrirListaLivros(5) }
        btnAcao.setOnClickListener { abrirListaLivros(6) }
        btnHq.setOnClickListener { abrirListaLivros(7) }
        btnCientifico.setOnClickListener { abrirListaLivros(8) }

        // Por enquanto, o botão "Continuar" pode abrir uma lista genérica
        btnContinuar.setOnClickListener { abrirListaLivros(1) } // ou você pode deixar sem ação
    }

    private fun abrirListaLivros(generoId: Int) {
        val intent = Intent(this, ListaLivrosActivity::class.java)
        intent.putExtra("genero_id", generoId)
        startActivity(intent)
    }
}
