package com.mobile.survey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.survey.surveypage.IndicatorOrientation
import com.mobile.survey.surveypage.PagerIndicator
import com.mobile.survey.surveypage.SurveyPage
import com.mobile.survey.surveypage.model.ScaleType
import com.mobile.survey.surveypage.model.SurveyPageState
import com.mobile.survey.surveypage.model.SurveyPagesState
import com.mobile.survey.surveypage.model.positiveList
import com.mobile.survey.surveypage.model.surveyPagesState
import com.mobile.survey.ui.theme.MobileSurveyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSurveyTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SurveyPagesCarousel(surveyPagesCarouselState = surveyPagesState())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurveyPagesCarousel(surveyPagesCarouselState: SurveyPagesState) {
    val pageCount = surveyPagesCarouselState.surveyPagesState.size

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })

    Spacer(modifier = Modifier.width(10.dp))
    PagerIndicator(
        pagerState = pagerState, orientation = IndicatorOrientation.Vertical
    )

    VerticalPager(
        pageSize = PageSize.Fill,
        state = pagerState,
    ) { page ->

        Box(
            Modifier
                .wrapContentHeight()
        ) {


            SurveyPage(
                enableColor = surveyPagesCarouselState.enableColor,
                surveyPageState = surveyPagesCarouselState.surveyPagesState[page]
            )

        }
    }
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun SurveyPagesPreview() {
    MobileSurveyTheme {
        SurveyPage(
            enableColor = true, surveyPageState = SurveyPageState(
                options = positiveList(), textVisible = true, scaleType = ScaleType.POSITIVE
            )
        )
    }
}

