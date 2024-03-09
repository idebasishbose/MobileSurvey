package com.mobile.survey.surveypage.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.mobile.survey.R
import com.mobile.survey.surveypage.SortingOrder
import com.mobile.survey.surveypage.createDynamicOptionsList

@Composable
fun surveyPagesState() = SurveyPagesState(
    enableColor = true, surveyPagesState = listOf(
        SurveyPageState(
            question = "1. How likely is it that you would recommend this company to a friend or colleague?",
            options = positiveList(),
            scaleType = ScaleType.POSITIVE,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "2. Overall, how dissatisfied did you get with the last service?",
            options = negativeList(),
            scaleType = ScaleType.POSITIVE,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "3. Overall, how satisfied or dissatisfied are you with our company?",
            options = mixedList2(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "4. How well do our products meet your needs?",
            options = mixedList3(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "5. How would you rate the quality of the product?",
            options = mixedList4(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "6. How would you rate the value for money of the product?",
            options = mixedList5(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "7. How responsive have we been to your questions about our services?",
            options = mixedList6(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "8. How likely are you to purchase any of our products again?",
            options = mixedList1(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "9. How likely are you  to repeat your purchase?",
            options = positiveList(),
            scaleType = ScaleType.POSITIVE,
            textVisible = true,
            verticalSurvey = true
        )
    )
)


@Composable
fun positiveList() = createDynamicOptionsList(R.color.green10, steps = 11)


@Composable
fun negativeList() = createDynamicOptionsList(R.color.red10, SortingOrder.NORMAL)


@Composable
fun mixedList1() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Extremely likely", color = colorResource(id = R.color.green03)
    ), DynamicOption(
        title = "1", detailedTitle = "Very likely", color = colorResource(
            id = R.color.green02
        )
    ), DynamicOption(
        title = "0", detailedTitle = "Somewhat likely", color = colorResource(
            id = R.color.green01
        )
    ), DynamicOption(
        title = "1", detailedTitle = "Not so likely", color = colorResource(
            id = R.color.red01
        )
    ), DynamicOption(
        title = "2", detailedTitle = "Not at all likely", color = colorResource(id = R.color.red02)
    )
)


@Composable
fun mixedList2() = listOf(
    DynamicOption(
        title = "2",
        detailedTitle = "Very satisfied",
        color = colorResource(id = R.color.green02)
    ),
    DynamicOption(
        title = "1",
        detailedTitle = "Somewhat satisfied",
        color = colorResource(id = R.color.green01)
    ),
    DynamicOption(
        title = "0",
        detailedTitle = "Neither satisfied nor dissatisfied",
        color = colorResource(id = R.color.white)
    ),
    DynamicOption(
        title = "1",
        detailedTitle = "Somewhat dissatisfied",
        color = colorResource(id = R.color.red01)
    ),
    DynamicOption(
        title = "2",
        detailedTitle = "Very dissatisfied",
        color = colorResource(id = R.color.red02)
    ),
)

@Composable
fun mixedList3() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Extremely well", color = colorResource(id = R.color.green03)
    ),
    DynamicOption(
        title = "1", detailedTitle = "Very well", color = colorResource(id = R.color.green02)
    ),
    DynamicOption(
        title = "0", detailedTitle = "Somewhat well", color = colorResource(id = R.color.green01)
    ),
    DynamicOption(
        title = "1", detailedTitle = "Not so well", color = colorResource(id = R.color.red01)
    ),
    DynamicOption(
        title = "2", detailedTitle = "Not at all well", color = colorResource(id = R.color.red02)
    ),
)

@Composable
fun mixedList4() = listOf(
    DynamicOption(
        title = "2",
        detailedTitle = "Very high quality",
        color = colorResource(id = R.color.green02)
    ),
    DynamicOption(
        title = "1", detailedTitle = "High quality", color = colorResource(id = R.color.green01)
    ),
    DynamicOption(
        title = "0",
        detailedTitle = "Neither high nor low quality",
        color = colorResource(id = R.color.white)
    ),
    DynamicOption(
        title = "1", detailedTitle = "Low quality", color = colorResource(id = R.color.red01)
    ),
    DynamicOption(
        title = "2", detailedTitle = "Very low quality", color = colorResource(id = R.color.red02)
    ),
)

@Composable
fun mixedList5() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Excellent", color = colorResource(id = R.color.green02)
    ),
    DynamicOption(
        title = "1", detailedTitle = "Above average", color = colorResource(id = R.color.green01)
    ),
    DynamicOption(
        title = "0", detailedTitle = "Average", color = colorResource(id = R.color.white)
    ),
    DynamicOption(
        title = "1", detailedTitle = "Below average", color = colorResource(id = R.color.red01)
    ),
    DynamicOption(
        title = "2", detailedTitle = "Poor", color = colorResource(id = R.color.red02)
    ),
)

@Composable
fun mixedList6() = listOf(
    DynamicOption(
        title = "3",
        detailedTitle = "Extremely responsive",
        color = colorResource(id = R.color.green03)
    ),
    DynamicOption(
        title = "2", detailedTitle = "Very responsive", color = colorResource(id = R.color.green02)
    ),
    DynamicOption(
        title = "1",
        detailedTitle = "Somewhat responsive",
        color = colorResource(id = R.color.green01)
    ),
    DynamicOption(
        title = "0", detailedTitle = "Not applicable", color = colorResource(id = R.color.white)
    ),
    DynamicOption(
        title = "1", detailedTitle = "Not so responsive", color = colorResource(id = R.color.red01)
    ),
    DynamicOption(
        title = "2",
        detailedTitle = "Not at all responsive",
        color = colorResource(id = R.color.red02)
    ),
)