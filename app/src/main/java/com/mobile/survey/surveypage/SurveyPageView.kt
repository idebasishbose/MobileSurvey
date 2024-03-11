package com.mobile.survey.surveypage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobile.survey.R
import com.mobile.survey.surveypage.model.SurveyPagesState
import com.mobile.survey.surveypage.model.surveyPagesState


@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SurveyPageView(parameter: String, modifier: Modifier = Modifier) {


    Row(
        modifier = modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
    ) {
        val surveyPagesCarouselState = surveyPagesState(false)
        val pageCount = surveyPagesCarouselState.surveyPagesState.size

        val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
        val coroutineScope = rememberCoroutineScope()

        Spacer(modifier = Modifier.width(10.dp))

//        PagerIndicator(pagerState = pagerState,
//            orientation = IndicatorOrientation.Vertical,
//            onClick = { index ->
//                // Handle click event, for example, scroll to the page at the clicked index
//                coroutineScope.launch {
//                    pagerState.animateScrollToPage(index)
//                }
//            })

        SurveyPagesCarousel(
            surveyPagesCarouselState = surveyPagesCarouselState
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



        text1.forEach {
            Text(text = it.toString(), modifier = Modifier.padding(top = 4.dp))
        }
//        ColoredSpacer(color = Color.Gray, modifier = Modifier.width(18.dp))
//        text2.forEach {
//            Text(text = it.toString(), modifier = Modifier.padding(top = 4.dp))
//        }
//
//        MovingArrowAnimation(
//            direction = ArrowDirection.DOWN, painter = painter2, iconTint = iconTint
//        )
    }
}


@Composable
fun SurveyPagesCarousel(surveyPagesCarouselState: SurveyPagesState) {
    val pageCount = surveyPagesCarouselState.surveyPagesState.size
    val pagerState = rememberPagerState(pageCount = { pageCount })
    VerticalPager(
        pageSize = PageSize.Fill,
        state = pagerState,
//        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
    ) { page ->
        Box(
            Modifier
//                .pagerFadeTransition(page = page, pagerState = pagerState)
                .fillMaxSize()
        ) {

            Row(
                Modifier
                    .height(24.dp)
                    .padding(start = 4.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter), horizontalArrangement = Arrangement.Start
            ) {
                repeat(pageCount) { iteration ->

                    val color =
                        if (pagerState.currentPage == iteration) colorResource(id = R.color.green10)
                        else colorResource(id = R.color.green10).copy(
                            alpha = 0.2f
                        )
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(color)
                            .weight(1f)
                            .height(4.dp)
                    )
                }
            }
            SurveyPage(
                enableColor = surveyPagesCarouselState.enableColor,
                surveyPageState = surveyPagesCarouselState.surveyPagesState[page]
            )

        }
    }
}
