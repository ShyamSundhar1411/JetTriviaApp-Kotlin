package com.example.jettrivaapp.component

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.jettrivaapp.viewmodel.QuestionViewModel

@Composable
fun Questions(viewModel: QuestionViewModel){
    val questions = viewModel.getData().value.data?.toMutableList()
    if(viewModel.getData().value.loading == true){
        CircularProgressIndicator()
    }
    Log.d("Size","Questions: ${questions?.size} ")

}