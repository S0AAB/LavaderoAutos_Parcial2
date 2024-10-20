package com.example.parcial2_lavadero.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.parcial2_lavadero.model.Servicios

@Dao
interface ServiciosDao {

    // Insertar un nuevo servicio
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarServicio(servicio: Servicios)

    // Actualizar un servicio existente
    @Update
    suspend fun actualizarServicio(servicio: Servicios)

    // Eliminar un servicio
    @Delete
    suspend fun eliminarServicio(servicio: Servicios)

    // Obtener todos los servicios
    @Query("SELECT * FROM servicios")
    suspend fun obtenerTodosLosServicios(): List<Servicios>

    // Obtener un servicio por ID
    @Query("SELECT * FROM servicios WHERE id = :servicioId LIMIT 1")
    suspend fun obtenerServicioPorId(servicioId: Int): Servicios?
}
