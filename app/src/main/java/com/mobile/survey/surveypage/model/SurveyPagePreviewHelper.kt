package com.mobile.survey.surveypage.model

import com.mobile.survey.R

fun surveyPagesState() = SurveyPagesState(
    enableColor = true,
    surveyPagesState = listOf(
        SurveyPageState(
            question = "1. How likely is it that you would recommend this company to a friend or colleague?",
            options = positiveList(),
            scaleType = ScaleType.POSITIVE,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "2. Overall, how satisfied or dissatisfied are you with our company?",
            options = mixedList2(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "3. How well do our products meet your needs?",
            options = mixedList3(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "4. How would you rate the quality of the product?",
            options = mixedList4(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ), SurveyPageState(
            question = "5. How would you rate the value for money of the product?",
            options = mixedList5(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ),
        SurveyPageState(
            question = "6. How responsive have we been to your questions about our services?",
            options = mixedList6(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ),
        SurveyPageState(
            question = "7. How likely are you to purchase any of our products again?",
            options = mixedList1(),
            scaleType = ScaleType.NEUTRAL,
            textVisible = true,
            verticalSurvey = true
        ),
        SurveyPageState(
            question = "8. On a scale of 0 to 10, how likely are you repeat your purchase?",
            options = positiveList(),
            scaleType = ScaleType.POSITIVE,
            textVisible = true,
            verticalSurvey = true
        )
    )
)


fun positiveList() = listOf(
    DynamicOption(title = "10", color = R.color.green10),
    DynamicOption(title = "9", color = R.color.green09),
    DynamicOption(title = "8", color = R.color.green08),
    DynamicOption(title = "7", color = R.color.green07),
    DynamicOption(title = "6", color = R.color.green06),
    DynamicOption(title = "5", color = R.color.green05),
    DynamicOption(title = "4", color = R.color.green04),
    DynamicOption(title = "3", color = R.color.green03),
    DynamicOption(title = "2", color = R.color.green02),
    DynamicOption(title = "1", color = R.color.green01),
    DynamicOption(title = "0", color = R.color.white),
)

fun negativeList() = listOf(
    DynamicOption(title = "0", color = R.color.white),
    DynamicOption(title = "1", color = R.color.red01),
    DynamicOption(title = "2", color = R.color.red02),
    DynamicOption(title = "3", color = R.color.red03),
    DynamicOption(title = "4", color = R.color.red04),
    DynamicOption(title = "5", color = R.color.red05),
    DynamicOption(title = "6", color = R.color.red06),
    DynamicOption(title = "7", color = R.color.red07),
    DynamicOption(title = "8", color = R.color.red08),
    DynamicOption(title = "9", color = R.color.red09),
    DynamicOption(title = "10", color = R.color.red10),
)

fun mixedList1() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Extremely likely", color = R.color.green03
    ),
    DynamicOption(
        title = "1", detailedTitle = "Very likely", color = R.color.green02
    ),
    DynamicOption(
        title = "0", detailedTitle = "Somewhat likely", color = R.color.green01
    ),
    DynamicOption(
        title = "1", detailedTitle = "Not so likely", color = R.color.red01
    ),
    DynamicOption(
        title = "2", detailedTitle = "Not at all likely", color = R.color.red02
    ),
)

fun mixedList2() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Very satisfied", color = R.color.green02
    ),
    DynamicOption(
        title = "1", detailedTitle = "Somewhat satisfied", color = R.color.green01
    ),
    DynamicOption(
        title = "0", detailedTitle = "Neither satisfied nor dissatisfied", color = R.color.white
    ),
    DynamicOption(
        title = "1", detailedTitle = "Somewhat dissatisfied", color = R.color.red01
    ),
    DynamicOption(
        title = "2", detailedTitle = "Very dissatisfied", color = R.color.red02
    ),
)

fun mixedList3() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Extremely well", color = R.color.green03
    ),
    DynamicOption(
        title = "1", detailedTitle = "Very well", color = R.color.green02
    ),
    DynamicOption(
        title = "0", detailedTitle = "Somewhat well", color = R.color.green01
    ),
    DynamicOption(
        title = "1", detailedTitle = "Not so well", color = R.color.red01
    ),
    DynamicOption(
        title = "2", detailedTitle = "Not at all well", color = R.color.red02
    ),
)

fun mixedList4() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Very high quality", color = R.color.green02
    ),
    DynamicOption(
        title = "1", detailedTitle = "High quality", color = R.color.green01
    ),
    DynamicOption(
        title = "0", detailedTitle = "Neither high nor low quality", color = R.color.white
    ),
    DynamicOption(
        title = "1", detailedTitle = "Low quality", color = R.color.red01
    ),
    DynamicOption(
        title = "2", detailedTitle = "Very low quality", color = R.color.red02
    ),
)

fun mixedList5() = listOf(
    DynamicOption(
        title = "2", detailedTitle = "Excellent", color = R.color.green02
    ),
    DynamicOption(
        title = "1", detailedTitle = "Above average", color = R.color.green01
    ),
    DynamicOption(
        title = "0", detailedTitle = "Average", color = R.color.white
    ),
    DynamicOption(
        title = "1", detailedTitle = "Below average", color = R.color.red01
    ),
    DynamicOption(
        title = "2", detailedTitle = "Poor", color = R.color.red02
    ),
)

fun mixedList6() = listOf(
    DynamicOption(
        title = "3", detailedTitle = "Extremely responsive", color = R.color.green03
    ),
    DynamicOption(
        title = "2", detailedTitle = "Very responsive", color = R.color.green02
    ),
    DynamicOption(
        title = "1", detailedTitle = "Somewhat responsive", color = R.color.green01
    ),
    DynamicOption(
        title = "0", detailedTitle = "Not applicable", color = R.color.white
    ),
    DynamicOption(
        title = "1", detailedTitle = "Not so responsive", color = R.color.red01
    ),
    DynamicOption(
        title = "2", detailedTitle = "Not at all responsive", color = R.color.red02
    ),
)