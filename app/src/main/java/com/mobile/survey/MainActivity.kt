package com.mobile.survey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.survey.surveypage.SurveyPage
import com.mobile.survey.surveypage.model.Option1
import com.mobile.survey.surveypage.model.SurveyPageState
import com.mobile.survey.ui.theme.MobileSurveyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSurveyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SurveyPage(
                        surveyPageState = surveyPageState()
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun GreetingPreview() {
    MobileSurveyTheme {
        SurveyPage(
            surveyPageState = surveyPageState()
        )
    }
}

fun surveyPageState() = SurveyPageState(
    question = "How likely is it that you would recommend " +
            "this company to a friend or colleague?",
    options = listOf(
        Option1.OPTION_0,
        Option1.OPTION_p1,
        Option1.OPTION_p2,
        Option1.OPTION_p3,
        Option1.OPTION_p4,
        Option1.OPTION_p5,
        Option1.OPTION_p6,
        Option1.OPTION_p7,
        Option1.OPTION_p8,
        Option1.OPTION_p9,
        Option1.OPTION_p10,
    )
)