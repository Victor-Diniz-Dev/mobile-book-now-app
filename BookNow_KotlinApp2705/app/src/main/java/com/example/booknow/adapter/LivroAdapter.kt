package com.example.booknow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booknow.R
import com.example.booknow.model.Livro


class LivroAdapter(
    private val listaLivros: List<Livro>,
    private val onItemClick: (Livro) -> Unit
) : RecyclerView.Adapter<LivroAdapter.LivroViewHolder>() {

    class LivroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTituloLivro)
        val preco: TextView = itemView.findViewById(R.id.txtPrecoLivro)
        val estoque: TextView = itemView.findViewById(R.id.txtEstoqueLivro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro, parent, false)
        return LivroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = listaLivros[position]
        holder.titulo.text = livro.titulo
        holder.preco.text = "R$ ${livro.preco}"
      //  holder.estoque.text = if (livro.emEstoque) "Em estoque ✔" else "Fora de estoque ❌"

        holder.itemView.setOnClickListener {
            onItemClick(livro)
        }
    }

    override fun getItemCount(): Int = listaLivros.size
}
