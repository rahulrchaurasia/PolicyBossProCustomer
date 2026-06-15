package com.policyboss.customer.ui.theme



import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

/*
How To use:

color = MaterialTheme.colorScheme.success
 */

fun ColorScheme.isLight(): Boolean {
    return background.luminance() > 0.5f
}

val ColorScheme.textPrimary: Color
    get() = onBackground

val ColorScheme.textSecondary: Color
    get() = AppColors.TextSecondary



val ColorScheme.textSecondaryDark: Color
    get() =
        AppColors.TextSecondaryDark


//val ColorScheme.textSecondaryDark: Color
//    get() = if (isLight()) {
//        AppColors.TextSecondaryDark
//    } else {
//        Color(0xFFE4E7EC)
//    }

val ColorScheme.buttonDisabled: Color
    get() = AppColors.ButtonDisabled


val ColorScheme.success: Color
    get() = AppColors.BrandSuccess

val ColorScheme.trustBadgeBackground: Color
    get() = AppColors.TrustBadgeBackground


val ColorScheme.border: Color
    get() = AppColors.Border

val ColorScheme.placeholder: Color
    get() = AppColors.Placeholder




val ColorScheme.successLight: Color
    get() = AppColors.SuccessLight

val ColorScheme.errorCustom: Color
    get() = AppColors.ErrorRed
