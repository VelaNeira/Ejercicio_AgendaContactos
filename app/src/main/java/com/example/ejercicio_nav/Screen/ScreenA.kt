package com.example.ejercicio_nav.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenA(navController: NavController, onSaveContact: (String, String, String, String) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var tel by remember { mutableStateOf("") }
    var hobbie by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    // Validaciones
    val esNumerico = tel.all { it.isDigit() }
    val esTextoValido = { input: String -> input.all { it.isLetter() || it.isWhitespace() } }
    val esTelefonoValido = tel.length == 10 && esNumerico

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Agregar Contacto",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Input para Nombre
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            isError = mostrarError && !esTextoValido(nombre),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Gray
            )
        )
        if (mostrarError && !esTextoValido(nombre)) {
            Text(
                text = "Nombre solo puede contener letras.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input para Apellido
        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            isError = mostrarError && !esTextoValido(apellido),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Gray
            )
        )
        if (mostrarError && !esTextoValido(apellido)) {
            Text(
                text = "Apellido solo puede contener letras.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input para Teléfono
        TextField(
            value = tel,
            onValueChange = { if (it.length <= 10) tel = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = mostrarError && !esTelefonoValido,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Gray
            )
        )
        if (mostrarError && !esTelefonoValido) {
            Text(
                text = "El teléfono debe contener 10 números.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input para Hobbie
        TextField(
            value = hobbie,
            onValueChange = { hobbie = it },
            label = { Text("Hobbie") },
            isError = mostrarError && !esTextoValido(hobbie),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Gray
            )
        )
        if (mostrarError && !esTextoValido(hobbie)) {
            Text(
                text = "Hobbie solo puede contener letras.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para guardar contacto
        Button(
            onClick = {
                val camposValidos = esTextoValido(nombre) && esTextoValido(apellido) && esTelefonoValido && esTextoValido(hobbie)
                if (camposValidos) {
                    onSaveContact(nombre, apellido, tel, hobbie)
                    navController.navigate("screen_b")
                    mostrarError = false
                } else {
                    mostrarError = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Guardar contacto")
        }
    }
}
