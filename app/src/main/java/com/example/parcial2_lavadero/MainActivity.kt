package com.example.parcial2_lavadero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.parcial2_lavadero.database.AppDatabase // Importa tu base de datos
import com.example.parcial2_lavadero.repository.ClientesRepository
import com.example.parcial2_lavadero.repository.RegistroLavadoRepository
import com.example.parcial2_lavadero.repository.ServiciosRepository
import com.example.parcial2_lavadero.repository.VehiculosRepository
import com.example.parcial2_lavadero.screens.MainScreen
import com.example.parcial2_lavadero.ui.theme.Parcial2_LavaderoTheme

class MainActivity : ComponentActivity() {
    private lateinit var appDatabase: AppDatabase // Declaración de la base de datos

    private lateinit var clientesRepository: ClientesRepository
    private lateinit var vehiculosRepository: VehiculosRepository
    private lateinit var serviciosRepository: ServiciosRepository
    private lateinit var registroLavadoRepository: RegistroLavadoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa la base de datos
        appDatabase = AppDatabase.getDatabase(applicationContext)

        // Inicializa los repositorios
        clientesRepository = ClientesRepository(appDatabase.clientesDao()) // Pasa el DAO correspondiente
        vehiculosRepository = VehiculosRepository(appDatabase.vehiculosDao()) // Pasa el DAO correspondiente
        serviciosRepository = ServiciosRepository(appDatabase.serviciosDao()) // Pasa el DAO correspondiente
        registroLavadoRepository = RegistroLavadoRepository(appDatabase.registroLavadoDao()) // Pasa el DAO correspondiente

        setContent {
            Parcial2_LavaderoTheme { // Aplica tu tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llama a MainScreen aquí, pasando los repositorios
                    MainScreen(
                        clientesRepository = clientesRepository,
                        vehiculosRepository = vehiculosRepository,
                        serviciosRepository = serviciosRepository,
                        registroLavadoRepository = registroLavadoRepository
                    )
                }
            }
        }
    }
}
