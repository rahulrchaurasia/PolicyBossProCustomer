package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model

import androidx.annotation.DrawableRes

data class Insurer(
    val id: String,
    val name: String,
    @DrawableRes val logoRes: Int
)