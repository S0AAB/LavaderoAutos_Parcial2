package com.example.parcial2_lavadero.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial2_lavadero.model.Vehiculos
import com.example.parcial2_lavadero.viewmodels.VehiculosViewModel
import androidx.compose.ui.graphics.Color

@Composable
fun VehiculosScreen(viewModel: VehiculosViewModel) {
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var clienteId by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Validar entradas antes de proceder
            if (marca.isEmpty() || modelo.isEmpty() || placa.isEmpty() || color.isEmpty() || tipo.isEmpty() || clienteId.isEmpty()) {
                errorMessage = "Por favor, complete todos los campos."
            } else {
                errorMessage = "" // Limpiar mensaje de error
                val clienteIdValue = clienteId.toIntOrNull()

                if (clienteIdValue == null) {
                    errorMessage = "Por favor, ingrese un ID Cliente válido."
                } else {
                    // Lógica para guardar el vehículo
                    viewModel.addVehiculo(Vehiculos(marca = marca, modelo = modelo, placa = placa, color = color, tipo = tipo, cliente_id = clienteIdValue))
                    // Limpiar campos después de guardar
                    marca = ""
                    modelo = ""
                    placa = ""
                    color = ""
                    tipo = ""
                    clienteId = ""
                    errorMessage = "Vehículo guardado exitosamente."
                }
            }
        }) {
            Text("Guardar Vehículo")
        }
    }
}
