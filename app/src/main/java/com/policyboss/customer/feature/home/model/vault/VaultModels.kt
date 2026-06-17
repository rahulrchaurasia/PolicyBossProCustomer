package com.policyboss.customer.feature.home.model.vault

import androidx.annotation.DrawableRes
import com.policyboss.customer.ui.components.card.PolicyDetailsCardModel

data class VaultTabItem(

    val id: Int,

    val title: String,

    val iconRes: Int
)



data class VaultPolicy(

    val vehicleName: String,

    val vehicleNumber: String,

    @DrawableRes
    val carImage: Int,

    val daysLeft: String,

    @DrawableRes
    val companyLogo: Int,

    val idv: String,

    val premium: String,

    val expiry: String,

    val title: String // 👈 add this
)