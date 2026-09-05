package com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.policyReport




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
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component.SingleDocumentUploadScreen
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel.PoliceReportViewModel
import com.policyboss.customer.ui.components.bottomSheet.UploadBottomSheet
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.createTempPictureUri
import com.policyboss.customer.utils.extension.findActivity
import com.policyboss.customer.utils.extension.showAppSnackbar
import kotlinx.coroutines.launch

// Imports omitted for brevity...

@Composable
fun PoliceReportRoute(
    viewModel: PoliceReportViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (Uri?) -> Unit
) {

    val context = LocalContext.current
    val activity = context.findActivity()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
        uri?.let { viewModel.onAction(PoliceReportAction.OnDocumentSelected(it)) }
    }

    // 2. FILES LAUNCHER (Great for PDF Police Reports!)
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { viewModel.onAction(PoliceReportAction.OnDocumentSelected(it)) }
    }

    // 3. CAMERA LAUNCHER
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            tempCameraUri?.let { uri ->
                viewModel.onAction(PoliceReportAction.OnDocumentSelected(uri))
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
                    globalSnackbar.showAppSnackbar("Camera access is needed to capture the police report.")
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
            if (event is PoliceReportUiEvent.NavigateNext) onNavigateNext(event.uri)
        }
    }
    // ==========================================
    // ⭐ UI RENDERING
    // ==========================================
    SingleDocumentUploadScreen(
        title = "Police Report (If applicable)",
        step = 4,
        totalSteps = 5,
        documentUri = uiState.documentUri,
        emptyButtonText = "Skip & Continue",
        selectedButtonText = "Confirm and Continue",
        informationTitle = "When is a Police Report required?",
        informationItems = listOf(
            "Theft or vandalism incidents",
            "Accidents involving a third party",
            "Major property damage"
        ),

        // 🚀 ONLY CHANGE HERE: Open the bottom sheet!
        onUploadClick = { showBottomSheet = true },

        onRemoveClick = { viewModel.onAction(PoliceReportAction.OnRemoveDocument) },
        onContinueClick = { viewModel.onAction(PoliceReportAction.OnContinueClick) },
        onBackClick = onNavigateBack
    )

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