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
import com.example.parcial2_lavadero.model.Clientes
import com.example.parcial2_lavadero.viewmodels.ClientesViewModel

@Composable
fun ClientesScreen(viewModel: ClientesViewModel) {

    // Estado para los campos de entrada
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) } // Estado para manejar el mensaje de éxito
    var selectedCliente by remember { mutableStateOf<Clientes?>(null) } // Cliente seleccionado para editar

    // Obtener la lista de clientes desde el ViewModel
    val allClientes by viewModel.allClientes.observeAsState(emptyList())
    // Función para limpiar los campos de entrada
    fun clearFields() {
        nombre = ""
        apellido = ""
        telefono = ""
        email = ""
        direccion = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        TextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") })
        TextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotEmpty() && apellido.isNotEmpty()) {
                val cliente = selectedCliente?.copy(
                    nombre = nombre,
                    apellido = apellido,
                    telefono = telefono,
                    email = email,
                    direccion = direccion
                ) ?: Clientes(
                    nombre = nombre,
                    apellido = apellido,
                    telefono = telefono,
                    email = email,
                    direccion = direccion
                )

                if (selectedCliente == null) {
                    // Agregar nuevo cliente
                    viewModel.addCliente(cliente)
                } else {
                    // Actualizar cliente existente
                    viewModel.updateCliente(cliente)
                }

                isSuccess = true
                // Limpiar los campos después de guardar o actualizar el cliente
                clearFields()
                selectedCliente = null
            }
        }) {
            Text(if (selectedCliente == null) "Guardar Cliente" else "Actualizar Cliente")
        }

        // Botón para cancelar la edición
        if (selectedCliente != null) {
            Button(onClick = {
                clearFields()
                selectedCliente = null
            }) {
                Text("Cancelar")
            }
        }

        // Mostrar mensaje de éxito
        if (isSuccess) {
            Text(text = "Cliente guardado exitosamente", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listar todos los clientes
        Text(text = "Lista de Clientes", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(allClientes) { cliente ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Al hacer clic, llenar los campos con la información del cliente para editarlo
                            nombre = cliente.nombre
                            apellido = cliente.apellido
                            telefono = cliente.telefono
                            email = cliente.email
                            direccion = cliente.direccion
                            selectedCliente = cliente
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
                            Text(text = "${cliente.nombre} ${cliente.apellido}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Teléfono: ${cliente.telefono}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "Email: ${cliente.email}", style = MaterialTheme.typography.bodySmall)
                            Text(text = "Dirección: ${cliente.direccion}", style = MaterialTheme.typography.bodySmall)
                        }

                        IconButton(onClick = {
                            viewModel.deleteCliente(cliente)
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

