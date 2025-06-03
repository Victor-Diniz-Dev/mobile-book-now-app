package com.example.booknow.util

import java.security.MessageDigest

fun hashSenha(senha: String): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(senha.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}
