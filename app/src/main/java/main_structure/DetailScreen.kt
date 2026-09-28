package main_structure

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController,
    matricula: String,
    nombre: String,
    carrera: String,
    turno: String,
    estatus: Boolean
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Confirmación de Registro", style = MaterialTheme.typography.headlineMedium)

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Matrícula: $matricula", style = MaterialTheme.typography.bodyLarge)
                    Text("Nombre: $nombre", style = MaterialTheme.typography.bodyLarge)
                    Text("Carrera: $carrera", style = MaterialTheme.typography.bodyLarge)
                    Text("Turno: $turno", style = MaterialTheme.typography.bodyLarge)
                    Text("Estatus: ${if (estatus) "Activo" else "Inactivo"}", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Formulario")
            }
        }
    }
}