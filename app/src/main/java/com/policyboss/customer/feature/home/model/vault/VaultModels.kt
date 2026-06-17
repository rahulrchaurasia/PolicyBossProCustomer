package com.policyboss.customer.feature.home.model.vault

import androidx.annotation.DrawableRes


data class VaultTabItem(

    val id: Int,

    val title: String,

    val iconRes: Int
)

object VaultTabIds {

    const val MOTOR = 0

    const val BIKE = 1

    const val CV = 2

    const val HEALTH = 3

    const val LIFE = 4

    const val TRAVEL = 5

    const val SMELINE = 6
}

data class VaultPolicy(

    val id: String,

    val tabId: Int,

    val vehicleName: String,

    val vehicleNumber: String,

    @DrawableRes
    val vehicleImage: Int,

    val daysLeft: String,

    @DrawableRes
    val companyLogo: Int,

    val idv: String,

    val premium: String,

    val expiry: String,

    val title: String
)