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
import com.example.parcial2_lavadero.model.RegistrosLavadoConDetalles
import com.example.parcial2_lavadero.viewmodels.RegistroLavadoViewModel

@Composable
fun RegistroLavadoScreen(viewModel: RegistroLavadoViewModel) {
    var fechaLavado by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var precioTotal by remember { mutableStateOf("") }
    var vehiculoId by remember { mutableStateOf("") } // Cambiar a ID en lugar de placa
    var servicioId by remember { mutableStateOf("") }
    var selectedRegistro by remember { mutableStateOf<RegistrosLavadoConDetalles?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    // Cargar la lista de registros de lavado con detalles
    val registrosLavadoConDetalles by viewModel.registrosLavadoConDetalles.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.cargarRegistrosLavadoConDetalles()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Lista de registros de lavado con detalles
        LazyColumn {
            items(registrosLavadoConDetalles) { registroConDetalles ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedRegistro = registroConDetalles
                            // Cargar los datos del registro seleccionado para editar
                            fechaLavado = registroConDetalles.registroLavado.fechaLavado.toString()
                            horaInicio = registroConDetalles.registroLavado.horaInicio
                            horaFin = registroConDetalles.registroLavado.horaFin
                            precioTotal = registroConDetalles.registroLavado.precioTotal.toString()
                            vehiculoId = registroConDetalles.registroLavado.vehiculo_id.toString() // Cambiado a ID
                            servicioId = registroConDetalles.registroLavado.servicio_id.toString()
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Fecha: ${registroConDetalles.registroLavado.fechaLavado} - Vehículo ID: ${registroConDetalles.registroLavado.vehiculo_id} - Precio: ${registroConDetalles.registroLavado.precioTotal}")
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.deleteRegistroLavado(registroConDetalles.registroLavado) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campos para agregar o editar un registro de lavado
        TextField(
            value = fechaLavado,
            onValueChange = { fechaLavado = it },
            label = { Text("Fecha de Lavado (timestamp)") }
        )
        TextField(
            value = horaInicio,
            onValueChange = { horaInicio = it },
            label = { Text("Hora Inicio (HH:MM)") }
        )
        TextField(
            value = horaFin,
            onValueChange = { horaFin = it },
            label = { Text("Hora Fin (HH:MM)") }
        )
        TextField(
            value = precioTotal,
            onValueChange = { precioTotal = it },
            label = { Text("Precio Total") }
        )
        TextField(
            value = vehiculoId,
            onValueChange = { vehiculoId = it },
            label = { Text("ID del Vehículo") } // Cambiar a ID
        )
        TextField(
            value = servicioId,
            onValueChange = { servicioId = it },
            label = { Text("ID Servicio") }
        )

        // Mostrar mensaje de error si es necesario
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar o actualizar el registro
        Button(onClick = {
            // Validar entradas antes de proceder
            if (fechaLavado.isEmpty() || horaInicio.isEmpty() || horaFin.isEmpty() || precioTotal.isEmpty() || vehiculoId.isEmpty() || servicioId.isEmpty()) {
                errorMessage = "Por favor, complete todos los campos."
            } else {
                errorMessage = "" // Limpiar mensaje de error

                // Modificar la creación o actualización del registro
                val registro = selectedRegistro?.registroLavado?.copy(
                    fechaLavado = fechaLavado.toLongOrNull() ?: 0L,
                    horaInicio = horaInicio,
                    horaFin = horaFin,
                    precioTotal = precioTotal.toFloatOrNull() ?: 0f,
                    vehiculo_id = vehiculoId.toIntOrNull() ?: 0, // Usar ID directamente
                    servicio_id = servicioId.toIntOrNull() ?: 0
                ) ?: RegistroLavado(
                    fechaLavado = fechaLavado.toLongOrNull() ?: 0L,
                    horaInicio = horaInicio,
                    horaFin = horaFin,
                    precioTotal = precioTotal.toFloatOrNull() ?: 0f,
                    vehiculo_id = vehiculoId.toIntOrNull() ?: 0, // Usar ID directamente
                    servicio_id = servicioId.toIntOrNull() ?: 0
                )

                if (selectedRegistro == null) {
                    // Si no hay un registro seleccionado, se crea uno nuevo
                    viewModel.addRegistroLavado(registro)
                } else {
                    // Si hay un registro seleccionado, se actualiza
                    viewModel.updateRegistroLavado(registro)
                    selectedRegistro = null
                }

                // Limpiar campos después de guardar
                fechaLavado = ""
                horaInicio = ""
                horaFin = ""
                precioTotal = ""
                vehiculoId = ""
                servicioId = ""
            }
        }) {
            Text(if (selectedRegistro == null) "Guardar Registro de Lavado" else "Actualizar Registro de Lavado")
        }
    }
}

