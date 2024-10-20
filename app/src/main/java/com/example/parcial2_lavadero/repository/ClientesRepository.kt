package com.example.parcial2_lavadero.repository

import com.example.parcial2_lavadero.dao.ClientesDao
import com.example.parcial2_lavadero.model.Clientes

class ClientesRepository(private val clientesDao: ClientesDao) {

    suspend fun insertarCliente(cliente: Clientes) {
        clientesDao.insertarCliente(cliente)
    }

    suspend fun actualizarCliente(cliente: Clientes) {
        clientesDao.actualizarCliente(cliente)
    }

    suspend fun eliminarCliente(cliente: Clientes) {
        clientesDao.eliminarCliente(cliente)
    }

    suspend fun obtenerTodosLosClientes(): List<Clientes> {
        return clientesDao.obtenerTodosLosClientes()
    }

    suspend fun obtenerClientePorId(clienteId: Int): Clientes? {
        return clientesDao.obtenerClientePorId(clienteId)
    }
}
