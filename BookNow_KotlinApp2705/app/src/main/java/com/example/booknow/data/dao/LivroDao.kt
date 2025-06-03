package com.example.booknow.data.dao

import androidx.room.*
import com.example.booknow.data.entity.Livro

@Dao
interface LivroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirLivro(livro: Livro)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirTodos(lista: List<Livro>)

    @Query("SELECT * FROM livros WHERE genero = :genero")
    suspend fun listarPorGenero(genero: String): List<Livro>

    @Query("SELECT * FROM livros")
    suspend fun listarTodos(): List<Livro>
}
