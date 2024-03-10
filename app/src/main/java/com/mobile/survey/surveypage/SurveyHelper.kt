package com.mobile.survey.surveypage

import android.animation.ArgbEvaluator
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.mobile.survey.R
import com.mobile.survey.surveypage.model.DynamicOption


@Composable
fun createDynamicOptionsList(
    startColorRes: Int = R.color.green10,
    sortingOrder: SortingOrder = SortingOrder.REVERSED,
    steps: Int = 11
): List<DynamicOption> {
    val context = LocalContext.current

    val endColor = Color.White

    val colorInterpolator = ColorInterpolator(context, startColorRes, endColor, steps = steps)

    val dynamicOptionsList = (0..<steps).map { index ->
        val backgroundColor = colorInterpolator.getColor(index)
        val textColor = contrastColor(backgroundColor)
        DynamicOption(title = index.toString(), color = backgroundColor, textColor = textColor)
    }
    return when (sortingOrder) {
        SortingOrder.NORMAL -> dynamicOptionsList
        SortingOrder.REVERSED -> dynamicOptionsList.reversed()
    }
}

enum class SortingOrder {
    NORMAL,
    REVERSED
}

class ColorInterpolator(
    context: Context,
    private val startColorRes: Int,
    private val endColor: Color,
    private val steps: Int
) {
    private val startColor: Int = ContextCompat.getColor(context, startColorRes)

    fun getColor(step: Int): Color {
        val fraction: Float = step.toFloat() / (steps - 1)
        val argbEvaluator = ArgbEvaluator()
        val interpolatedColor =
            argbEvaluator.evaluate(fraction, endColor.toArgb(), startColor) as Int
        return Color(interpolatedColor)
    }
}

@Composable
fun contrastColor(backgroundColor: Color): Color {
    val hsv = FloatArray(3)
    ColorUtils.colorToHSL(backgroundColor.toArgb(), hsv)

    // Adjusting the brightness threshold based on your preference
    val threshold = 0.7f

    val textColor = if (hsv[2] > threshold) {
        Color.Black
    } else {
        Color.White
    }

    return textColor
}



