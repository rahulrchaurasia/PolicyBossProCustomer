package com.policyboss.customer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R


/*
Because you are using androidx.compose.material3.Typography,
you are bound to the 15 predefined text styles of the Material Design 3 (M3) Type Scale.
 You cannot add custom property names (like myCustomHeader or extraLargeTitle)
 directly inside the Typography(...) constructor.


The fixed names available in M3 are grouped into 5 categories, each with Large, Medium, and Small:

display (Large, Medium, Small)

headline (Large, Medium, Small)

title (Large, Medium, Small)

body (Large, Medium, Small)

label (Large, Medium, Small)
 */

/*


********* How To Used **************
style = MaterialTheme.typography.bodyMedium

Benefits:

            consistent UI
            easy maintenance
            change entire app typography from one place
            follows Material3 architecture
            scalable for large projects

   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 If you need SMALL modification

Use .copy():

Text(
    text = "Create account",
    style = MaterialTheme.typography.labelMedium.copy(
        fontWeight = FontWeight.SemiBold
    ),
    color = MaterialTheme.colorScheme.secondary
)
 */


/*
// 2. Add custom names using Extension Properties
val Typography.buttonExtraLarge: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = GeistFontFamily
    )

val Typography.tinyCaption: TextStyle
    get() = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.Thin,
        fontFamily = GeistFontFamily
    )

    for used :
    Text(
    text = "Submit Application",
    // Accessing your custom name directly!
    style = MaterialTheme.typography.buttonExtraLarge
)
 */

//======================= Typography ========================================
/*
display / headline: Large screen titles, hero sections.

title: Card headers, dialog titles, distinct sections.

body: Long-form reading text (paragraphs, descriptions). Usually Normal weight.

label: Short, utilitarian text inside UI components (buttons, tags, chips, "View Details" links). Usually Medium or SemiBold weight.
 */
//===============================================================
val GeistFontFamily = FontFamily(
    Font(R.font.geist_thin, FontWeight.Thin),
    Font(R.font.geist_extralight, FontWeight.ExtraLight),
    Font(R.font.geist_light, FontWeight.Light),

    Font(R.font.geist_regular, FontWeight.Normal),
    Font(R.font.geist_medium, FontWeight.Medium),
    Font(R.font.geist_semibold, FontWeight.SemiBold),

    Font(R.font.geist_regular_italic, FontWeight.Normal),
    Font(R.font.geist_medium_italic, FontWeight.Medium),
    Font(R.font.geist_semibold_italic, FontWeight.SemiBold)


)

// ===================================================
// APP TYPOGRAPHY
// ===================================================

val AppTypography = Typography(


    // ===================================================
    // DISPLAY
    // ===================================================

    displayLarge = TextStyle(
        fontSize = 28.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = GeistFontFamily
    ),

    displayMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = GeistFontFamily
    ),


    // ===================================================
    // HEADLINE
    // ===================================================

    headlineLarge = TextStyle(
        fontSize = 22.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    ),

    headlineMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    ),

    headlineSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily

    ),


    // ===================================================
    // TITLE
    // ===================================================

    titleLarge = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    ),

    titleMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = GeistFontFamily
    ),

    titleSmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = GeistFontFamily
    ),




    // ===================================================
    // BODY
    // ===================================================

    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = GeistFontFamily
    ),

    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = GeistFontFamily
    ),

    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = GeistFontFamily
    ),


    // ===================================================
    // LABEL
    // ===================================================

    labelLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    ),

    labelMedium = TextStyle(
        fontSize = 13.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = GeistFontFamily
    ),



    labelSmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = GeistFontFamily
    )







)


// ****** Custom added ********
val Typography.captionSmall: TextStyle
    get() = TextStyle(
        fontSize = 10.sp, // or 11.sp
        lineHeight = 13.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    )

val Typography.buttonLinkSmall: TextStyle
    get() = TextStyle(
        fontSize = 12.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    )

val Typography.labelMediumSemiBold: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    )

val Typography.bodyMediumSemiBold: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = GeistFontFamily
    )

val Typography.titleSmallItalic: TextStyle
    get() = titleSmall.copy(
        fontStyle = FontStyle.Italic
    )

val Typography.bottomNavLabelSelected: TextStyle
    get() = labelSmall.copy(
        fontWeight = FontWeight.SemiBold
    )

