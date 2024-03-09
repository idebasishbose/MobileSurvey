package com.mobile.survey.surveypage.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalWormIndicator(
    count: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    spacing: Dp = 10.dp,
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.TopCenter
    ) {

        val currentItem by remember {
            derivedStateOf {
                pagerState.currentPage
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing),
            modifier = modifier.width(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            repeat(count) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = Color.Red.copy(alpha = 0.3f), shape = CircleShape
                        ), contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = (it+1).toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
                    )

                }
            }
        }

        Box(
            Modifier
                .verticalWormTransition(pagerState)
                .size(30.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.verticalWormTransition(
    pagerState: PagerState
) = drawBehind {
    val distance = size.height + 10.dp.roundToPx()
    val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
    val wormOffset = (scrollPosition % 1) * 2

    val yPos = scrollPosition.toInt() * distance
    val head = yPos + distance * 0f.coerceAtLeast(wormOffset - 1)
    val tail = yPos + size.height + 1f.coerceAtMost(wormOffset) * distance

    val worm = RoundRect(
        0f, head, size.width, tail, CornerRadius(50f)
    )

    val path = Path().apply { addRoundRect(worm) }
    drawPath(path = path, color = Color.Red.copy(alpha = 0.8f))
}
