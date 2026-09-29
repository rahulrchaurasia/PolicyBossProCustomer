package com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel


import androidx.annotation.DrawableRes
import com.policyboss.customer.ui.components.badge.PolicyBadgeUi
import com.policyboss.customer.ui.theme.AppColors

data class PolicyVaultPolicy(
    val id: String,
    val category: PolicyCategory,
    val source: PolicySource,
    val status: PolicyStatus,
    val title: String,
    val vehicleName: String,
    val vehicleNumber: String,
    @DrawableRes val vehicleImage: Int,
    @DrawableRes val companyLogo: Int,
    val idv: String,
    val premium: String,
    val expiry: String,
    val daysLeft: Int,

    // NEW: Raw data for sorting
    val premiumAmount: Int,
    val issueDateMillis: Long
)






fun PolicyVaultPolicy.badgeUi(): PolicyBadgeUi {

    return when (status) {

        PolicyStatus.ACTIVE ->

            PolicyBadgeUi(

                text = "Active policy",

                backgroundColor = AppColors.BadgeSuccessBackground,

                textColor = AppColors.BadgeSuccessText
            )

        PolicyStatus.EXPIRING_SOON ->

            PolicyBadgeUi(

                text = "Expires in $daysLeft days",

                backgroundColor = AppColors.BadgeWarningBackground,

                textColor = AppColors.BadgeWarningText
            )

        PolicyStatus.EXPIRED ->

            PolicyBadgeUi(

                text = "Expired",

                backgroundColor = AppColors.BadgeErrorBackground,

                textColor = AppColors.BadgeErrorText
            )
    }
}

