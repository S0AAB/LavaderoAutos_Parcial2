package com.example.parcial2_lavadero.model

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "servicios")
data class Servicios(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: Float
)