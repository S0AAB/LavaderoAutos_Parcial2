package com.example.parcial2_lavadero.model

import androidx.room.Embedded
import androidx.room.Relation

data class RegistrosLavadoConDetalles(
    @Embedded val registroLavado: RegistroLavado,

    @Relation(
        parentColumn = "vehiculo_id",
        entityColumn = "id"
    )
    val vehiculo: Vehiculos,

    @Relation(
        parentColumn = "servicio_id",
        entityColumn = "id"
    )
    val servicio: Servicios
)