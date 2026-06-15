package com.policyboss.customer.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

 val LightColorScheme = lightColorScheme(

    primary = AppColors.BrandDark,

    onPrimary = Color.White, // Text inside primary buttons

    secondary = AppColors.BrandBlue,

    tertiary = AppColors.BrandSuccess,

// The background of your app screens
    background = AppColors.Background,
    surface = AppColors.Background, // Background of cards, sheets, etc.



// 👇 THIS is what makes BrandDark your default text color
    onBackground = AppColors.TextPrimary,
    onSurface = AppColors.TextPrimary,


    onSecondary = Color.White,
    onTertiary = Color.White,

    error = AppColors.ErrorRed


)