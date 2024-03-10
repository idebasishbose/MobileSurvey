package com.mobile.survey.surveypage.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.mobile.survey.surveypage.ArrowDirection

@Composable
fun MovingArrowAnimation(direction: ArrowDirection, painter: Painter, iconTint: Color) {
    var offsetY by remember { mutableFloatStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (direction == ArrowDirection.UP) -2f else 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    offsetY = offsetAnimation.dp.value

    Box(
        modifier = Modifier
            .size(80.dp)
            .offset(y = offsetY.dp)
            .padding(4.dp)
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.fillMaxSize()
        )
    }
}