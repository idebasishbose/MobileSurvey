package com.mobile.survey.surveypage.model

import com.mobile.survey.R

data class SurveyPageState(
    val question: String = "",
    val options: List<GenericOption> = listOf(),
    val scaleType: ScaleType = ScaleType.NEUTRAL
)

interface GenericOption
enum class ScaleType(val type: String, val color: Int) {
    POSITIVE(type = "positive", color = R.color.red10),
    NEGATIVE(type = "negative", color = R.color.green10),
    NEUTRAL(type = "neutral", color = R.color.white),
}

enum class Option1(
    val title: String,
    val color: Int,
    val scaleType: ScaleType = ScaleType.NEUTRAL
) : GenericOption {

    OPTION_n10(title = "-10", color = R.color.green10),
    OPTION_n9(title = "-9", color = R.color.green09),
    OPTION_n8(title = "-8", color = R.color.green08),
    OPTION_n7(title = "-7", color = R.color.green07),
    OPTION_n6(title = "-6", color = R.color.green06),
    OPTION_n5(title = "-5", color = R.color.green05),
    OPTION_n4(title = "-4", color = R.color.green04),
    OPTION_n3(title = "-3", color = R.color.green03),
    OPTION_n2(title = "-2", color = R.color.green02),
    OPTION_n1(title = "-1", color = R.color.green01),
    OPTION_0(title = "0", color = R.color.white),
    OPTION_p1(title = "1", color = R.color.red01),
    OPTION_p2(title = "2", color = R.color.red02),
    OPTION_p3(title = "3", color = R.color.red03),
    OPTION_p4(title = "4", color = R.color.red04),
    OPTION_p5(title = "5", color = R.color.red05),
    OPTION_p6(title = "6", color = R.color.red06),
    OPTION_p7(title = "7", color = R.color.red07),
    OPTION_p8(title = "8", color = R.color.red08),
    OPTION_p9(title = "9", color = R.color.red09),
    OPTION_p10(title = "10", color = R.color.red10),
}
