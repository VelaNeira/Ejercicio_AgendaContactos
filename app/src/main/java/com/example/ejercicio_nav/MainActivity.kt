package com.example.ejercicio_nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ejercicio_nav.Screen.navigationExample

// La actividad principal, donde se establece el contenido de la app.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establecemos el contenido de la pantalla usando la función de navegación.
        setContent {
            navigationExample()
        }
    }
}