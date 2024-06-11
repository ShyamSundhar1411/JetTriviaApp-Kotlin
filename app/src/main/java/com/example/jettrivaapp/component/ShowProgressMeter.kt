package com.example.jettrivaapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettrivaapp.util.AppColors

@Preview
@Composable
fun ShowProgressMeter(score: Int = 12, totalQuestions: Int = 4875) {
    val gradient = Brush.linearGradient(
        listOf(
            Color(0xFFF95075), Color(0XFFBE6BE5)
        )
    )
    val scoreMeterState = remember(score) {
        mutableFloatStateOf((score / 4875).toFloat())
    }
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mLightPurple, AppColors.mLightPurple
                    )
                ), shape = RoundedCornerShape(34.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50,
                    topEndPercent = 50
                )
            )
            .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(scoreMeterState.floatValue)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
            colors = ButtonDefaults.buttonColors(

                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Text(
                text = score.toString(), modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(23.dp),
                    )
                    .fillMaxHeight(0.87f)
                    .fillMaxWidth()
                    .padding(6.dp), color = AppColors.mOffWhite,
                textAlign = TextAlign.Center
            )
        }
    }
}