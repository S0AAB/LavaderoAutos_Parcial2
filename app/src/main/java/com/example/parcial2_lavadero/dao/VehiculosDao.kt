package com.example.parcial2_lavadero.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.parcial2_lavadero.model.Vehiculos

@Dao
interface VehiculosDao {

    // Insertar un nuevo vehículo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarVehiculo(vehiculo: Vehiculos)

    // Actualizar un vehículo existente
    @Update
    suspend fun actualizarVehiculo(vehiculo: Vehiculos)

    // Eliminar un vehículo
    @Delete
    suspend fun eliminarVehiculo(vehiculo: Vehiculos)

    // Obtener todos los vehículos
    @Query("SELECT * FROM vehiculos")
    suspend fun obtenerTodosLosVehiculos(): List<Vehiculos>

    // Obtener un vehículo por ID
    @Query("SELECT * FROM vehiculos WHERE id = :vehiculoId LIMIT 1")
    suspend fun obtenerVehiculoPorId(vehiculoId: Int): Vehiculos?

    // Obtener vehículos por cliente_id
    @Query("SELECT * FROM vehiculos WHERE cliente_id = :clienteId")
    suspend fun obtenerVehiculosPorCliente(clienteId: Int): List<Vehiculos>

    @Query("SELECT id FROM vehiculos WHERE placa = :placa LIMIT 1")
    suspend fun obtenerVehiculoIdPorPlaca(placa: String): Int?
}
