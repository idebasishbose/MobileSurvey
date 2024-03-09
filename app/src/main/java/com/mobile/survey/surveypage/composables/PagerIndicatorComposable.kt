package com.mobile.survey.surveypage.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorCount: Int = 5,
    indicatorSize: Dp = 17.dp,
    indicatorShape: Shape = CircleShape,
    space: Dp = 20.dp,
    activeColor: Color = Color.Gray,
    inActiveColor: Color = Color.LightGray,
    orientation: IndicatorOrientation = IndicatorOrientation.Horizontal,
    onClick: ((Int) -> Unit)? = null
) {

    val listState = rememberLazyListState()

    val itemCount = pagerState.pageCount
    val totalWidth: Dp = indicatorSize * itemCount + space * (itemCount - 1)
    val widthInPx = LocalDensity.current.run { indicatorSize.toPx() }


    val currentItem by remember {
        derivedStateOf {
            pagerState.currentPage
        }
    }


    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        if (orientation == IndicatorOrientation.Horizontal) {
            listState.animateScrollToItem(
                currentItem - (widthInPx / 2 - viewportSize.width / 2).toInt()
            )
        } else {
            listState.animateScrollToItem(
                currentItem, (widthInPx / 2 - viewportSize.height / 2).toInt()
            )
        }

    }

    if (orientation == IndicatorOrientation.Horizontal) {
        LazyRow(
            modifier = modifier.width(totalWidth),
            state = listState,
            contentPadding = PaddingValues(start = 5.dp, end = space, top = space, bottom = space),
            horizontalArrangement = Arrangement.spacedBy(space),
            userScrollEnabled = false
        ) {
            indicatorItems(
                itemCount,
                currentItem,
                indicatorCount,
                indicatorShape,
                activeColor,
                inActiveColor,
                indicatorSize,
                onClick
            )
        }
    } else {

        LazyColumn(
            modifier = modifier.height(totalWidth + 25.dp),
            state = listState,
            contentPadding = PaddingValues(start = 2.dp, end = 10.dp, top = space, bottom = space),
            verticalArrangement = Arrangement.spacedBy(space),
            userScrollEnabled = false
        ) {
            indicatorItems(
                itemCount,
                currentItem,
                indicatorCount,
                indicatorShape,
                activeColor,
                inActiveColor,
                indicatorSize,
                onClick
            )
        }
    }

}

private fun LazyListScope.indicatorItems(
    itemCount: Int,
    currentItem: Int,
    indicatorCount: Int,
    indicatorShape: Shape,
    activeColor: Color,
    inActiveColor: Color,
    indicatorSize: Dp,
    onClick: ((Int) -> Unit)?
) {
    items(itemCount) { index ->

        val isSelected = (index == currentItem)

        // Index of item in center when odd number of indicators are set
        // for 5 indicators this is 2nd indicator place
        val centerItemIndex = indicatorCount / 2

        val right1 = (currentItem < centerItemIndex && index >= indicatorCount - 1)

        val right2 =
            (currentItem >= centerItemIndex && index >= currentItem + centerItemIndex && index < itemCount - centerItemIndex + 1)
        val isRightEdgeItem = right1 || right2

        // Check if this item's distance to center item is smaller than half size of
        // the indicator count when current indicator at the center or
        // when we reach the end of list. End of the list only one item is on edge
        // with 10 items and 7 indicators
        // 7-3= 4th item can be the first valid left edge item and
        val isLeftEdgeItem =
            index <= currentItem - centerItemIndex && currentItem > centerItemIndex && index < itemCount - indicatorCount + 1

        Box(modifier = Modifier
            .graphicsLayer {
                val scale = if (isSelected) {
                    1.8f
                } else if (isLeftEdgeItem || isRightEdgeItem) {
                    1.1f
                } else {
                    1.2f
                }
                scaleX = scale
                scaleY = scale
            }

            .clip(indicatorShape)
            .size(indicatorSize)
            .background(
                if (isSelected) activeColor else inActiveColor, indicatorShape
            )
            .then(if (onClick != null) {
                Modifier.clickable {
                    onClick.invoke(index)
                }
            } else Modifier), contentAlignment = Alignment.Center) {
            Text(
                text = (index + 1).toString(),
                textAlign = TextAlign.Center,
                style = typography.bodySmall,
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}

enum class IndicatorOrientation {
    Horizontal, Vertical
}

// extension method for current page offset
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerFadeTransition(page: Int, pagerState: PagerState) = graphicsLayer {
    val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
    translationY = pageOffset * size.width
    alpha = 1 - pageOffset.absoluteValue
}