package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.R
import com.example.booknow.ui.PagamentoActivity

class DetalheLivroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_livro)

        // Recupera os dados passados pela Intent
        val titulo = intent.getStringExtra("LIVRO")
        val preco = intent.getDoubleExtra("PRECO", 0.0)
        val estoque = intent.getBooleanExtra("ESTOQUE", false)
        val autor = intent.getStringExtra("AUTOR")

        // Encontra as views pelo ID
        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetalhe)
        val txtPreco = findViewById<TextView>(R.id.txtPrecoDetalhe)
        val txtEstoque = findViewById<TextView>(R.id.txtEstoqueDetalhe)
        val txtAutor = findViewById<TextView>(R.id.txtAutorDetalhe)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionarCarrinho)

        // Define os valores nas views
        txtTitulo.text = titulo
        txtPreco.text = "R$ $preco"
        txtAutor.text = autor
      //  txtEstoque.text = if (estoque) "Disponível ✔" else "Fora de estoque ❌"

        // Configura o onClickListener para o botão "Adicionar ao Carrinho"
        btnAdicionar.setOnClickListener {
            // Cria a Intent para navegar até a tela de pagamento
            val intent = Intent(this, PagamentoActivity::class.java)

            // Passando o livro selecionado para a tela de pagamento (se necessário)
            intent.putExtra("TITULO_LIVRO", titulo)
            intent.putExtra("PRECO_LIVRO", preco)

            // Inicia a atividade de pagamento
            startActivity(intent)
        }
    }
}
