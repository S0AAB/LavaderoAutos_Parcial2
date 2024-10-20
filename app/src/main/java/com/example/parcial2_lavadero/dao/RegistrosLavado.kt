package com.example.parcial2_lavadero.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.parcial2_lavadero.model.RegistroLavado

@Dao
interface RegistroLavadoDao {

    // Insertar un nuevo registro de lavado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRegistroLavado(registro: RegistroLavado)

    // Actualizar un registro de lavado existente
    @Update
    suspend fun actualizarRegistroLavado(registro: RegistroLavado)

    // Eliminar un registro de lavado
    @Delete
    suspend fun eliminarRegistroLavado(registro: RegistroLavado)

    // Obtener todos los registros de lavado
    @Query("SELECT * FROM registroLavado")
    suspend fun obtenerTodosLosRegistrosLavado(): List<RegistroLavado>

    // Obtener un registro de lavado por ID
    @Query("SELECT * FROM registroLavado WHERE id = :registroId LIMIT 1")
    suspend fun obtenerRegistroLavadoPorId(registroId: Int): RegistroLavado?

    // Obtener registros de lavado por vehiculo_id
    @Query("SELECT * FROM registroLavado WHERE vehiculo_id = :vehiculoId")
    suspend fun obtenerRegistrosLavadoPorVehiculo(vehiculoId: Int): List<RegistroLavado>

    // Obtener registros de lavado por servicio_id
    @Query("SELECT * FROM registroLavado WHERE servicio_id = :servicioId")
    suspend fun obtenerRegistrosLavadoPorServicio(servicioId: Int): List<RegistroLavado>
}
