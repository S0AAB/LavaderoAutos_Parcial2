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
import com.example.parcial2_lavadero.model.RegistroLavado
import com.example.parcial2_lavadero.viewmodels.RegistroLavadoViewModel
import java.text.SimpleDateFormat
import java.util.*
@Composable
fun RegistroLavadoScreen(viewModel: RegistroLavadoViewModel) {
    var fechaLavado by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var precioTotal by remember { mutableStateOf("") }
    var vehiculoId by remember { mutableStateOf("") }
    var servicioId by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var selectedRegistroLavado by remember { mutableStateOf<RegistroLavado?>(null) }

    // Obtener la lista de registros de lavado desde el ViewModel
    val allRegistrosLavado by viewModel.allRegistrosLavado.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = fechaLavado, onValueChange = { fechaLavado = it }, label = { Text("Fecha (YYYY-MM-DD)") })
        TextField(value = horaInicio, onValueChange = { horaInicio = it }, label = { Text("Hora Inicio (HH:MM)") })
        TextField(value = horaFin, onValueChange = { horaFin = it }, label = { Text("Hora Fin (HH:MM)") })
        TextField(value = precioTotal, onValueChange = { precioTotal = it }, label = { Text("Precio Total") })
        TextField(value = vehiculoId, onValueChange = { vehiculoId = it }, label = { Text("ID Vehículo") })
        TextField(value = servicioId, onValueChange = { servicioId = it }, label = { Text("ID Servicio") })

        // Mostrar mensaje de error si es necesario
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        // Mostrar mensaje de éxito
        if (isSuccess) {
            Text(text = "Registro de lavado guardado exitosamente", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Validar entradas antes de proceder
            if (fechaLavado.isEmpty() || horaInicio.isEmpty() || horaFin.isEmpty() || precioTotal.isEmpty() || vehiculoId.isEmpty() || servicioId.isEmpty()) {
                errorMessage = "Por favor, complete todos los campos."
                isSuccess = false
            } else {
                errorMessage = ""
                val vehiculoIdValue = vehiculoId.toIntOrNull()
                val servicioIdValue = servicioId.toIntOrNull()
                val precioTotalValue = precioTotal.toFloatOrNull()
                val fechaLavadoValue = fechaLavado.toLongOrNull()

                if (vehiculoIdValue == null || servicioIdValue == null || precioTotalValue == null || fechaLavadoValue == null) {
                    errorMessage = "Por favor, ingrese valores válidos."
                    isSuccess = false
                } else {
                    // Lógica para guardar el registro de lavado
                    val registroLavado = selectedRegistroLavado?.copy(
                        fechaLavado = fechaLavadoValue,
                        horaInicio = horaInicio,
                        horaFin = horaFin,
                        precioTotal = precioTotalValue,
                        vehiculo_id = vehiculoIdValue,
                        servicio_id = servicioIdValue
                    ) ?: RegistroLavado(
                        fechaLavado = fechaLavadoValue,
                        horaInicio = horaInicio,
                        horaFin = horaFin,
                        precioTotal = precioTotalValue,
                        vehiculo_id = vehiculoIdValue,
                        servicio_id = servicioIdValue
                    )

                    if (selectedRegistroLavado == null) {
                        viewModel.addRegistroLavado(registroLavado)
                    } else {
                        viewModel.updateRegistroLavado(registroLavado)
                    }

                    // Limpiar campos después de guardar o actualizar
                    fechaLavado = ""
                    horaInicio = ""
                    horaFin = ""
                    precioTotal = ""
                    vehiculoId = ""
                    servicioId = ""
                    isSuccess = true
                    selectedRegistroLavado = null
                }
            }
        }) {
            Text(if (selectedRegistroLavado == null) "Guardar Registro" else "Actualizar Registro")
        }

        // Botón para cancelar la edición
        if (selectedRegistroLavado != null) {
            Button(onClick = {
                // Limpiar campos y reiniciar selección
                fechaLavado = ""
                horaInicio = ""
                horaFin = ""
                precioTotal = ""
                vehiculoId = ""
                servicioId = ""
                selectedRegistroLavado = null
            }) {
                Text("Cancelar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listar todos los registros de lavado
        Text(text = "Lista de Registros de Lavado", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(allRegistrosLavado) { registro ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Al hacer clic, llenar los campos con la información del registro de lavado para editarlo
                            fechaLavado = registro.fechaLavado.toString()
                            horaInicio = registro.horaInicio
                            horaFin = registro.horaFin
                            precioTotal = registro.precioTotal.toString()
                            vehiculoId = registro.vehiculo_id.toString()
                            servicioId = registro.servicio_id.toString()
                            selectedRegistroLavado = registro
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
                            Text(text = "ID: ${registro.id} Fecha: ${registro.fechaLavado}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Hora: ${registro.horaInicio} - ${registro.horaFin}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "Precio: $${registro.precioTotal}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "ID vehiculo: ${registro.vehiculo_id} ID servicio:${registro.servicio_id}", style = MaterialTheme.typography.bodySmall)

                        }

                        IconButton(onClick = {
                            viewModel.deleteRegistroLavado(registro)
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
