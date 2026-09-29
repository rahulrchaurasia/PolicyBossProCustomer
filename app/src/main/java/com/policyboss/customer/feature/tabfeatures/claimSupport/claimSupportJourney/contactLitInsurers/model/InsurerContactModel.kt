package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.model

import androidx.annotation.DrawableRes

data class InsurerContactModel(
    val id: String,
    val name: String,
    @DrawableRes val logoRes: Int
)