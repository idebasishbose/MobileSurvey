package com.mobile.survey.surveypage.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RevealDotIndicator2(
    pagerState: PagerState,
) {
    val circleSpacing = 8.dp
    val circleSize = 20.dp
    val innerCircle = 14.dp
    val itemCount = pagerState.pageCount

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(48.dp),
        horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
    ) {
        Canvas(modifier = Modifier) {
            val distance = (circleSize + circleSpacing).toPx()

            val centerX = size.width / 2
            val centerY = size.height / 2

            val totalWidth = distance * itemCount
            val startX = centerX - (totalWidth / 2) + (circleSize / 2).toPx()

            repeat(itemCount) {
                val pageOffset = pagerState.calculateCurrentOffsetForPage(it)

                val alpha = 0.8f.coerceAtLeast(1 - pageOffset.absoluteValue)
                val scale = 1f.coerceAtMost(pageOffset.absoluteValue)

                val x = startX + (it * distance)
                val circleCenter = Offset(x, centerY)
                val radius = circleSize.toPx() / 2
                val innerRadius = (innerCircle.toPx() * scale) / 2

                drawCircle(
                    color = Color.White, center = circleCenter,
                    radius = radius, alpha = alpha,
                )

                drawCircle(color = Color(0xFFE77F82), center = circleCenter, radius = innerRadius)
            }
        }
    }
}