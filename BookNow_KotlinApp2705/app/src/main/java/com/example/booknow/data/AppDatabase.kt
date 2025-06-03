package com.example.booknow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booknow.data.dao.LivroDao
import com.example.booknow.data.dao.UsuarioDao
import com.example.booknow.data.entity.Livro
import com.example.booknow.data.entity.Usuario

@Database(entities = [Livro::class, Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDao(): LivroDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "booknow_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
