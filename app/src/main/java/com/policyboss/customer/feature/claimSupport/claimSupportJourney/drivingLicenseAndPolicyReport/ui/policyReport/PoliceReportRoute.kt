package com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.policyReport




import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component.SingleDocumentUploadScreen
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel.PoliceReportViewModel

// Imports omitted for brevity...

@Composable
fun PoliceReportRoute(
    viewModel: PoliceReportViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (Uri?) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val photoPickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let { viewModel.onAction(PoliceReportAction.OnDocumentSelected(it)) }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            if (event is PoliceReportUiEvent.NavigateNext) onNavigateNext(event.uri)
        }
    }

    SingleDocumentUploadScreen(
        title = "Police Report (If applicable)",
        step = 4,
        totalSteps = 5,
        documentUri = uiState.documentUri,
        emptyButtonText = "Confirm and Continue",
        selectedButtonText = "Confirm and Continue", // No "File a Claim" here
        informationTitle = "When is a Police Report required?",
        informationItems = listOf(
            "Theft or vandalism incidents",
            "Accidents involving a third party",
            "Major property damage"
        ),
        onUploadClick = { photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
        onRemoveClick = { viewModel.onAction(PoliceReportAction.OnRemoveDocument) },
        onContinueClick = { viewModel.onAction(PoliceReportAction.OnContinueClick) },
        onBackClick = onNavigateBack
    )
}