package com.policyboss.customer.feature.onboarding.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable

data class OnboardingPage(
   // val imageUrl: String,
    @DrawableRes val imageResId: Int,
    val title: String,
    val subtitle: String? = null,
    val content: (@Composable () -> Unit)? = null
)