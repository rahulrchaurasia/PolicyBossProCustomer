package com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel

import androidx.annotation.DrawableRes

data class PolicyCategoryTab(

    val category: PolicyCategory,

    @DrawableRes
    val iconRes: Int
)

//uses
//tab.category.title