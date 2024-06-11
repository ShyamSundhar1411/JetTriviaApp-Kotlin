package com.example.jettrivaapp.component

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettrivaapp.model.QuestionItem
import com.example.jettrivaapp.util.AppColors
import com.example.jettrivaapp.viewmodel.QuestionViewModel


@Composable
fun Questions(viewModel: QuestionViewModel,modifier: Modifier = Modifier){
    val questions = viewModel.getData().value.data?.toMutableList()
    val indexState = remember {
        mutableIntStateOf(0)
    }
    if (viewModel.getData().value.loading == true){
        CircularProgressIndicator()
    }else{

        if (questions != null) {
            QuestionDisplay(question = questions[0], questionIndex = indexState, viewModel, onNextClicked = {
                Log.d("Question","${indexState.intValue}")
            } )
        }
    }
    Log.d("Size","Questions: ${questions?.size} ")

}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    onNextClicked: (Int) -> Unit,
)
{
    val choicesState = remember(question){
        question.choices.toMutableList()
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
                QuestionTracker()
                DrawDottedLine(pathEffect = pathEffect)
                Column(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                ) {
                    Text(text = "What is this Question ?",
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
                            .height(45.dp)
                            .border(
                                width = 4.dp,
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
                            .background(color = Color.Transparent)
                        ) {

                        }
                    }

                }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun QuestionTracker(
    modifier: Modifier = Modifier,
    counter: Int = 10,
    maxQuestions: Int = 100
    ){
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(
            textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = AppColors.mLightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp
                )
            ) {
                append("Question $counter/")
                withStyle(style = SpanStyle(
                    color = AppColors.mLightGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )){
                    append("$maxQuestions")
                }
            }
        }
    },modifier = modifier.padding(15.dp)
    )
}
@Composable
fun DrawDottedLine(pathEffect: PathEffect){
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        ,
        contentDescription =  "Dotted Line"){
        drawLine(
           color = AppColors.mLightGray,
            start = Offset(0f,0f),
            end = Offset(size.width,0f),
            pathEffect = pathEffect
        )
    }
}