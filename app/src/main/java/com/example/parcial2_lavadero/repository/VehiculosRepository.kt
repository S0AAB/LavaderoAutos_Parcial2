package com.example.parcial2_lavadero.repository

import com.example.parcial2_lavadero.dao.VehiculosDao
import com.example.parcial2_lavadero.model.Vehiculos

class VehiculosRepository(private val vehiculosDao: VehiculosDao) {

    suspend fun insertarVehiculo(vehiculo: Vehiculos) {
        vehiculosDao.insertarVehiculo(vehiculo)
    }

    suspend fun actualizarVehiculo(vehiculo: Vehiculos) {
        vehiculosDao.actualizarVehiculo(vehiculo)
    }

    suspend fun eliminarVehiculo(vehiculo: Vehiculos) {
        vehiculosDao.eliminarVehiculo(vehiculo)
    }

    suspend fun obtenerTodosLosVehiculos(): List<Vehiculos> {
        return vehiculosDao.obtenerTodosLosVehiculos()
    }

    suspend fun obtenerVehiculoPorId(vehiculoId: Int): Vehiculos? {
        return vehiculosDao.obtenerVehiculoPorId(vehiculoId)
    }

    suspend fun obtenerVehiculosPorCliente(clienteId: Int): List<Vehiculos> {
        return vehiculosDao.obtenerVehiculosPorCliente(clienteId)
    }


}
