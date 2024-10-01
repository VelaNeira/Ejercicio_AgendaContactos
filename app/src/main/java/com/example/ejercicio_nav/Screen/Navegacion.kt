package com.example.ejercicio_nav.Screen

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

data class Contacto(var nombre: String, var apellido: String, var tel: String, var hobbie: String)

@Composable
fun navigationExample() {
    val navController = rememberNavController()
    var contactos by remember { mutableStateOf(listOf<Contacto>()) }

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController) { name, surname, phone, hobbie ->
                contactos = contactos + Contacto(name, surname, phone, hobbie)
            }
        }
        composable("screen_b") {
            ScreenB(navController, contactos,
                onDeleteContact = { contacto ->
                    contactos = contactos.filter { it != contacto }
                },
                onEditContact = { contacto ->
                    navController.navigate("screen_edit/${contactos.indexOf(contacto)}")
                }
            )
        }
        composable("screen_edit/{contactIndex}") { backStackEntry ->
            val contactIndex = backStackEntry.arguments?.getString("contactIndex")?.toInt() ?: -1
            if (contactIndex != -1) {
                ScreenEdit(
                    navController = navController, // Asegúrate de pasar el NavController
                    contacto = contactos[contactIndex],
                    onUpdateContact = { updatedContact ->
                        contactos = contactos.toMutableList().apply {
                            this[contactIndex] = updatedContact
                        }
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}