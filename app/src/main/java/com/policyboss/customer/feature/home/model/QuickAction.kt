package com.policyboss.customer.feature.home.model

import androidx.annotation.DrawableRes

data class QuickAction(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageRes: Int,
    val badge: BadgeType? = null
)

