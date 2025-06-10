package com.example.booknow.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.R

class PagamentoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartao_credito) // Aponte para o layout que criamos anteriormente

        // Obtém os dados passados pela Intent
        val tituloLivro = intent.getStringExtra("TITULO_LIVRO")
        val preco = intent.getDoubleExtra("PRECO_LIVRO", 0.0)

        // Obtém as views para exibir os dados
        val txtTituloLivro = findViewById<TextView>(R.id.txtTituloCartao)
        val txtPreco = findViewById<TextView>(R.id.txtPrecoLivroCartao)

        // Define o título e o preço do livro na tela de pagamento
        txtTituloLivro.text = "Titulo:  " + tituloLivro
        txtPreco.text = "R$ " + preco

        // Campos do cartão de crédito
        val edtNumeroCartao = findViewById<EditText>(R.id.edtNumeroCartao)
        val edtNomeTitular = findViewById<EditText>(R.id.edtNomeTitular)
        val edtValidadeCartao = findViewById<EditText>(R.id.edtValidadeCartao)
        val edtCVV = findViewById<EditText>(R.id.edtCVV)

        // Botão para confirmar o pagamento
        val btnPagar = findViewById<Button>(R.id.btnPagar)

        btnPagar.setOnClickListener {
            // Pegar os dados do cartão inseridos pelo usuário
            val numeroCartao = edtNumeroCartao.text.toString()
            val nomeTitular = edtNomeTitular.text.toString()
            val validade = edtValidadeCartao.text.toString()
            val cvv = edtCVV.text.toString()

            // Para este exemplo, vamos apenas exibir uma mensagem simulada
            // Realizar validações ou o processo de pagamento real seria necessário aqui
            if (numeroCartao.isNotEmpty() && nomeTitular.isNotEmpty() && validade.isNotEmpty() && cvv.isNotEmpty()) {
                // Simulação de pagamento bem-sucedido
                Toast.makeText(this, "Pagamento realizado com sucesso!", Toast.LENGTH_SHORT).show()

                // Redireciona para a tela de conclusão de pagamento
                val intent = Intent(this, ConclusaoPagamentoActivity::class.java)
                startActivity(intent)
            } else {
                // Caso algum campo não tenha sido preenchido
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
