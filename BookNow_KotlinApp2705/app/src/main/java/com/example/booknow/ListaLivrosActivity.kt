package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booknow.model.Livro
import com.example.booknow.adapter.LivroAdapter


class ListaLivrosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LivroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros)

        // Ativa a seta de voltar na AppBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Livros"

        val genero = intent.getStringExtra("GENERO")

        val titulo = findViewById<TextView>(R.id.txtTituloGenero)
        titulo.text = genero

        recyclerView = findViewById(R.id.recyclerLivros)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val livros = getLivrosPorGenero(genero)
        adapter = LivroAdapter(livros) { livroSelecionado ->
            val intent = Intent(this, DetalheLivroActivity::class.java)
            intent.putExtra("LIVRO", livroSelecionado.titulo)
            intent.putExtra("PRECO", livroSelecionado.preco)
            intent.putExtra("ESTOQUE", livroSelecionado.emEstoque)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

    //aqui vamos ter q pegar de um repository, jogar num DTO e dar o get aqui provavelmente.
    private fun getLivrosPorGenero(genero: String?): List<Livro> {
        return when (genero) {
            "Ficção Científica" -> listOf(
                Livro("A Sociedade e a Tecnologia", 29.00, true),
                Livro("Astrofísica para Gente com Pressa", 34.00, false),
                Livro("Cosmos", 18.00, true)
            )
            "Comédia" -> listOf(
                Livro("Piadas Nerds", 22.00, true),
                Livro("O Guia do Mochileiro das Galáxias", 25.00, true)
            )
            "Romance" -> listOf(
                Livro("A Culpa é das Estrelas", 28.00, true),
                Livro("Orgulho e Preconceito", 30.00, true)
            )
            "Ação" -> listOf(
                Livro("Missão Impossível", 27.00, true),
                Livro("John Wick", 32.00, false)
            )
            "Biografia" -> listOf(
                Livro("Steve Jobs", 35.00, true),
                Livro("Elon Musk", 33.00, true)
            )
            "Suspense" -> listOf(
                Livro("Garota Exemplar", 26.00, true),
                Livro("O Iluminado", 30.00, true)
            )
            "HQs" -> listOf(
                Livro("Batman: A Piada Mortal", 18.00, true),
                Livro("Homem-Aranha: Aranhaverso", 22.00, true)
            )
            "Científico" -> listOf(
                Livro("Breves Respostas para Grandes Questões", 31.00, true),
                Livro("Uma Breve História do Tempo", 29.00, false)
            )
            else -> listOf(
                Livro("Livro Genérico 1", 10.00, true),
                Livro("Livro Genérico 2", 15.00, true)
            )
        }
    }
}
