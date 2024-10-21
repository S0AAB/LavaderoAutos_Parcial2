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
import com.example.parcial2_lavadero.model.Vehiculos
import com.example.parcial2_lavadero.viewmodels.VehiculosViewModel

@Composable
fun VehiculosScreen(viewModel: VehiculosViewModel) {
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var clienteId by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) } // Estado para manejar el mensaje de éxito
    var errorMessage by remember { mutableStateOf("") }
    var selectedVehiculo by remember { mutableStateOf<Vehiculos?>(null) } // Vehículo seleccionado para editar

    // Obtener la lista de vehículos desde el ViewModel
    val allVehiculos by viewModel.allVehiculos.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = marca, onValueChange = { marca = it }, label = { Text("Marca") })
        TextField(value = modelo, onValueChange = { modelo = it }, label = { Text("Modelo") })
        TextField(value = placa, onValueChange = { placa = it }, label = { Text("Placa") })
        TextField(value = color, onValueChange = { color = it }, label = { Text("Color") })
        TextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo") })
        TextField(value = clienteId, onValueChange = { clienteId = it }, label = { Text("ID Cliente") })

        // Mostrar mensaje de error si es necesario
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        // Mostrar mensaje de éxito
        if (isSuccess) {
            Text(text = "Vehículo guardado exitosamente", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Validar entradas antes de proceder
            if (marca.isEmpty() || modelo.isEmpty() || placa.isEmpty() || color.isEmpty() || tipo.isEmpty() || clienteId.isEmpty()) {
                errorMessage = "Por favor, complete todos los campos."
                isSuccess = false
            } else {
                errorMessage = "" // Limpiar mensaje de error
                val clienteIdValue = clienteId.toIntOrNull()

                if (clienteIdValue == null) {
                    errorMessage = "Por favor, ingrese un ID Cliente válido."
                    isSuccess = false
                } else {
                    // Lógica para guardar el vehículo
                    val vehiculo = selectedVehiculo?.copy(
                        marca = marca,
                        modelo = modelo,
                        placa = placa,
                        color = color,
                        tipo = tipo,
                        cliente_id = clienteIdValue
                    ) ?: Vehiculos(
                        marca = marca,
                        modelo = modelo,
                        placa = placa,
                        color = color,
                        tipo = tipo,
                        cliente_id = clienteIdValue
                    )

                    if (selectedVehiculo == null) {
                        viewModel.addVehiculo(vehiculo)
                    } else {
                        viewModel.updateVehiculo(vehiculo)
                    }

                    // Limpiar campos después de guardar o actualizar
                    marca = ""
                    modelo = ""
                    placa = ""
                    color = ""
                    tipo = ""
                    clienteId = ""
                    isSuccess = true
                    selectedVehiculo = null // Reiniciar selección
                }
            }
        }) {
            Text(if (selectedVehiculo == null) "Guardar Vehículo" else "Actualizar Vehículo")
        }

        // Botón para cancelar la edición
        if (selectedVehiculo != null) {
            Button(onClick = {
                // Limpiar campos y reiniciar selección
                marca = ""
                modelo = ""
                placa = ""
                color = ""
                tipo = ""
                clienteId = ""
                selectedVehiculo = null
            }) {
                Text("Cancelar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listar todos los vehículos
        Text(text = "Lista de Vehículos", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(allVehiculos) { vehiculo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Al hacer clic, llenar los campos con la información del vehículo para editarlo
                            marca = vehiculo.marca
                            modelo = vehiculo.modelo
                            placa = vehiculo.placa
                            color = vehiculo.color
                            tipo = vehiculo.tipo
                            clienteId = vehiculo.cliente_id.toString()
                            selectedVehiculo = vehiculo
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
                            Text(text = "ID:${vehiculo.id} ${vehiculo.marca} ${vehiculo.modelo}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Placa: ${vehiculo.placa}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "Color: ${vehiculo.color}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "Tipo: ${vehiculo.tipo}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "ID Cliente: ${vehiculo.cliente_id}", style = MaterialTheme.typography.bodySmall)
                        }

                        IconButton(onClick = {
                            viewModel.deleteVehiculo(vehiculo)
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
