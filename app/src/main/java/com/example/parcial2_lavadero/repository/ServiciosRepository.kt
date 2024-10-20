package com.example.parcial2_lavadero.repository

import com.example.parcial2_lavadero.dao.ServiciosDao
import com.example.parcial2_lavadero.model.Servicios

class ServiciosRepository(private val serviciosDao: ServiciosDao) {

    suspend fun insertarServicio(servicio: Servicios) {
        serviciosDao.insertarServicio(servicio)
    }

    suspend fun actualizarServicio(servicio: Servicios) {
        serviciosDao.actualizarServicio(servicio)
    }

    suspend fun eliminarServicio(servicio: Servicios) {
        serviciosDao.eliminarServicio(servicio)
    }

    suspend fun obtenerTodosLosServicios(): List<Servicios> {
        return serviciosDao.obtenerTodosLosServicios()
    }

    suspend fun obtenerServicioPorId(servicioId: Int): Servicios? {
        return serviciosDao.obtenerServicioPorId(servicioId)
    }
}
