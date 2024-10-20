package com.example.parcial2_lavadero.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.parcial2_lavadero.model.Servicios
import com.example.parcial2_lavadero.viewmodels.ServiciosViewModel

@Composable
fun ServiciosScreen(viewModel: ServiciosViewModel) {
    // Estado para los campos de entrada
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) } // Estado para manejar el mensaje de éxito
    var selectedServicio by remember { mutableStateOf<Servicios?>(null) } // Servicio seleccionado para editar

    // Obtener la lista de servicios desde el ViewModel
    val allServicios by viewModel.allServicios.observeAsState(emptyList())

    // Función para limpiar los campos de entrada
    fun clearFields() {
        nombre = ""
        precio = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre del Servicio") })
        TextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotEmpty() && precio.isNotEmpty()) {
                val precioValue = precio.toFloatOrNull()

                if (precioValue != null) {
                    val servicio = selectedServicio?.copy(
                        nombre = nombre,
                        precio = precioValue
                    ) ?: Servicios(
                        nombre = nombre,
                        precio = precioValue
                    )

                    if (selectedServicio == null) {
                        // Agregar nuevo servicio
                        viewModel.addServicio(servicio)
                    } else {
                        // Actualizar servicio existente
                        viewModel.updateServicio(servicio)
                    }

                    isSuccess = true
                    // Limpiar los campos después de guardar o actualizar el servicio
                    clearFields()
                    selectedServicio = null
                }
            }
        }) {
            Text(if (selectedServicio == null) "Guardar Servicio" else "Actualizar Servicio")
        }

        // Botón para cancelar la edición
        if (selectedServicio != null) {
            Button(onClick = {
                clearFields()
                selectedServicio = null
            }) {
                Text("Cancelar")
            }
        }

        // Mostrar mensaje de éxito
        if (isSuccess) {
            Text(text = "Servicio guardado exitosamente", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listar todos los servicios
        Text(text = "Lista de Servicios", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(allServicios) { servicio ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Al hacer clic, llenar los campos con la información del servicio para editarlo
                            nombre = servicio.nombre
                            precio = servicio.precio.toString()
                            selectedServicio = servicio
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = servicio.nombre, style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Precio: ${servicio.precio}", style = MaterialTheme.typography.bodySmall)
                        }

                        IconButton(onClick = {
                            viewModel.deleteServicio(servicio)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}
