package com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport
import android.net.Uri

data class PoliceReportUiState(val documentUri: Uri? = null)

sealed interface PoliceReportAction {
    data class OnDocumentSelected(val uri: Uri) : PoliceReportAction
    data object OnRemoveDocument : PoliceReportAction
    data object OnContinueClick : PoliceReportAction
}

sealed interface PoliceReportUiEvent {
    data class NavigateNext(val uri: Uri?) : PoliceReportUiEvent // Nullable!
}