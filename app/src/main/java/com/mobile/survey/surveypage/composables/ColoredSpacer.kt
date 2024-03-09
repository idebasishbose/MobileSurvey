package com.mobile.survey.surveypage.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColoredSpacer(color: Color, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(20.dp))
    Box(
        modifier = modifier
            .height(1.dp)
            .background(color)
    )
    Spacer(modifier = modifier.height(20.dp))

}