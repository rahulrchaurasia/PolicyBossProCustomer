package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.ui


// Assuming this is where you placed the location helper
import androidx.activity.compose.rememberLauncherForActivityResult
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
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.viewmodel.AccidentDetailsViewModel
import com.policyboss.customer.ui.components.loading.AppLoadingOverlay
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.extension.findActivity
import com.policyboss.customer.utils.extension.showAppSnackbar
import com.policyboss.customer.utils.location.fetchCurrentLocationAndAddress
import com.policyboss.customer.utils.permission.PermissionRationaleDialog
import kotlinx.coroutines.launch

import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await // 👈 This one is very easy to miss!



@Composable
fun AccidentDetailsRoute(
    viewModel: AccidentDetailsViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (AccidentDetailsUiState) -> Unit, // Pass state up to save in Journey ViewModel
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context.findActivity()
    val coroutineScope = rememberCoroutineScope()
    val globalSnackbar = LocalAppSnackbar.current

    // State for the reusable settings dialog
    var showLocationSettingsDialog by remember { mutableStateOf(false) }
    var isFetchingLocation by remember { mutableStateOf(false) }


    // ==========================================
    // ⭐ LOCATION PERMISSION LAUNCHER
    // ==========================================
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions.entries.any { it.value } // True if either Fine or Coarse is granted

        if (isGranted) {
            // Permission Granted -> Fetch Location
            isFetchingLocation = true
            coroutineScope.launch {
                val locationData = fetchCurrentLocationAndAddress(context)
                if (locationData != null) {
                    viewModel.onAction(
                        AccidentDetailsAction.OnCurrentLocationFetched(
                            address = locationData.first,
                            lat = locationData.second,
                            lng = locationData.third
                        )
                    )
                } else {
                    globalSnackbar.showAppSnackbar("Could not determine location. Please try again.")
                }
                isFetchingLocation = false
            }
        } else {
            // Permission Denied -> Check Rationale
            val shouldShowRationale = activity?.shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION) == true

            if (shouldShowRationale) {
                coroutineScope.launch {
                    globalSnackbar.showAppSnackbar("Location access is needed to auto-fill your accident location.")
                }
            } else {
                // Permanently Denied -> Show Settings Dialog
                showLocationSettingsDialog = true
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AccidentDetailsUiEvent.NavigateNext -> onNavigateNext(uiState)
                is AccidentDetailsUiEvent.ShowError -> { /* Show Snackbar */ }
            }
        }
    }

//    AccidentDetailsScreen(
//        uiState = uiState,
//        onAction = viewModel::onAction,
//        onBackClick = onNavigateBack,
//        modifier = modifier
//    )

    // ==========================================
    // ⭐ UI & DIALOG RENDERING
    // ==========================================

    // Show a loading overlay while waiting for GPS to connect
    AppLoadingOverlay(
        isLoading = isFetchingLocation,
        message = "Locating you..."
    ) {
        AccidentDetailsScreen(
            uiState = uiState,
            onAction = { action ->
                // 🚀 Intercept the button click to launch permissions
                if (action is AccidentDetailsAction.OnUseCurrentLocationClick) {
                    locationPermissionLauncher.launch(
                        arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                } else {
                    viewModel.onAction(action)
                }
            },
            onBackClick = onNavigateBack,
            modifier = modifier
        )
    }

    // Render the reusable dialog if permanently denied
    if (showLocationSettingsDialog) {
        PermissionRationaleDialog(
            title = "Location Permission Required",
            description = "You have permanently denied location access. Please enable it in Settings to use your current location.",
            onDismiss = { showLocationSettingsDialog = false }
        )
    }
}
