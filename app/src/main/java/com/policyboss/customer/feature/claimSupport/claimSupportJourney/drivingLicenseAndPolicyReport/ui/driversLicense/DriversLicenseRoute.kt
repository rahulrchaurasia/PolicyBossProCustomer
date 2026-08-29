package com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.driversLicense


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component.SingleDocumentUploadScreen
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel.DriversLicenseViewModel
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.ClaimJourneyEvent
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.ClaimJourneyViewModel
import com.policyboss.customer.ui.components.loading.CustomAppLoader
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.extension.showAppSnackbar

@Composable
fun DriversLicenseRoute(
    viewModel: DriversLicenseViewModel,
    journeyViewModel: ClaimJourneyViewModel, // 🚀 ADDED: The Shared ViewModel
    onNavigateBack: () -> Unit,
    onNavigateToSuccess: () -> Unit // 🚀 Changed from onSubmitClaim
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 🚀 1. Create a State variable to hold the loading status
    var showLoader by remember { mutableStateOf(false) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { viewModel.onAction(DriversLicenseAction.OnDocumentSelected(it)) }
    }

    // Grab the global state instantly
    val globalSnackbar = LocalAppSnackbar.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is DriversLicenseUiEvent.SubmitClaim -> {
                    // Step 1: The photo is valid! Save it to the shared Journey.
                    journeyViewModel.saveDriversLicense(event.uri)

                    // Step 2: Tell the Journey ViewModel to start the API upload!
                    journeyViewModel.submitFinalClaim()
                }
                is DriversLicenseUiEvent.ShowError -> {
                    globalSnackbar.showAppSnackbar(message = event.message)
                }
            }
        }
    }

    // 2. LISTEN TO THE API NETWORK RESPONSE (ClaimJourneyViewModel)
    LaunchedEffect(Unit) {
        journeyViewModel.journeyEvent.collect { event ->
            when (event) {
                is ClaimJourneyEvent.Loading -> {

                    // 🚀 2. Update the state (do NOT draw the UI here)
                    showLoader = event.isLoading
                }
                is ClaimJourneyEvent.SubmissionSuccess -> {
                    // The API call finished! Navigate to the Claim Filed screen!
                    onNavigateToSuccess()
                }
                is ClaimJourneyEvent.ShowError -> {
                    globalSnackbar.showAppSnackbar(message = event.message)
                }
            }
        }
    }

    // 🚀 3. Draw the UI at the root of the Composable, reacting to the state
    if (showLoader) {
        CustomAppLoader(showBackground = true)
    }

    SingleDocumentUploadScreen(
        title = "Driver's License",
        step = 5,
        totalSteps = 5,
        documentUri = uiState.documentUri,
        emptyButtonText = "Confirm and Continue",
        selectedButtonText = "File a Claim",
        informationTitle = "Helps us process your claim faster",
        informationItems = listOf(
            "Ensure the document is clear and readable",
            "All corners of the license should be visible",
            "You can edit or add more photos later"
        ),
        onUploadClick = {
            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        onRemoveClick = { viewModel.onAction(DriversLicenseAction.OnRemoveDocument) },
        onContinueClick = { viewModel.onAction(DriversLicenseAction.OnSubmitClick) },
        onBackClick = onNavigateBack
    )
}