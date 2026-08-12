package com.policyboss.customer.feature.policyVault.model.policyVaultModel

import androidx.annotation.DrawableRes

data class PolicyVaultTabItem(

    val category: PolicyCategory,

    @DrawableRes
    val iconRes: Int? = null
)