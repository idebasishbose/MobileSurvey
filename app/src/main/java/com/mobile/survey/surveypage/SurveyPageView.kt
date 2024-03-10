package com.mobile.survey.surveypage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.survey.R
import com.mobile.survey.surveypage.composables.ColoredSpacer
import com.mobile.survey.surveypage.composables.IndicatorOrientation
import com.mobile.survey.surveypage.composables.MovingArrowAnimation
import com.mobile.survey.surveypage.composables.PagerIndicator
import com.mobile.survey.surveypage.model.SurveyPagesState
import com.mobile.survey.surveypage.model.surveyPagesState
import kotlinx.coroutines.launch


@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SurveyPageView(modifier: Modifier = Modifier) {


    Row(
        modifier = modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
    ) {
        val surveyPagesCarouselState = surveyPagesState()
        val pageCount = surveyPagesCarouselState.surveyPagesState.size

        val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
        val coroutineScope = rememberCoroutineScope()

        Spacer(modifier = Modifier.width(10.dp))

        PagerIndicator(pagerState = pagerState,
            orientation = IndicatorOrientation.Vertical,
            onClick = { index ->
                // Handle click event, for example, scroll to the page at the clicked index
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            })

        SurveyPagesCarousel(
            surveyPagesCarouselState = surveyPagesCarouselState, pagerState = pagerState
        )


    }

}


enum class ArrowDirection {
    UP, DOWN
}

@Composable
fun TextWithIconVerticalLayout(
    text1: String,
    text2: String,
    iconResId1: Int,
    iconResId2: Int,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified,
    contentDescription: String? = null
) {

    val painter1 = painterResource(id = iconResId1)
    val painter2 = painterResource(id = iconResId2)


    Column(
        modifier = modifier
            .padding(end = 10.dp)
            .width(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        MovingArrowAnimation(painter = painter1, direction = ArrowDirection.UP, iconTint = iconTint)

        text1.forEach {
            Text(text = it.toString(), modifier = Modifier.padding(top = 4.dp))
        }
        ColoredSpacer(color = Color.Gray, modifier = Modifier.width(18.dp))
        text2.forEach {
            Text(text = it.toString(), modifier = Modifier.padding(top = 4.dp))
        }

        MovingArrowAnimation(
            direction = ArrowDirection.DOWN, painter = painter2, iconTint = iconTint
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurveyPagesCarousel(
    surveyPagesCarouselState: SurveyPagesState,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {

    VerticalPager(
        pageSize = PageSize.Fill,
        state = pagerState,
    ) { page ->

        Box(
            modifier.wrapContentHeight()
        ) {

            SurveyPage(
                enableColor = surveyPagesCarouselState.enableColor,
                surveyPageState = surveyPagesCarouselState.surveyPagesState[page],
                modifier
            )

            TextWithIconVerticalLayout(text1 = "Better",
                text2 = "Worse",
                iconResId1 = R.drawable.baseline_arrow_upward_24,
                iconResId2 = R.drawable.baseline_arrow_downward_24,
                iconTint = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .semantics { contentDescription = "" })

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewSurveyPage() {
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        1
    }
    SurveyPagesCarousel(
        surveyPagesCarouselState = surveyPagesState(), pagerState = pagerState
    )
}