package com.policyboss.customer.feature.home.model

import androidx.annotation.DrawableRes

data class EarningBanner(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int // Use an Int for local drawable resources
)