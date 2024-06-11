package com.example.jettrivaapp.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettrivaapp.model.QuestionItem
import com.example.jettrivaapp.util.AppColors
import com.example.jettrivaapp.viewmodel.QuestionViewModel


@Composable
fun Questions(viewModel: QuestionViewModel,modifier: Modifier = Modifier){
    val questions = viewModel.getData().value.data?.toMutableList()
    val questionIndexState = remember {
        mutableIntStateOf(0)
    }
    if (viewModel.getData().value.loading == true){
        CircularProgressIndicator()
    }else{
        val question = try{
            questions?.get(questionIndexState.intValue)
        }catch(ex: Exception){
            null
        }
        if (questions != null) {
            QuestionDisplay(question = question!!,
                questionIndexState,
                viewModel){
                questionIndexState.intValue += 1
                
            }
        }
    }
    Log.d("Size","Questions: ${questions?.size} ")

}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    onNextClicked: (Int) -> Unit = {},
)
{
    val choicesState = remember(question){
        question.choices.toMutableList()
    }
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val scoreState = remember {
        mutableIntStateOf(0)
    }
    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == question.answer
            if (correctAnswerState.value == true){
                scoreState.intValue+=1
            }

        }
    }
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f,10f),0f)
    Surface(modifier =
    Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp),
        color = AppColors.mDarkPurple

    ) {
        Column(modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
            ) {
                if(questionIndex.value >= 4){
                    viewModel.getTotalQuestionCount()
                        ?.let { ShowProgressMeter(score = scoreState.intValue, totalQuestions = it) }
                }

            viewModel.getTotalQuestionCount()?.let {
                QuestionTracker(counter = questionIndex.value+1,
                    maxQuestions = it
                )
            }

                DrawDottedLine(pathEffect = pathEffect)
                Column(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                ) {
                    Text(
                        text = question.question,
                        modifier = Modifier
                            .padding(6.dp)
                            .align(alignment = Alignment.Start)
                            .fillMaxHeight(0.3f)
                        ,

                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp,
                        color = Color.White
                    )
                    choicesState.forEachIndexed { index, choice ->
                        Row(modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(55.dp)
                            .border(
                                width = 10.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(AppColors.mDarkPurple, AppColors.mDarkPurple)
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,

                                    topEndPercent = 50,
                                    bottomEndPercent = 50,
                                    bottomStartPercent = 50
                                )
                            )
                            .background(color = Color.Transparent),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (
                                        answerState.value == index
                                        ), onClick = {
                                    updateAnswer(index)

                                },
                                modifier = Modifier.padding(start = 16.dp),
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = if (correctAnswerState.value == true
                                        && index == answerState.value
                                    ) Color.Green.copy(alpha = 0.3f)
                                    else {
                                        Color.Red.copy(alpha = 0.3f)
                                    }
                                )
                            )
                            val annotatedString = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        color = if (correctAnswerState.value == true
                                            && index == answerState.value
                                        ) Color.Green
                                        else if (correctAnswerState.value == false
                                            && index == answerState.value
                                        ) {
                                            Color.Red
                                        } else {
                                            AppColors.mOffWhite
                                        }
                                    ))
                                {
                                    append(choice)
                                }
                            }
                                Text(text = annotatedString, modifier = Modifier.padding(6.dp))


                        }
                    }
                    Button(onClick = { onNextClicked(questionIndex.value) },
                        modifier = Modifier
                            .padding(3.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(34.dp),
                        colors = ButtonDefaults
                            .buttonColors(
                                AppColors.mLightBlue
                            )
                        ) {
                            Text(text = "Next",
                                modifier = Modifier.padding(4.dp),
                                color = AppColors.mOffWhite,
                                fontSize = 17.sp,

                                )
                        }

                }
        }
    }
}

