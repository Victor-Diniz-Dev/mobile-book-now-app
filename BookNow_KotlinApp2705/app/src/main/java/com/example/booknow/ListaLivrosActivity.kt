package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booknow.adapter.LivroAdapter
import com.example.booknow.data.AppDatabase
import com.example.booknow.data.entity.Livro
import kotlinx.coroutines.launch

class ListaLivrosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LivroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Livros"

        val genero = intent.getStringExtra("GENERO") ?: "Geral"
        val titulo = findViewById<TextView>(R.id.txtTituloGenero)
        titulo.text = genero

        recyclerView = findViewById(R.id.recyclerLivros)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = AppDatabase.getDatabase(this)
        val dao = db.livroDao()

        lifecycleScope.launch {
            var livros = dao.listarPorGenero(genero)

            // Mock inicial se estiver vazio
            if (livros.isEmpty()) {
            //    livros = getLivrosPorGenero(genero)
                livros.forEach { dao.inserirLivro(it) }
            }

            adapter = LivroAdapter(livros.map {
                com.example.booknow.model.Livro(
                    it.titulo,
                    preco = 29.90,
                    emEstoque = true
                )
            }) { livroSelecionado ->
                val intent = Intent(this@ListaLivrosActivity, DetalheLivroActivity::class.java)
                intent.putExtra("LIVRO", livroSelecionado.titulo)
                intent.putExtra("PRECO", livroSelecionado.preco)
                intent.putExtra("ESTOQUE", livroSelecionado.emEstoque)
                startActivity(intent)
            }

            recyclerView.adapter = adapter
        }
    }

    // Mock para popular inicialmente o banco
//    private fun getLivrosPorGenero(genero: String): List<Livro> {
//        return listOf(
//            Livro(titulo = "Livro Teste A", autor = "Autor A", genero = genero, descricao = "Desc A"),
//            Livro(titulo = "Livro Teste B", autor = "Autor B", genero = genero, descricao = "Desc B")
//
//        )
//    }
}
