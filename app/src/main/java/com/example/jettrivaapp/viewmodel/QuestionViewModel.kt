package com.example.jettrivaapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettrivaapp.data.DataOrException
import com.example.jettrivaapp.model.QuestionItem
import com.example.jettrivaapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository): ViewModel(){
    private val data: MutableState<DataOrException<ArrayList<QuestionItem>,
            Boolean, Exception
            >> = mutableStateOf(
                DataOrException(null, true, Exception(""))
            )
    init {
        getAllQuestions()
    }
    public fun getData(): MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> {
        return data
    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if(data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }
}