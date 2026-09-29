package com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel

import androidx.annotation.DrawableRes

data class PolicyVaultTabItem(

    val category: PolicyCategory,

    @DrawableRes
    val iconRes: Int? = null
)