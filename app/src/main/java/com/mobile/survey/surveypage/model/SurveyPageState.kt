package com.mobile.survey.surveypage.model

data class SurveyPageState(
    val question: String = "",
    val options: List<GenericOption> = listOf()
)

interface GenericOption

enum class Option1(val title: String) : GenericOption {
    OPTION_0("0"),
    OPTION_1("1"),
    OPTION_2("2"),
    OPTION_3("3"),
    OPTION_4("4"),
    OPTION_5("5"),
    OPTION_6("6"),
    OPTION_7("7"),
    OPTION_8("8"),
    OPTION_9("9"),
    OPTION_10("10"),
}

enum class Option2(val title: String) : GenericOption {
    OPTION_m5("-5"),
    OPTION_m4("-4"),
    OPTION_m3("-3"),
    OPTION_m2("-2"),
    OPTION_m1("1"),
    OPTION_0("0"),
    OPTION_p1("1"),
    OPTION_p2("2"),
    OPTION_p3("3"),
    OPTION_p4("4"),
    OPTION_p5("5"),
}