package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.R

class DetalheLivroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_livro)

        val titulo = intent.getStringExtra("LIVRO")
        val preco = intent.getDoubleExtra("PRECO", 0.0)
        val estoque = intent.getBooleanExtra("ESTOQUE", false)

        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetalhe)
        val txtPreco = findViewById<TextView>(R.id.txtPrecoDetalhe)
        val txtEstoque = findViewById<TextView>(R.id.txtEstoqueDetalhe)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionarCarrinho)

        txtTitulo.text = titulo
        txtPreco.text = "R$ $preco"
        txtEstoque.text = if (estoque) "Disponível ✔" else "Fora de estoque ❌"

//        btnAdicionar.setOnClickListener {
//            val intent = Intent(this, CarrinhoActivity::class.java)
//            intent.putExtra("LIVRO", titulo)
//            intent.putExtra("PRECO", preco)
//            startActivity(intent)
//        }
    }
}
