package com.mobile.survey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.survey.surveypage.SurveyPage
import com.mobile.survey.surveypage.model.ScaleType
import com.mobile.survey.surveypage.model.SurveyPageState
import com.mobile.survey.surveypage.model.SurveyPagesState
import com.mobile.survey.surveypage.model.positiveList
import com.mobile.survey.surveypage.model.surveyPagesState
import com.mobile.survey.ui.theme.MobileSurveyTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSurveyTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {


                    SurveyPagesCarousel(surveyPagesCarouselState = surveyPagesState())
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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


@Preview(showBackground = true, widthDp = 400)
@Composable
fun SurveyPagesPreview() {
    MobileSurveyTheme {
        SurveyPage(
            enableColor = true,
            surveyPageState = SurveyPageState(
                options = positiveList(), textVisible = true, scaleType = ScaleType.POSITIVE
            )
        )
    }
}

