package com.example.parcial2_lavadero.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.parcial2_lavadero.dao.ClientesDao
import com.example.parcial2_lavadero.dao.RegistroLavadoDao
import com.example.parcial2_lavadero.dao.ServiciosDao
import com.example.parcial2_lavadero.dao.VehiculosDao
import com.example.parcial2_lavadero.model.Clientes
import com.example.parcial2_lavadero.model.RegistroLavado
import com.example.parcial2_lavadero.model.Servicios
import com.example.parcial2_lavadero.model.Vehiculos

// Anotar la clase con @Database y especificar las entidades y la versión de la base de datos
@Database(
    entities = [Clientes::class, Vehiculos::class, Servicios::class, RegistroLavado::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientesDao(): ClientesDao
    abstract fun vehiculosDao(): VehiculosDao
    abstract fun serviciosDao(): ServiciosDao
    abstract fun registroLavadoDao(): RegistroLavadoDao

    // Crear una instancia de la base de datos utilizando el patrón singleton
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "parcial2_lavadero_database" // Nombre de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
