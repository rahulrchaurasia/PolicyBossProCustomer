package com.policyboss.customer.ui.theme.gradients

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.policyboss.customer.ui.theme.AppColors.PrimaryYellow

// A reusable factory for your gradient
object AppGradients {
    val BorderBrush = Brush.horizontalGradient(
        colors = listOf(PrimaryYellow, Color.Transparent)
    )

    // Fades from [Color] (on the left) to [Transparent] (on the right)
    fun fadeToRight(color: Color) = Brush.horizontalGradient(
        colors = listOf(color, Color.Transparent)
    )

    // Fades from [Transparent] (on the left) to [Color] (on the right)
    fun fadeToLeft(color: Color) = Brush.horizontalGradient(
        colors = listOf(Color.Transparent, color)
    )


    // If you need them for other colors later, you can make a factory:
    fun horizontalFade(color: Color) = Brush.horizontalGradient(colors = listOf(Color.Transparent, color))
}


//Mark : ex
/*
//drawBehind
 Box(
       Modifier
            .weight(1f)
            .height(1.dp)
             .drawBehind { drawRect(AppGradients.fadeToLeft(AppColors.GoldText)) }
               )

 */