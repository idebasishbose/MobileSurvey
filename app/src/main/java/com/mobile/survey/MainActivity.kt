package com.mobile.survey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.survey.surveypage.SurveyPage
import com.mobile.survey.surveypage.model.SurveyPageState
import com.mobile.survey.ui.theme.MobileSurveyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSurveyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SurveyPage(SurveyPageState())
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 300, heightDp = 400)
@Composable
fun GreetingPreview() {
    MobileSurveyTheme {
        SurveyPage(SurveyPageState())
    }
}