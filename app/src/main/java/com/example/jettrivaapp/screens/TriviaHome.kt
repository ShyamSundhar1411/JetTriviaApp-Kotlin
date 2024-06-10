package com.example.jettrivaapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettrivaapp.component.Questions
import com.example.jettrivaapp.viewmodel.QuestionViewModel

@Composable
fun TriviaHome(viewModel: QuestionViewModel = hiltViewModel()){
    Questions(viewModel)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(text = "Trivia App", modifier = Modifier.padding(innerPadding))
    }
}