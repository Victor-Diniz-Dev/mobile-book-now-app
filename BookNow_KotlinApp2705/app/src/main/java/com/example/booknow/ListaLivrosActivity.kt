package com.example.booknow

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booknow.adapter.LivroAdapter
import com.example.booknow.data.ApiService
import com.example.booknow.data.LivroResponse
import com.example.booknow.data.RetrofitClient
import com.example.booknow.model.Livro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaLivrosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LivroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Livros"

        val generoId = intent.getIntExtra("genero_id", 1)
        val titulo = findViewById<TextView>(R.id.txtTituloGenero)
        titulo.text = obterNomeGenero(generoId)

        recyclerView = findViewById(R.id.recyclerLivros)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiService = RetrofitClient.retrofit.create(ApiService::class.java)
        apiService.getLivros().enqueue(object : Callback<List<LivroResponse>> {
            override fun onResponse(
                call: Call<List<LivroResponse>>,
                response: Response<List<LivroResponse>>
            ) {
                if (response.isSuccessful) {
                    val livrosFiltrados = response.body()?.filter { it.genero_id == generoId } ?: emptyList()

                    adapter = LivroAdapter(livrosFiltrados.map {
                        Livro(
                            titulo = it.titulo,
                            preco = it.preco.toDouble(),
                            emEstoque = it.estoque > 0
                        )
                    }) { livroSelecionado ->
                        val intent = Intent(this@ListaLivrosActivity, DetalheLivroActivity::class.java)
                        intent.putExtra("LIVRO", livroSelecionado.titulo)
                        intent.putExtra("PRECO", livroSelecionado.preco)
                        intent.putExtra("ESTOQUE", livroSelecionado.emEstoque)
                        startActivity(intent)
                    }

                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@ListaLivrosActivity, "Erro ao carregar livros", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LivroResponse>>, t: Throwable) {
                Toast.makeText(this@ListaLivrosActivity, "Erro de conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun obterNomeGenero(id: Int): String {
        return when (id) {
            1 -> "Ficção"
            2 -> "Comédia"
            3 -> "Biografia"
            4 -> "Suspense"
            5 -> "Romance"
            6 -> "Ação"
            7 -> "HQs"
            8 -> "Científicos"
            else -> "Geral"
        }
    }
}
