package com.example.parcial2_lavadero.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Clientes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val email: String,
    val direccion: String
)
