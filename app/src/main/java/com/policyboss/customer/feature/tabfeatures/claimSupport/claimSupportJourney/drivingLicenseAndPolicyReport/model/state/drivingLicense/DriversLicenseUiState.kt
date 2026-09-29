package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense

import android.net.Uri


data class DriversLicenseUiState(
    val documentUri: Uri? = null,
    val errorMessage: String? = null // 🚀 ADDED: Holds the inline error
)



