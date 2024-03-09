package com.mobile.survey.surveypage.model

import androidx.compose.ui.graphics.Color
import com.mobile.survey.R

data class SurveyPageState(
    val question: String = "",
    val options: List<Option> = listOf(),
    val scaleType: ScaleType = ScaleType.NEUTRAL,
    val textVisible: Boolean = false,
    val verticalSurvey: Boolean = true
)


//interface GenericOption

sealed class Option(
    open val title: String,
    open val detailedTitle: String = "",
    open val color: Color,
    open val textColor: Color,

    open val scaleType: ScaleType = ScaleType.NEUTRAL
)

data class DynamicOption(
    override val title: String,
    override val detailedTitle: String = "",
    override val color: Color,
    override val textColor: Color = Color.Black,
    override val scaleType: ScaleType = ScaleType.NEUTRAL
) : Option(title, detailedTitle, color, textColor, scaleType)


enum class ScaleType(val type: String, val color: Int) {
    POSITIVE(type = "positive", color = R.color.red10),
    NEGATIVE(type = "negative", color = R.color.green10),
    NEUTRAL(type = "neutral", color = R.color.white),
}

