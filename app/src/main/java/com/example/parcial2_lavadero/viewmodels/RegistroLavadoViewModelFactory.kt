package com.example.parcial2_lavadero.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial2_lavadero.repository.RegistroLavadoRepository

class RegistroLavadoViewModelFactory(private val registroLavadoRepository: RegistroLavadoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroLavadoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistroLavadoViewModel(registroLavadoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}