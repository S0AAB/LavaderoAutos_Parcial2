package com.example.parcial2_lavadero.repository

import com.example.parcial2_lavadero.dao.RegistroLavadoDao
import com.example.parcial2_lavadero.model.RegistroLavado

class RegistroLavadoRepository(private val registroLavadoDao: RegistroLavadoDao) {

    suspend fun insertarRegistroLavado(registro: RegistroLavado) {
        registroLavadoDao.insertarRegistroLavado(registro)
    }

    suspend fun actualizarRegistroLavado(registro: RegistroLavado) {
        registroLavadoDao.actualizarRegistroLavado(registro)
    }

    suspend fun eliminarRegistroLavado(registro: RegistroLavado) {
        registroLavadoDao.eliminarRegistroLavado(registro)
    }

    suspend fun obtenerTodosLosRegistrosLavado(): List<RegistroLavado> {
        return registroLavadoDao.obtenerTodosLosRegistrosLavado()
    }

    suspend fun obtenerRegistroLavadoPorId(registroId: Int): RegistroLavado? {
        return registroLavadoDao.obtenerRegistroLavadoPorId(registroId)
    }

    suspend fun obtenerRegistrosLavadoPorVehiculo(vehiculoId: Int): List<RegistroLavado> {
        return registroLavadoDao.obtenerRegistrosLavadoPorVehiculo(vehiculoId)
    }

    suspend fun obtenerRegistrosLavadoPorServicio(servicioId: Int): List<RegistroLavado> {
        return registroLavadoDao.obtenerRegistrosLavadoPorServicio(servicioId)
    }
}
