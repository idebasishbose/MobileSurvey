package com.mobile.survey.surveypage

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.survey.R
import com.mobile.survey.surveypage.model.GenericOption
import com.mobile.survey.surveypage.model.Option1
import com.mobile.survey.surveypage.model.SurveyPageState

@Composable
fun SurveyPage(
    surveyPageState: SurveyPageState, modifier: Modifier = Modifier
) {
//    Card(modifier = Modifier.clip(shape = RoundedCornerShape(size = 4.dp))) {
    Column(
        verticalArrangement = Arrangement.SpaceAround, modifier = modifier
    ) {

        Text(
            text = surveyPageState.question,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
        )
        OptionList(
            surveyPageState = surveyPageState,
            modifier = Modifier.border(width = 1.dp, color = Color.Black)
        )
    }
//    }
}


@Composable
fun OptionList(surveyPageState: SurveyPageState, modifier: Modifier = Modifier) {

    surveyPageState.apply {
        var selectedOption by remember { mutableStateOf<GenericOption?>(null) }

        LazyColumn(modifier = modifier) {
            items(options.size) { index ->
                val option = options[index]
                if (option is Option1) {
                    OptionItem(option = option,
                        isSelected = selectedOption == option,
                        onSelected = { selectedOption = it })
                }
            }
        }
    }

}

@Composable
fun OptionItem(
    option: Option1, isSelected: Boolean, onSelected: (Option1) -> Unit
) {
//    val selectedColor = if (isSelected) R.color.lightGray else option.color
    val interactionSource = remember { MutableInteractionSource() }
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val zoomFactor by animateFloatAsState(targetValue = if (isSelected) 1.2f else 1f)


    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(color = colorResource(id = option.color))
        .border(width = 2.dp, color = borderColor)
        .clickable(interactionSource = interactionSource,
            indication = null,
            onClick = { onSelected(option) }), verticalAlignment = Alignment.CenterVertically
    ) {

        RadioText(isSelected = isSelected, onSelected =  onSelected , option = option)

    }


}


@Composable

fun RadioText(
    option: Option1,
    isSelected: Boolean,
    onSelected: (Option1) -> Unit,
    modifier: Modifier = Modifier,
) {

    Text(
        text = option.title,
        color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}


@Preview(name = "SurveyPageComposable")
@Composable
private fun PreviewSurveyPageComposable() {
    Surface(color = Color.White) {
        SurveyPage(SurveyPageState())
    }
}