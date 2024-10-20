package com.example.parcial2_lavadero.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.parcial2_lavadero.model.Clientes

@Dao
interface ClientesDao {

    // Insertar un nuevo cliente
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCliente(cliente: Clientes)

    // Actualizar un cliente existente
    @Update
    suspend fun actualizarCliente(cliente: Clientes)

    // Eliminar un cliente
    @Delete
    suspend fun eliminarCliente(cliente: Clientes)

    // Obtener todos los clientes
    @Query("SELECT * FROM clientes")
    suspend fun obtenerTodosLosClientes(): List<Clientes>

    // Obtener un cliente por ID
    @Query("SELECT * FROM clientes WHERE id = :clienteId LIMIT 1")
    suspend fun obtenerClientePorId(clienteId: Int): Clientes?
}
