package com.policyboss.customer.feature.home.model.policy

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class CuratedPolicy(

    val id: String,

    val title: String,

    @DrawableRes
    val iconRes: Int,

    val backgroundColor: Color,

    val isComingSoon: Boolean
)