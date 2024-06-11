package com.example.jettrivaapp.component

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import com.example.jettrivaapp.util.AppColors
import com.example.jettrivaapp.viewmodel.QuestionViewModel


@Composable
fun Questions(viewModel: QuestionViewModel,modifier: Modifier = Modifier){
    val questions = viewModel.getData().value.data?.toMutableList()

    if (viewModel.getData().value.loading == true){
        CircularProgressIndicator()
    }else{

        QuestionDisplay()
    }
    Log.d("Size","Questions: ${questions?.size} ")

}
@Preview(showBackground = true)
@Composable
fun QuestionDisplay(){
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