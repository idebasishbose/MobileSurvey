package com.mobile.survey.surveypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.survey.surveypage.model.GenericOption
import com.mobile.survey.surveypage.model.Option1
import com.mobile.survey.surveypage.model.SurveyPageState

@Composable
fun SurveyPage(
    surveyPageState: SurveyPageState,
    modifier: Modifier = Modifier
) {
    Text(text = "")
    Box(modifier) {
        OptionList(surveyPageState = surveyPageState)
    }
}


@Composable
fun OptionList(surveyPageState: SurveyPageState, modifier: Modifier = Modifier) {

    surveyPageState.apply {
        var selectedOption by remember { mutableStateOf<GenericOption?>(null) }

        LazyColumn {
            items(Option1.entries.size) { index ->
                val option = Option1.entries[index]

                OptionItem(
                    option = option,
                    isSelected = selectedOption == option,
                    onSelected = { selectedOption = it }
                )
            }
        }
    }

}

@Composable
fun OptionItem(
    option: Option1,
    isSelected: Boolean,
    onSelected: (Option1) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(option) }
            .padding(16.dp)
    ) {
        RadioButton(
            isSelected = isSelected,
            onSelected = { onSelected(option) },
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp)
        )
        Text(text = option.title)
    }
}

@Composable
fun RadioButton(
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
    iconUnchecked: ImageVector ,
    iconChecked: ImageVector,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    val selectedColor = if (isSelected) iconChecked else iconUnchecked

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(color = selectedColor)
            .clickable { onSelected() }
    ) {

    }
}


@Preview(name = "SurveyPageComposable")
@Composable
private fun PreviewSurveyPageComposable() {
    Surface(color = Color.White) {
        SurveyPage(SurveyPageState())
    }
}