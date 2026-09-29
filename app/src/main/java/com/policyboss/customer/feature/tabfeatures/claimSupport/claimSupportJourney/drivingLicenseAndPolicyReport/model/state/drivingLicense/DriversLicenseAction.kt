package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense

import android.net.Uri

sealed interface DriversLicenseAction {
    data class OnDocumentSelected(val uri: Uri) : DriversLicenseAction
    data object OnRemoveDocument : DriversLicenseAction
    data object OnSubmitClick : DriversLicenseAction // 🚀 Submit instead of Continue
}