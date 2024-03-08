package com.mobile.survey.surveypage

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.survey.R
import com.mobile.survey.surveypage.model.Option
import com.mobile.survey.surveypage.model.ScaleType
import com.mobile.survey.surveypage.model.SurveyPageState
import com.mobile.survey.surveypage.model.positiveList

@Composable
fun SurveyPage(
    enableColor: Boolean,
    surveyPageState: SurveyPageState,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = surveyPageState.question,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
        )
        OptionList(
            enableColor = enableColor,
            surveyPageState = surveyPageState,
            modifier = Modifier.border(width = 1.dp, color = Color.Black)
        )

    }

}


@Composable
fun OptionList(
    enableColor: Boolean,
    surveyPageState: SurveyPageState,
    modifier: Modifier = Modifier
) {

    var selectedOption by remember { mutableStateOf<Option?>(null) }

    if (surveyPageState.verticalSurvey) {
        LazyColumn(modifier = modifier) {
            items(surveyPageState.options.size) { index ->
                val option = surveyPageState.options[index]
                OptionItem(
                    enableColor = enableColor,
                    surveyPageState = surveyPageState,
                    option = option,
                    isSelected = selectedOption == option,
                    onSelected = { selectedOption = it }, scaleType = surveyPageState.scaleType
                )
            }
        }
    } else {
        LazyRow(modifier = modifier) {

            val newOption = surveyPageState.options.reversed()
            items(newOption.size) { index ->
                val option = newOption[index]
                OptionItem(
                    enableColor = enableColor,
                    surveyPageState = surveyPageState,
                    option = option,
                    isSelected = selectedOption == option,
                    onSelected = { selectedOption = it }, scaleType = surveyPageState.scaleType
                )
            }
        }
    }

}

@Composable
fun OptionItem(
    enableColor: Boolean,
    option: Option,
    isSelected: Boolean,
    onSelected: (Option) -> Unit,
    scaleType: ScaleType,
    surveyPageState: SurveyPageState
) {
    val interactionSource = remember { MutableInteractionSource() }
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val zoomFactor by animateFloatAsState(targetValue = if (isSelected) 1.3f else 1f, label = "")

    val color = if (enableColor) option.color else R.color.white
// Create a ColorMatrixColorFilter with the ColorMatrix
    val colorMatrix = remember { ColorMatrix().apply { setSaturation(0f) } } // Desaturate the image

    val colorFilter = remember { ColorMatrixColorFilter(colorMatrix) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .widthIn(min = 20.dp, max = 30.dp)
            .background(color = colorResource(id = color))
            .border(width = 2.dp, color = borderColor)
            .graphicsLayer(scaleX = zoomFactor, scaleY = zoomFactor)
            .clickable(interactionSource = interactionSource,
                indication = null,
                onClick = { onSelected(option) }),
        contentAlignment = Alignment.Center
    ) {
        if (!surveyPageState.textVisible) return
        RadioText(
            option = option,
            scaleType = scaleType
        )

    }


}


@Composable

fun RadioText(
    option: Option,
    scaleType: ScaleType,
) {
    val title = when (scaleType) {
        ScaleType.NEGATIVE -> {
            "-${option.title}"
        }

        ScaleType.NEUTRAL -> {
            option.detailedTitle
        }

        else -> option.title
    }
    Text(
        text = title,
        color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}


@Preview(name = "SurveyPageComposable")
@Composable
private fun PreviewSurveyPageComposableVertical() {
    Surface(color = Color.White) {
        SurveyPage(
            enableColor = false,
            surveyPageState = SurveyPageState(
                options = positiveList(),
                textVisible = true,
                verticalSurvey = true,
                scaleType = ScaleType.POSITIVE
            )
        )
    }
}

@Preview(name = "SurveyPageComposable")
@Composable
private fun PreviewSurveyPageComposableHorizontal() {
    Surface(color = Color.White) {
        SurveyPage(
            enableColor = false,
            surveyPageState =
            SurveyPageState(
                options = positiveList().reversed(),
                textVisible = true,
                verticalSurvey = false,
                scaleType = ScaleType.POSITIVE
            )
        )
    }
}