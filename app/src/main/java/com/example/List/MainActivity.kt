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

    val fruts = remember { mutableStateListOf<Frut>(
        Frut("Manzana", "Fruta roja y deliciosa", "https://th.bing.com/th/id/R.8f5ff2e8c8efcf2e4e4730b39b91951d?rik=lI9W7xRnC8SDYg&riu=http%3a%2f%2fupload.wikimedia.org%2fwikipedia%2fcommons%2f1%2f15%2fRed_Apple.jpg&ehk=yc0sOMMcFxEbzUr6B6KnEj%2bAyx0nLaVXHkJ9iN73cUw%3d&risl=&pid=ImgRaw&r=0"),
        Frut("Banana", "Fruta amarilla y deliciosa", "https://th.bing.com/th/id/OIP.DzzBtp9wRuY1VocmOurZ7gHaJE?rs=1&pid=ImgDetMain"),
        Frut("Pera", "Fruta verde y jugosa", "https://th.bing.com/th/id/OIP.I3lWiCMDjz_tnMqhLxG85QHaIZ?rs=1&pid=ImgDetMain"),
        Frut("Naranja", "Fruta cítrica y jugosa", "https://th.bing.com/th/id/OIP.0-DQgL51QOZvBgFGsaJ5-AHaEA?rs=1&pid=ImgDetMain"),
        Frut("Uva", "Fruta morada y dulce", "https://th.bing.com/th/id/OIP.SfoYCSkwGW4RPKNRARGW-gAAAA?rs=1&pid=ImgDetMain"),
        Frut("Fresa", "Fruta roja y jugosa", "https://th.bing.com/th/id/R.0f3d2d5bf998fd5a6c8684c04995761f?rik=oPoTyhTEogEuMA&riu=http%3a%2f%2flaguiadelasvitaminas.com%2fwp-content%2fuploads%2f2014%2f08%2ffresas.jpg&ehk=XntkY5xewOPm5li95W%2fcUzNakrfNBm1sC1g22Fvw4Dg%3d&risl=&pid=ImgRaw&r=0"),
        Frut("Kiwi", "Fruta verde y jugosa", "https://cdn.britannica.com/45/126445-050-4C0FA9F6/Kiwi-fruit.jpg"),
        Frut("Mango", "Fruta naranja y jugosa", "https://th.bing.com/th/id/R.f09b0f9c3c7aee1f4f770629d1be2726?rik=oW%2bHo8mkTPQsoA&pid=ImgRaw&r=0"),
        Frut("Piña", "Fruta amarilla y jugosa", "https://th.bing.com/th/id/OIP.91twXM305jgUp_5Vde35PgHaFL?rs=1&pid=ImgDetMain"),
        Frut("Sandía", "Fruta roja y jugosa", "https://th.bing.com/th/id/OIP.QF4WITnd-5FATIX73HocnwHaHa?rs=1&pid=ImgDetMain"),
        Frut("Melón", "Fruta amarilla y jugosa", "https://th.bing.com/th/id/OIP.PRQupjakK7fyV8U_8jZ8_gHaHa?rs=1&pid=ImgDetMain"),
        Frut("Cereza", "Fruta roja y dulce", "https://th.bing.com/th/id/OIP.ulM5yj0H5mXNjs-HUkwR8AHaHe?rs=1&pid=ImgDetMain"),
        Frut("Durazno", "Fruta naranja y jugosa", "https://th.bing.com/th/id/R.176871874a02513876a75cd3ecbf1297?rik=hTOW01OH%2be5J7A&riu=http%3a%2f%2f1.bp.blogspot.com%2f-dc_GvMXfiuY%2fVOGPKlJ9dFI%2fAAAAAAAABp4%2fSisQ8pRmehE%2fs1600%2fdurazno-01.jpg&ehk=fBADsgOBc6ghaDT%2frm4esGk%2bplaN6oZRawXWqeISjuw%3d&risl=&pid=ImgRaw&r=0"),
        Frut("Mora", "Fruta morada y dulce", "https://th.bing.com/th/id/OIP.l_yTodctSEFy_5b68yg__gHaGc?rs=1&pid=ImgDetMain"),
    ) }

    val context = LocalContext.current

    var showModal by remember { mutableStateOf(false) }
    var selectedFrut by remember { mutableStateOf<Frut?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Campo de texto para el nombre de la fruta
        TextField(
            value = frutName,
            label = { Text(text = "Nombre:") },
            onValueChange = { frutName = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo de texto para la descripción de la fruta
        TextField(
            value = frutDescription,
            label = { Text(text = "Descripción:") },
            onValueChange = { frutDescription = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo de texto para la URL de la imagen
        TextField(
            value = frutImage,
            label = { Text(text = "Image URL:") },
            onValueChange = { frutImage = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Botón para agregar la fruta
        Button(onClick = {
            if (frutName.isNotEmpty() && frutDescription.isNotEmpty() && frutImage.isNotEmpty()) {
                val newFrut = Frut(frutName, frutDescription, frutImage)
                fruts.add(newFrut)
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
                            selectedFrut = frut // Asignamos la fruta seleccionada
                            showModal = true  // Mostramos el modal
                        }
                ) {
                    Column(Modifier.padding(5.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = frut.image,
                                contentDescription = "Imagen de ${frut.nombre}",
                                modifier = Modifier.size(100.dp),
                                placeholder = rememberImagePainter("https://via.placeholder.com/150"), // Placeholder por defecto
                                error = rememberImagePainter("https://via.placeholder.com/150/ff0000/ffffff?text=Error") // Placeholder de error
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
            onDismissRequest = { showModal = false },  // Cerrar el modal
            title = { Text(text = "Detalles de la Fruta") },
            text = {
                selectedFrut?.let { frut ->
                    Column {
                        AsyncImage(
                            model = frut.image,
                            contentDescription = "Imagen de ${frut.nombre}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            placeholder = rememberImagePainter("https://via.placeholder.com/150"),  // Placeholder por defecto
                            error = rememberImagePainter("https://via.placeholder.com/150/ff0000/ffffff?text=Error") // Placeholder de error
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

