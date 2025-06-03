package com.example.booknow.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livros")
data class Livro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val autor: String,
    val genero: String,
    val descricao: String,
    val preco: Double,
    val estoque: Int,
    val imgUrl: String
)