package com.example.parcial2_lavadero.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial2_lavadero.repository.VehiculosRepository

class VehiculosViewModelFactory(private val vehiculosRepository: VehiculosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehiculosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehiculosViewModel(vehiculosRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
