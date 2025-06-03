package com.example.booknow.data.dao

import androidx.room.*
import com.example.booknow.data.entity.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE email = :email AND senhaHash = :senhaHash LIMIT 1")
    suspend fun login(email: String, senhaHash: String): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun listarTodos(): List<Usuario>

    @Update
    suspend fun atualizar(usuario: Usuario)

}
