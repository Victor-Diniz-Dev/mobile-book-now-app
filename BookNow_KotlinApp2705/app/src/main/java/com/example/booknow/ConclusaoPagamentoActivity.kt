package com.example.booknow.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.booknow.R
import com.example.booknow.model.GeneroActivity

class ConclusaoPagamentoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conclusao_pagamento)


        Handler().postDelayed({
            val intent = Intent(this, GeneroActivity::class.java)
            startActivity(intent)
            finish()  // Mata a activiviti
        }, 4000)  // Espera 4 segundos
    }
}
