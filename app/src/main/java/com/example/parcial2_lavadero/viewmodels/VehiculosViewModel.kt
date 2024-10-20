package com.example.parcial2_lavadero.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2_lavadero.model.Vehiculos
import com.example.parcial2_lavadero.repository.VehiculosRepository
import kotlinx.coroutines.launch

class VehiculosViewModel(private val vehiculosRepository: VehiculosRepository) : ViewModel() {

    // MutableLiveData para la lista de vehículos
    private val _allVehiculos = MutableLiveData<List<Vehiculos>>()
    val allVehiculos: LiveData<List<Vehiculos>> get() = _allVehiculos

    // Método para cargar todos los vehículos
    fun cargarTodosLosVehiculos() {
        viewModelScope.launch {
            _allVehiculos.value = vehiculosRepository.obtenerTodosLosVehiculos()
        }
    }

    // Método para agregar un vehículo
    fun addVehiculo(vehiculo: Vehiculos) {
        viewModelScope.launch {
            vehiculosRepository.insertarVehiculo(vehiculo)
            cargarTodosLosVehiculos() // Recargar la lista después de agregar
        }
    }

    // Método para actualizar un vehículo
    fun updateVehiculo(vehiculo: Vehiculos) {
        viewModelScope.launch {
            vehiculosRepository.actualizarVehiculo(vehiculo)
            cargarTodosLosVehiculos() // Recargar la lista después de actualizar
        }
    }

    // Método para eliminar un vehículo
    fun deleteVehiculo(vehiculo: Vehiculos) {
        viewModelScope.launch {
            vehiculosRepository.eliminarVehiculo(vehiculo)
            cargarTodosLosVehiculos() // Recargar la lista después de eliminar
        }
    }

    // Método para obtener un vehículo por ID
    fun obtenerVehiculoPorId(vehiculoId: Int): LiveData<Vehiculos?> {
        val vehiculoLiveData = MutableLiveData<Vehiculos?>()
        viewModelScope.launch {
            vehiculoLiveData.value = vehiculosRepository.obtenerVehiculoPorId(vehiculoId)
        }
        return vehiculoLiveData
    }

    // Método para obtener vehículos por cliente
    fun obtenerVehiculosPorCliente(clienteId: Int) {
        viewModelScope.launch {
            _allVehiculos.value = vehiculosRepository.obtenerVehiculosPorCliente(clienteId)
        }
    }
}

