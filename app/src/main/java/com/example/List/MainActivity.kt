package com.example.List

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    FrutList()
                }
            }
        }


data class Frut(
    val nombre: String,
    val descripcion: String,
    val image: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrutList() {
    var frutName by remember { mutableStateOf("") }
    var frutDescription by remember { mutableStateOf("") }
    var frutImage by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Cargar las frutas desde SharedPreferences al iniciar la app
    val fruts = remember {
        mutableStateListOf(*loadFruts(context).toTypedArray())  // Cargamos las frutas guardadas
    }

    var showModal by remember { mutableStateOf(false) }
    var selectedFrut by remember { mutableStateOf<Frut?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Campos para agregar una nueva fruta
        TextField(
            value = frutName,
            label = { Text(text = "Nombre:") },
            onValueChange = { frutName = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = frutDescription,
            label = { Text(text = "Descripción:") },
            onValueChange = { frutDescription = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = frutImage,
            label = { Text(text = "Image URL:") },
            onValueChange = { frutImage = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Botón para agregar una fruta
        Button(onClick = {
            if (frutName.isNotEmpty() && frutDescription.isNotEmpty() && frutImage.isNotEmpty()) {
                val newFrut = Frut(frutName, frutDescription, frutImage)
                fruts.add(newFrut)
                saveFruts(context, fruts)  // Guardamos la lista actualizada
                frutName = ""
                frutDescription = ""
                frutImage = ""
                Toast.makeText(context, "Fruta agregada correctamente", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Agregar")
        }

        // Lista de frutas
        LazyColumn {
            items(fruts) { frut ->
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            selectedFrut = frut
                            showModal = true
                        }
                ) {
                    Column(Modifier.padding(5.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = frut.image,
                                contentDescription = "Imagen de ${frut.nombre}",
                                modifier = Modifier.size(100.dp),
                                placeholder = rememberImagePainter("https://via.placeholder.com/150"),
                                error = rememberImagePainter("https://via.placeholder.com/150/ff0000/ffffff?text=Error")
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Nombre: ${frut.nombre}")
                                Text(text = "Descripción: ${frut.descripcion}")
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal con detalles de la fruta seleccionada
    if (showModal && selectedFrut != null) {
        AlertDialog(
            onDismissRequest = { showModal = false },
            title = { Text(text = "Detalles de la Fruta") },
            text = {
                selectedFrut?.let { frut ->
                    Column {
                        AsyncImage(
                            model = frut.image,
                            contentDescription = "Imagen de ${frut.nombre}",
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            placeholder = rememberImagePainter("https://via.placeholder.com/150"),
                            error = rememberImagePainter("https://via.placeholder.com/150/ff0000/ffffff?text=Error")
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Nombre: ${frut.nombre}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Descripción: ${frut.descripcion}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showModal = false }) {
                    Text(text = "Cerrar")
                }
            }
        )
    }
}


// Función para guardar las frutas en SharedPreferences
fun saveFruts(context: Context, fruts: List<Frut>) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("fruts_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(fruts)  // Convertimos la lista a JSON
    editor.putString("fruts_list", json)
    editor.apply()  // Guardamos los datos
}

// Función para cargar las frutas desde SharedPreferences
fun loadFruts(context: Context): List<Frut> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("fruts_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString("fruts_list", null)
    val type = object : TypeToken<List<Frut>>() {}.type
    return if (json != null) {
        gson.fromJson(json, type)  // Convertimos el JSON de vuelta a lista
    } else {
        emptyList()  // Si no hay datos, retornamos una lista vacía
    }
}


