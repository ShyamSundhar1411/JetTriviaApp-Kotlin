package com.example.jettrivaapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettrivaapp.model.Question
import com.example.jettrivaapp.ui.theme.JetTrivaAppTheme
import com.example.jettrivaapp.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTrivaAppTheme {
                TriviaApp()
            }
        }
    }
}

@Composable
fun TriviaApp(viewModel: QuestionViewModel = hiltViewModel()){
    Questions(viewModel)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(text = "Trivia App", modifier = Modifier.padding(innerPadding))
    }
}
@Composable
fun Questions(viewModel: QuestionViewModel){
    val questions = viewModel.getData().value.data?.toMutableList()
    Log.d("Questions","Questions: ${questions?.size} ")
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTrivaAppTheme {

    }
}