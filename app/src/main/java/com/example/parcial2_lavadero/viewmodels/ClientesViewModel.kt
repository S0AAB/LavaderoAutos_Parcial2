package com.example.parcial2_lavadero.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2_lavadero.model.Clientes
import com.example.parcial2_lavadero.repository.ClientesRepository
import kotlinx.coroutines.launch

class ClientesViewModel(private val clientesRepository: ClientesRepository) : ViewModel() {

    // MutableLiveData para la lista de clientes
    private val _allClientes = MutableLiveData<List<Clientes>>()
    val allClientes: LiveData<List<Clientes>> get() = _allClientes

    init {

        // Cargar los clientes al inicializar el ViewModel
        cargarTodosLosClientes()
    }
    // Método para cargar todos los clientes
    fun cargarTodosLosClientes() {
        viewModelScope.launch {
            _allClientes.value = clientesRepository.obtenerTodosLosClientes()
        }
    }

    // Método para agregar un cliente
    fun addCliente(cliente: Clientes) {
        viewModelScope.launch {
            clientesRepository.insertarCliente(cliente)
            cargarTodosLosClientes() // Recargar la lista después de agregar
        }
    }

    // Método para actualizar un cliente
    fun updateCliente(cliente: Clientes) {
        viewModelScope.launch {
            clientesRepository.actualizarCliente(cliente)
            cargarTodosLosClientes() // Recargar la lista después de actualizar
        }
    }

    // Método para eliminar un cliente
    fun deleteCliente(cliente: Clientes) {
        viewModelScope.launch {
            clientesRepository.eliminarCliente(cliente)
            cargarTodosLosClientes() // Recargar la lista después de eliminar
        }
    }

    // Método para obtener un cliente por ID
    fun obtenerClientePorId(clienteId: Int): LiveData<Clientes?> {
        val clienteLiveData = MutableLiveData<Clientes?>()
        viewModelScope.launch {
            clienteLiveData.value = clientesRepository.obtenerClientePorId(clienteId)
        }
        return clienteLiveData
    }
}
