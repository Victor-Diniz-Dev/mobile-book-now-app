package com.example.booknow.data

data class LivroResponse(
    val id: Int,
    val titulo: String,
    val autor: String,
    val preco: String,
    val estoque: Int,
    val imagem_url: String,
    val genero_id: Int
)