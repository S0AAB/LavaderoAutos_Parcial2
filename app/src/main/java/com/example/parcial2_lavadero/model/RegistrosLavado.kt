package com.example.parcial2_lavadero.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "registroLavado",
    foreignKeys = [
        ForeignKey(
            entity = Vehiculos::class,
            parentColumns = ["id"],
            childColumns = ["vehiculo_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Servicios::class,
            parentColumns = ["id"],
            childColumns = ["servicio_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class RegistroLavado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fechaLavado: Long,
    val horaInicio: String,
    val horaFin: String,
    val precioTotal: Float,
    //Foraneas
    val vehiculo_id: Int,
    val servicio_id: Int
)