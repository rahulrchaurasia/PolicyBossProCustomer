package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense

import android.net.Uri

sealed interface DriversLicenseUiEvent {
    // 🚀 Payload is passed safely in the event!
    data class SubmitClaim(val uri: Uri) : DriversLicenseUiEvent
    data class ShowError(val message: String) : DriversLicenseUiEvent
}