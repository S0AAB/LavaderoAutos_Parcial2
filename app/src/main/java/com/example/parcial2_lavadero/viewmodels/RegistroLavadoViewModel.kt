package com.example.parcial2_lavadero.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2_lavadero.model.RegistroLavado
import com.example.parcial2_lavadero.model.RegistrosLavadoConDetalles
import com.example.parcial2_lavadero.repository.RegistroLavadoRepository
import com.example.parcial2_lavadero.repository.VehiculosRepository
import kotlinx.coroutines.launch

class RegistroLavadoViewModel(private val registroLavadoRepository: RegistroLavadoRepository) : ViewModel() {

    // MutableLiveData para la lista de registros de lavado
    private val _allRegistrosLavado = MutableLiveData<List<RegistroLavado>>()
    val allRegistrosLavado: LiveData<List<RegistroLavado>> get() = _allRegistrosLavado

    init {

        cargarTodosLosRegistrosLavado()
    }
    // Método para cargar todos los registros de lavado
    fun cargarTodosLosRegistrosLavado() {
        viewModelScope.launch {
            _allRegistrosLavado.value = registroLavadoRepository.obtenerTodosLosRegistrosLavado()
        }
    }

    private val _registrosLavadoConDetalles = MutableLiveData<List<RegistrosLavadoConDetalles>>()
    val registrosLavadoConDetalles: LiveData<List<RegistrosLavadoConDetalles>> get() = _registrosLavadoConDetalles

    // Método para cargar todos los registros de lavado con sus detalles
    fun cargarRegistrosLavadoConDetalles() {
        viewModelScope.launch {
            _registrosLavadoConDetalles.value = registroLavadoRepository.obtenerRegistrosLavadoConDetalles()
        }
    }

    // Método para agregar un registro de lavado
    fun addRegistroLavado(registro: RegistroLavado) {
        viewModelScope.launch {
            registroLavadoRepository.insertarRegistroLavado(registro)
            cargarTodosLosRegistrosLavado() // Recargar la lista después de agregar
        }
    }

    // Método para actualizar un registro de lavado
    fun updateRegistroLavado(registro: RegistroLavado) {
        viewModelScope.launch {
            registroLavadoRepository.actualizarRegistroLavado(registro)
            cargarTodosLosRegistrosLavado() // Recargar la lista después de actualizar
        }
    }

    // Método para eliminar un registro de lavado
    fun deleteRegistroLavado(registro: RegistroLavado) {
        viewModelScope.launch {
            registroLavadoRepository.eliminarRegistroLavado(registro)
            cargarTodosLosRegistrosLavado() // Recargar la lista después de eliminar
        }
    }

    // Método para obtener un registro de lavado por ID
    fun obtenerRegistroLavadoPorId(registroId: Int): LiveData<RegistroLavado?> {
        val registroLiveData = MutableLiveData<RegistroLavado?>()
        viewModelScope.launch {
            registroLiveData.value = registroLavadoRepository.obtenerRegistroLavadoPorId(registroId)
        }
        return registroLiveData
    }

    // Método para obtener registros de lavado por vehículo
    fun obtenerRegistrosLavadoPorVehiculo(vehiculoId: Int): LiveData<List<RegistroLavado>> {
        val registrosLiveData = MutableLiveData<List<RegistroLavado>>()
        viewModelScope.launch {
            registrosLiveData.value = registroLavadoRepository.obtenerRegistrosLavadoPorVehiculo(vehiculoId)
        }
        return registrosLiveData
    }

    // Método para obtener registros de lavado por servicio
    fun obtenerRegistrosLavadoPorServicio(servicioId: Int): LiveData<List<RegistroLavado>> {
        val registrosLiveData = MutableLiveData<List<RegistroLavado>>()
        viewModelScope.launch {
            registrosLiveData.value = registroLavadoRepository.obtenerRegistrosLavadoPorServicio(servicioId)
        }
        return registrosLiveData
    }


}
