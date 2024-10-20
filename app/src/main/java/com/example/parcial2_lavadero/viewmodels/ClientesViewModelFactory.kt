package com.example.parcial2_lavadero.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial2_lavadero.repository.ClientesRepository

class ClientesViewModelFactory(private val clientesRepository: ClientesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClientesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClientesViewModel(clientesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}