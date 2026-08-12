package com.policyboss.customer.ui.theme.gradients

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.policyboss.customer.ui.theme.AppColors
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

    // Mark: its purpose if it is a shared UI pattern.
    //++++ Background Surface using gradient ++++++++++++++++
    val ScreenSurfaceGradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to AppColors.SkyBlue,
            0.16f to AppColors.PaleCyan,
            0.42f to Color.White,
            1.00f to Color.White
        )
    )




    val PolicyCardGradient = Brush.linearGradient(

        colors = listOf(
            Color(0xFF7FBCFF),
            Color(0xFF1887FF)
        ),

        start = Offset.Zero,

        end = Offset.Infinite
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