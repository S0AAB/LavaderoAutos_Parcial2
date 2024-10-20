package com.example.parcial2_lavadero.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehiculos",
    foreignKeys = [
        ForeignKey(
            entity = Clientes::class,
            parentColumns = ["id"],
            childColumns = ["cliente_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Vehiculos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val marca: String,
    val modelo: String,
    val placa: String,
    val color: String,
    val tipo: String,
    //Foranea
    val cliente_id: Int
)