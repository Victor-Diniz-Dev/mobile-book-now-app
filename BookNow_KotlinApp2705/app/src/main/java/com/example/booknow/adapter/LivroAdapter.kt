package com.example.booknow.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booknow.R
import com.example.booknow.model.Livro
import com.example.booknow.ui.PagamentoActivity  // Sua Activity de pagamento

class LivroAdapter(
    private val listaLivros: List<Livro>,  // Lista de livros
    private val context: Context,          // Contexto para navegação entre as atividades
    private val onItemClick: (Livro) -> Unit // Função que define o comportamento de clique
) : RecyclerView.Adapter<LivroAdapter.LivroViewHolder>() {

    class LivroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTituloLivro)  // Acessando corretamente o ID
        val preco: TextView = itemView.findViewById(R.id.txtPrecoLivro)
        val estoque: TextView = itemView.findViewById(R.id.txtEstoqueLivro)
//        val btnAdicionarCarrinho: Button = itemView.findViewById(R.id.btnAdicionarCarrinho)  // Acessando o botão
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_livro, parent, false)
        return LivroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = listaLivros[position]
        holder.titulo.text = livro.titulo
        holder.preco.text = "R$ ${livro.preco}"
      //  holder.estoque.text = if (livro.emEstoque) "Em estoque ✔" else "Fora de estoque ❌"

        // Evento de clique no item (não no botão)
        holder.itemView.setOnClickListener {
            onItemClick(livro)
        }

        // Evento de clique no botão "Adicionar ao Carrinho"
//        holder.btnAdicionarCarrinho.setOnClickListener {
//            // Cria a Intent para navegar até a tela de adicionar cartão de crédito
//            val intent = Intent(context, PagamentoActivity::class.java)
//
//            // Passando o livro selecionado para a tela de pagamento (se necessário)
//            intent.putExtra("TITULO_LIVRO", livro.titulo)
//            intent.putExtra("PRECO_LIVRO", livro.preco)
//
//            // Inicia a atividade de pagamento
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int = listaLivros.size
}
