package com.example.parcial2_lavadero.model

import androidx.room.Embedded
import androidx.room.Relation
data class VehiculoConCliente(
    @Embedded val vehiculo: Vehiculos,

    @Relation(
        parentColumn = "cliente_id",
        entityColumn = "id"
    )
    val cliente: Clientes
)