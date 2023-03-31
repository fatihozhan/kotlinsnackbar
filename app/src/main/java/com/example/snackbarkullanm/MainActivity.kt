package com.example.snackbarkullanm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.snackbarkullanm.ui.theme.SnackBarKullanımıTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarKullanımıTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        content = { it ->
            it
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    scope.launch { scaffoldState.snackbarHostState.showSnackbar("Merhaba") }
                }) {
                    Text(text = "Varsayılan")
                }
                Button(onClick = {
                    scope.launch {
                        val sb = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Silmek İster Misin?",
                            actionLabel = "Evet"
                        )
                        if (sb == SnackbarResult.ActionPerformed) {
                            scaffoldState.snackbarHostState.showSnackbar("Silindi")
                        }
                    }
                }) {
                    Text(text = "Snackbar Action")
                }
                Button(onClick = {
                    scope.launch {
                        val sb = scaffoldState.snackbarHostState.showSnackbar(
                            message = "İnternet Bağlantısı Yok?",
                            actionLabel = "Tekrar Dene"
                        )
                        if (sb == SnackbarResult.ActionPerformed) {
                            scaffoldState.snackbarHostState.showSnackbar("Tekrar Denendi")
                        }
                    }
                }) {
                    Text(text = "Snackbar Özel")
                }
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) {
                Snackbar(
                    backgroundColor = Color.White,
                    contentColor = Color.Blue,
                    actionColor = Color.Red,
                    snackbarData = it
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackBarKullanımıTheme {
        Sayfa()
    }
}