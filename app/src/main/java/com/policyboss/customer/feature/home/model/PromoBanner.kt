package com.policyboss.customer.feature.home.model

import androidx.annotation.DrawableRes
import com.policyboss.customer.feature.home.model.banner.BannerDestination

data class PromoBanner(
    val id: String,
    val tagText: String, // e.g., "RENEW & EARN", "RENEW CAR INSURANCE"
    val title: String,
    val buttonText: String,
     val imageRes: Int, // The specific image for the right side of the card
    val isYellowTheme: Boolean = false ,// true for the top left yellow card, false for the blue ones
    val destination: BannerDestination

)