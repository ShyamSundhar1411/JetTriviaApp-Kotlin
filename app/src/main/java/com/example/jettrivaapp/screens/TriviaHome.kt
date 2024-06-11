package com.example.jettrivaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettrivaapp.component.Questions
import com.example.jettrivaapp.util.AppColors
import com.example.jettrivaapp.viewmodel.QuestionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaHome(viewModel: QuestionViewModel = hiltViewModel()){

    Scaffold(

        topBar =
                {
                    TopAppBar(title = {
                        Text(text = "Jet Trivia App",
                            color = Color.White
                            )

                    },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = AppColors.mDarkPurple
                        )
                    )
                }
                ,
        modifier = Modifier.fillMaxSize().background(AppColors.mDarkPurple), containerColor = AppColors.mDarkPurple) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Questions(viewModel)
        }

    }
}