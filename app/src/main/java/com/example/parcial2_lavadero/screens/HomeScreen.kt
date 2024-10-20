package com.example.parcial2_lavadero.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial2_lavadero.repository.ClientesRepository
import com.example.parcial2_lavadero.repository.RegistroLavadoRepository
import com.example.parcial2_lavadero.repository.ServiciosRepository
import com.example.parcial2_lavadero.repository.VehiculosRepository
import com.example.parcial2_lavadero.viewmodels.ClientesViewModel
import com.example.parcial2_lavadero.viewmodels.ClientesViewModelFactory
import com.example.parcial2_lavadero.viewmodels.RegistroLavadoViewModel
import com.example.parcial2_lavadero.viewmodels.RegistroLavadoViewModelFactory
import com.example.parcial2_lavadero.viewmodels.ServiciosViewModel
import com.example.parcial2_lavadero.viewmodels.ServiciosViewModelFactory
import com.example.parcial2_lavadero.viewmodels.VehiculosViewModel
import com.example.parcial2_lavadero.viewmodels.VehiculosViewModelFactory

@Composable
fun MainScreen(clientesRepository: ClientesRepository, vehiculosRepository: VehiculosRepository, serviciosRepository: ServiciosRepository, registroLavadoRepository: RegistroLavadoRepository) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("clientes") {
            val viewModelFactory = ClientesViewModelFactory(clientesRepository)
            val viewModel: ClientesViewModel = viewModel(factory = viewModelFactory) // Obtener el ViewModel aquí
            ClientesScreen(viewModel)
        }
        composable("vehiculos") {
            val viewModelFactory = VehiculosViewModelFactory(vehiculosRepository)
            val viewModel: VehiculosViewModel = viewModel(factory = viewModelFactory)
            VehiculosScreen(viewModel)
        }
        composable("servicios") {
            val viewModelFactory = ServiciosViewModelFactory(serviciosRepository)
            val viewModel: ServiciosViewModel = viewModel(factory = viewModelFactory)
            ServiciosScreen(viewModel)
        }
        composable("registroLavado") {
            val viewModelFactory = RegistroLavadoViewModelFactory(registroLavadoRepository)
            val viewModel: RegistroLavadoViewModel = viewModel(factory = viewModelFactory)
            RegistroLavadoScreen(viewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lavadero App") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { navController.navigate("clientes") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clientes")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("vehiculos") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Vehículos")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("servicios") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Servicios")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("registroLavado") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registro de Lavado")
            }
        }
    }
}
