package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.ui


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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.viewmodel.DamagePhotosViewModel
import com.policyboss.customer.ui.components.bottomSheet.UploadBottomSheet
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.createTempPictureUri
import com.policyboss.customer.utils.extension.findActivity
import com.policyboss.customer.utils.extension.showAppSnackbar
import kotlinx.coroutines.launch

@Composable
fun DamagePhotosRoute(
    viewModel: DamagePhotosViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (List<Uri>) -> Unit, // Passes data to the Parent Graph ViewModel
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val activity = context.findActivity() // 🚀 Get the activity safely

    // Grab the global snackbar instantly
    val globalSnackbar = LocalAppSnackbar.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // State to show the Settings Dialog if permanently denied
    var showPermissionSettingsDialog by remember { mutableStateOf(false) }

    // UI State for Bottom Sheet
    var showBottomSheet by remember { mutableStateOf(false) }

    // State to securely hold the temporary Camera Uri while the camera is open
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }



    // 1. GALLERY LAUNCHER (Multiple Selection)
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 5)
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onAction(DamagePhotosAction.OnPhotosSelected(uris))
        }
    }
    // 2. FILES LAUNCHER (Multiple Selection)
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onAction(DamagePhotosAction.OnPhotosSelected(uris))
        }
    }

    // 3. ACTUAL CAMERA LAUNCHER
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            // If photo was taken successfully, add it to our list!
            tempCameraUri?.let { uri ->
                viewModel.onAction(DamagePhotosAction.OnPhotosSelected(listOf(uri)))
            }
        }
    }

    // 4. CAMERA PERMISSION LAUNCHER
    // CAMERA PERMISSION LAUNCHER
    // 1. Declare the coroutine scope inside your @Composable function
    val coroutineScope = rememberCoroutineScope()
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Granted! Open the camera
            val uri = context.createTempPictureUri()
            tempCameraUri = uri
            cameraLauncher.launch(uri)
        } else {
            // Denied! Let's check how strictly they denied it
            val shouldShowRationale = activity?.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) == true

            if (shouldShowRationale) {
                // Case 1: Standard Deny -> Launch coroutine to show snackbar
                coroutineScope.launch {
                    globalSnackbar.showAppSnackbar(
                        message = "Camera access is needed to capture damage photos."
                    )
                }
            } else {
                // Case 2: Permanently Denied -> Trigger dialog
                showPermissionSettingsDialog = true
            }
        }
    }





    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is DamagePhotosUiEvent.NavigateNext -> onNavigateNext(event.photos)
                is DamagePhotosUiEvent.ShowError -> {
                    globalSnackbar.showAppSnackbar(message = event.message)
                }

                else -> {}
            }
        }
    }

    // Main Screen UI
    // Main Screen UI
    DamagePhotosScreen(
        uiState = uiState,
        onAction = { action ->
            if (action is DamagePhotosAction.OnUploadClick) {
                //// Intercepted!
                showBottomSheet = true // Open the sheet instead of directly launching gallery
            } else {
                viewModel.onAction(action) // Sent to ViewModel
            }
        },
        onBackClick = onNavigateBack,
        modifier = modifier
    )

// Bottom Sheet Overlay
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
                // Accept common image types and PDFs (*/* allows anything)
                fileLauncher.launch(arrayOf("image/*", "application/pdf"))
            }
        )
    }
}