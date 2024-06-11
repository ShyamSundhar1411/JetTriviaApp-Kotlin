package com.example.jettrivaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jettrivaapp.screens.TriviaHome
import com.example.jettrivaapp.ui.theme.JetTrivaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTrivaAppTheme(darkTheme = false) {
                TriviaHome()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTrivaAppTheme {

    }
}