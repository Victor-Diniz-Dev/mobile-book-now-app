package com.example.booknow.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import com.example.booknow.data.Usuario
interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("usuarios")
    fun cadastrarUsuario(@Body usuario: Usuario): Call<ApiResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<ApiResponse>

    @GET("livros")
    fun getLivros(): Call<List<LivroResponse>>



    companion object {
        fun create(): ApiService {
            return RetrofitClient.retrofit.create(ApiService::class.java)
        }
    }

}


data class LoginRequest(
    val email: String,
    val senha: String
)

data class ApiResponse(
    val mensagem: String,
    val nome: String? = null
)

