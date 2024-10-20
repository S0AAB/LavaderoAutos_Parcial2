package com.example.parcial2_lavadero.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2_lavadero.model.Servicios
import com.example.parcial2_lavadero.repository.ServiciosRepository
import kotlinx.coroutines.launch

class ServiciosViewModel(private val serviciosRepository: ServiciosRepository) : ViewModel() {

    // MutableLiveData para la lista de servicios
    private val _allServicios = MutableLiveData<List<Servicios>>()
    val allServicios: LiveData<List<Servicios>> get() = _allServicios
    init {

        cargarTodosLosServicios()
    }
    // Método para cargar todos los servicios
    fun cargarTodosLosServicios() {
        viewModelScope.launch {
            _allServicios.value = serviciosRepository.obtenerTodosLosServicios()
        }
    }


    // Método para agregar un servicio
    fun addServicio(servicio: Servicios) {
        viewModelScope.launch {
            serviciosRepository.insertarServicio(servicio)
            cargarTodosLosServicios() // Recargar la lista después de agregar
        }
    }

    // Método para actualizar un servicio
    fun updateServicio(servicio: Servicios) {
        viewModelScope.launch {
            serviciosRepository.actualizarServicio(servicio)
            cargarTodosLosServicios() // Recargar la lista después de actualizar
        }
    }

    // Método para eliminar un servicio
    fun deleteServicio(servicio: Servicios) {
        viewModelScope.launch {
            serviciosRepository.eliminarServicio(servicio)
            cargarTodosLosServicios() // Recargar la lista después de eliminar
        }
    }

    // Método para obtener un servicio por ID
    fun obtenerServicioPorId(servicioId: Int): LiveData<Servicios?> {
        val servicioLiveData = MutableLiveData<Servicios?>()
        viewModelScope.launch {
            servicioLiveData.value = serviciosRepository.obtenerServicioPorId(servicioId)
        }
        return servicioLiveData
    }
}
