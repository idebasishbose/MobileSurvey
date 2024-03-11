package com.mobile.survey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobile.survey.surveypage.SurveyPage
import com.mobile.survey.surveypage.SurveyPageView
import com.mobile.survey.surveypage.model.ScaleType
import com.mobile.survey.surveypage.model.SurveyPageState
import com.mobile.survey.surveypage.model.positiveList
import com.mobile.survey.ui.theme.MobileSurveyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            MobileSurveyTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "landing") {
                        composable("landing") { LandingPage(navController) }
                        composable("surveyPageView/{parameter}") { backStackEntry ->
                            val parameter = backStackEntry.arguments?.getString("parameter") ?: ""
                            SurveyPageView(parameter = parameter)
                        }
                    }

                }
            }
        }
    }


}

@Composable
fun LandingButton(navController: NavHostController, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = colorResource(id = R.color.green02))
            .clickable { navController.navigate("surveyPageView/$text") },
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
    }
}

@Composable
fun LandingPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LandingButton(navController, text = "Prototype 1")
        LandingButton(navController, text = "Prototype 2")
        LandingButton(navController, text = "Prototype 3")
        LandingButton(navController, text = "Prototype 4")
        LandingButton(navController, text = "Prototype 5")
        LandingButton(navController, text = "Prototype 6")

    }
}


@Composable
fun NavigateToDetailsScreen(parameter: String) {
    // Navigate to another composable with the parameter
    // You can use navigation library or any other navigation mechanism here
    // For demonstration, let's just show a toast with the parameter
    val context = LocalContext.current

    SurveyPageView("test")
//    Toast.makeText(context, "Parameter: $parameter", Toast.LENGTH_SHORT).show()


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

