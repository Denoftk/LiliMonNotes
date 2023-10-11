package com.HunterSoul.notas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.HunterSoul.notas.R
import com.HunterSoul.notas.model.Nota
import com.HunterSoul.notas.ui.theme.NotasAppTheme

@Composable
fun HomeScreen(
    notasUiState: NotasUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (notasUiState) {
        is NotasUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is NotasUiState.Success -> NotasGridScreen(notas = notasUiState.notas, modifier)

        is NotasUiState.Error -> ErrorScreen( retryAction,   modifier = modifier.fillMaxSize())
    }
}

@Composable
fun NotasGridScreen(notas: List<Nota>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(215.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = notas ) {
                nota ->
                    NotaCard(nota = nota,
                    modifier = modifier
                        .padding(4.dp).fillMaxWidth().aspectRatio(1.5f)
            )
        }
    }
}

// Importar los ciomponentes faltantes
@Composable
fun NotaCard(nota: Nota, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier ,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier= Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center

        ){
                Text(
                    text = ""+nota.id,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = ""+nota.titulo,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = ""+nota.contenido,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(4.dp)
                )

        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
   // Image(
     //   modifier = modifier.size(200.dp),
       // painter = painterResource(R.drawable.loading_img),
        //contentDescription = stringResource(R.string.loading)
    //)
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Image(
          //  painter = painterResource(id = R.string.connection_error), contentDescription = ""
       // )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }

    }
}


/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(notas: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = notas)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    NotasAppTheme{
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}