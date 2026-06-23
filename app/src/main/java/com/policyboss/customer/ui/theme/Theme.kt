package com.policyboss.customer.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable



@Composable
fun PolicyBossCustomerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,   // 👈 your colors
        typography = AppTypography,   // 👈 your typography
        shapes = AppShapes,
        content = content
    )
}


/*
✅ DO this
color = MaterialTheme.colorScheme.textPrimary
Button example
Button(
    colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = MaterialTheme.colorScheme.buttonDisabled
    )
)
Text example
Text(
    text = "Hello",
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.textPrimary
)
 */

//@Composable
//fun PolicyBossCustomerTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography =  AppTypography,
//        content = content
//    )
//}