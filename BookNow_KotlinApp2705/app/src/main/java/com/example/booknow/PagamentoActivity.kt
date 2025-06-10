package com.example.booknow.ui

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
//        val precoLivro = intent.getStringExtra("PRECO_LIVRO")
        val preco = intent.getDoubleExtra("PRECO", 0.0)

        // Obtém as views para exibir os dados
        val txtTituloLivro = findViewById<TextView>(R.id.txtTituloCartao)
       // val txtPrecoLivro = findViewById<TextView>(R.id.txtPrecoLivroCartao)

        val txtPreco = findViewById<TextView>(R.id.txtPrecoLivroCartao)


        // Define o título e o preço do livro na tela de pagamento
        txtTituloLivro.text = "Titulo:  " + tituloLivro
        txtPreco.text = "R$ $preco"

        // Campo de número do cartão
        val edtNumeroCartao = findViewById<EditText>(R.id.edtNumeroCartao)

        // Campo do nome do titular
        val edtNomeTitular = findViewById<EditText>(R.id.edtNomeTitular)

        // Campo de validade
        val edtValidadeCartao = findViewById<EditText>(R.id.edtValidadeCartao)

        // Campo de CVV
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
                // Você pode exibir uma mensagem ou redirecionar para outra tela
                // Exemplo de uma simples notificação:
                 Toast.makeText(this, "Pagamento realizado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                // Caso algum campo não tenha sido preenchido
                // Exemplo de uma simples notificação de erro
                 Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
