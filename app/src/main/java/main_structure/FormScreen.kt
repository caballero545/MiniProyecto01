package main_structure

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import Data.PreferencesManager

@Composable
fun FormScreen(navController: NavController) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    var matricula by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("Ingeniería de Software") }
    var turno by remember { mutableStateOf("Matutino") }
    var estatus by remember { mutableStateOf(true) }

    var expanded by remember { mutableStateOf(false) }
    val opcionesCarrera = listOf("Ingeniería de Software", "Derecho", "Psicologia", "Comercio", "nanotecnologia", "industrial")

    LaunchedEffect(Unit) {
        matricula = preferencesManager.getMatricula()
        nombre = preferencesManager.getNombre()
        carrera = preferencesManager.getCarrera()
        turno = preferencesManager.getTurno()
        estatus = preferencesManager.getEstatus()
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Registro de Estudiantes", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = matricula,
                onValueChange = { matricula = it },
                label = { Text("Matrícula") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre Completo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = carrera,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Carrera") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    opcionesCarrera.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                carrera = opcion
                                expanded = false
                            }
                        )
                    }
                }
            }

            Text("Turno:", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (turno == "Matutino"),
                    onClick = { turno = "Matutino" }
                )
                Text("Matutino")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = (turno == "Vespertino"),
                    onClick = { turno = "Vespertino" }
                )
                Text("Vespertino")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Estatus (Activo/Inactivo):", style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = estatus,
                    onCheckedChange = { estatus = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (matricula.isNotBlank() && nombre.isNotBlank()) {
                        preferencesManager.saveStudentData(matricula, nombre, carrera, turno, estatus)
                        Toast.makeText(context, "Datos guardados localmente", Toast.LENGTH_SHORT).show()

                        navController.navigate("detailScreen/$matricula/$nombre/$carrera/$turno/$estatus")
                    } else {
                        Toast.makeText(context, "Llene los campos obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar y Ver Detalle")
            }
        }
    }
}