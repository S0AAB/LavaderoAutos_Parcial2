package com.example.parcial2_lavadero.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial2_lavadero.repository.ServiciosRepository

class ServiciosViewModelFactory(private val serviciosRepository: ServiciosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiciosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServiciosViewModel(serviciosRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
