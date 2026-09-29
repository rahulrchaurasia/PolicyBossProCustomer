package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.driversLicense


import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component.SingleDocumentUploadScreen
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel.DriversLicenseViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.ClaimJourneyEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.ClaimJourneyViewModel

import com.policyboss.customer.ui.components.bottomSheet.UploadBottomSheet
import com.policyboss.customer.ui.components.loading.AppLoadingOverlay
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.createTempPictureUri
import com.policyboss.customer.utils.extension.findActivity
import com.policyboss.customer.utils.extension.showAppSnackbar
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun DriversLicenseRoute(
    viewModel: DriversLicenseViewModel,
    journeyViewModel: ClaimJourneyViewModel, // 🚀 ADDED: The Shared ViewModel
    onNavigateBack: () -> Unit,
    onNavigateToSuccess: () -> Unit // 🚀 Changed from onSubmitClaim
) {

    val context = LocalContext.current
    val activity = context.findActivity()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showLoader by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val globalSnackbar = LocalAppSnackbar.current

    // ==========================================
    // ⭐ BOTTOM SHEET & CAMERA STATES
    // ==========================================
    var showBottomSheet by remember { mutableStateOf(false) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }
    var showPermissionSettingsDialog by remember { mutableStateOf(false) }

    // 1. GALLERY LAUNCHER
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { viewModel.onAction(DriversLicenseAction.OnDocumentSelected(it)) }
    }

    // 2. FILES LAUNCHER (For PDFs or Images)
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { viewModel.onAction(DriversLicenseAction.OnDocumentSelected(it)) }
    }

    // 3. CAMERA LAUNCHER
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            tempCameraUri?.let { uri ->
                viewModel.onAction(DriversLicenseAction.OnDocumentSelected(uri))
            }
        }
    }

    // 4. PERMISSION LAUNCHER
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = context.createTempPictureUri()
            tempCameraUri = uri
            cameraLauncher.launch(uri)
        } else {
            val shouldShowRationale = activity?.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) == true
            if (shouldShowRationale) {
                coroutineScope.launch {
                    globalSnackbar.showAppSnackbar("Camera access is needed to capture your license.")
                }
            } else {
                showPermissionSettingsDialog = true
            }
        }
    }

    // ==========================================
    // ⭐ VIEWMODEL EVENT LISTENERS
    // ==========================================
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
                    // 🚀 Show success message, wait half a second, then navigate
                    coroutineScope.launch {
                        globalSnackbar.showAppSnackbar(message = "Claim filed successfully!")
                        kotlinx.coroutines.delay(300.milliseconds)
                        onNavigateToSuccess()
                    }
                }
                is ClaimJourneyEvent.ShowError -> {
                    globalSnackbar.showAppSnackbar(message = event.message)
                }
            }
        }
    }

    // 🚀 3. Draw the UI at the root of the Composable, reacting to the state

    // 🚀 WRAP THE SCREEN IN THE OVERLAY
    AppLoadingOverlay(
        isLoading = showLoader,
        message = "Filing your claim..." // Custom message for this screen
    ) {

        SingleDocumentUploadScreen(
            title = "Driver's License",
            step = 5,
            totalSteps = 5,
            documentUri = uiState.documentUri,
            errorMessage = uiState.errorMessage,
            emptyButtonText = "Confirm and Continue",
            selectedButtonText = "File a Claim",
            informationTitle = "Helps us process your claim faster",
            informationItems = listOf(
                "Ensure the document is clear and readable",
                "All corners of the license should be visible",
                "You can edit or add more photos later"
            ),

            // 🚀 ONLY CHANGE HERE: Open the sheet instead of the gallery!
            onUploadClick = { showBottomSheet = true },

            onRemoveClick = { viewModel.onAction(DriversLicenseAction.OnRemoveDocument) },
            onContinueClick = { viewModel.onAction(DriversLicenseAction.OnSubmitClick) },
            onBackClick = onNavigateBack
        )
    }

    // ==========================================
    // ⭐ BOTTOM SHEET COMPONENT
    // ==========================================
    if (showBottomSheet) {
        UploadBottomSheet(
            onDismiss = { showBottomSheet = false },
            onCameraClick = {
                showBottomSheet = false
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            },
            onGalleryClick = {
                showBottomSheet = false
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            onFilesClick = {
                showBottomSheet = false
                fileLauncher.launch(arrayOf("image/*", "application/pdf"))
            }


        )
    }


}