package com.policyboss.customer.feature.policyVault.model.policyVaultModel

import androidx.annotation.DrawableRes

data class PolicyCategoryTab(

    val category: PolicyCategory,

    @DrawableRes
    val iconRes: Int
)

//uses
//tab.category.title