package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.ui


// Assuming this is where you placed the location helper
import android.Manifest


import android.app.Activity
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
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.viewmodel.AccidentDetailsViewModel

import com.policyboss.customer.ui.components.loading.AppLoadingOverlay
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.extension.findActivity
import com.policyboss.customer.utils.extension.showAppSnackbar
import com.policyboss.customer.utils.location.LocationResult
import com.policyboss.customer.utils.location.LocationSettingsResult
import com.policyboss.customer.utils.location.checkLocationHardwareSettings
import com.policyboss.customer.utils.location.fetchCurrentLocationAndAddress
import com.policyboss.customer.utils.permission.PermissionRationaleDialog
import com.policyboss.customer.utils.permission.hasLocationPermission
import kotlinx.coroutines.launch

// ==========================================
// THE UDF CYCLE: LOCATION FETCH
// ==========================================
//region  LOCATION FETCH
/*
// STEP 1 (Route): Construct the Action payload and send it to the ViewModel
viewModel.onAction(
    AccidentDetailsAction.OnCurrentLocationFetched(address, lat, lng)
)

// STEP 2 (ViewModel): Extract payload from the Action and mutate the single source of truth (UiState)
is AccidentDetailsAction.OnCurrentLocationFetched -> {
    _uiState.update {
        it.copy(
            location = action.address,
            latitude = action.lat,
            longitude = action.lng,
            isAutoLocation = true
        )
    }
}

// STEP 3 (Screen): Compose automatically observes the new UiState and triggers a recomposition
val uiState by viewModel.uiState.collectAsStateWithLifecycle()
 */
//endregion

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
    // ⭐ STEP 3: THE FINAL FETCH
    // ==========================================
    val performLocationFetch: () -> Unit = {
        isFetchingLocation = true
        coroutineScope.launch {
            try { // 🚀 Wrap in try/finally to guarantee loading spinner hides
                when (val result = fetchCurrentLocationAndAddress(context)) {
                    is LocationResult.Success -> {

                  // step1 :The AccidentDetailsRoute fetches the raw GPS data from the device.
                  // It packages this data into the OnCurrentLocationFetched data class (using the address, lat, and lng parameters)
                  // and fires it into the ViewModel.

                        viewModel.onAction(
                            AccidentDetailsAction.OnCurrentLocationFetched(
                                address = result.address,
                                lat = result.lat,
                                lng = result.lng
                            )
                        )
                    }
                    LocationResult.NoFixAvailable -> globalSnackbar.showAppSnackbar("Couldn't get a location fix. Try moving outside.")
                    LocationResult.PermissionDenied -> globalSnackbar.showAppSnackbar("Location permission was revoked.")
                    LocationResult.Unknown -> globalSnackbar.showAppSnackbar("Something went wrong fetching your location.")
                }
            } finally {
                isFetchingLocation = false
            }
        }
    }

    // ==========================================
    // ⭐ STEP 2: GPS HARDWARE LAUNCHER
    // ==========================================
    val gpsResolutionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            performLocationFetch()
        } else {
            coroutineScope.launch {
                globalSnackbar.showAppSnackbar("Location services must be enabled to auto-fill your address.")
            }
        }
    }

    val verifyGpsAndFetch: () -> Unit = {
        coroutineScope.launch {
            when (val settingsResult = checkLocationHardwareSettings(context)) {
                is LocationSettingsResult.Satisfied -> performLocationFetch()
                is LocationSettingsResult.Resolvable -> gpsResolutionLauncher.launch(settingsResult.intentSender)
                LocationSettingsResult.Unresolvable -> globalSnackbar.showAppSnackbar("Please enable location services manually in your phone settings.")
            }
        }
    }

    // ==========================================
    // ⭐ STEP 1: APP PERMISSION LAUNCHER
    // ==========================================
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isFineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val isCoarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (isFineGranted || isCoarseGranted) {
            verifyGpsAndFetch()
        } else {
            // 🚀 Check rationale for BOTH Fine and Coarse
            val shouldShowRationale = activity?.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) == true ||
                    activity?.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) == true

            if (shouldShowRationale) {
                coroutineScope.launch { globalSnackbar.showAppSnackbar("Location access is needed to auto-fill your accident location.") }
            } else {
                showLocationSettingsDialog = true
            }
        }
    }

    // ==========================================
    // ⭐ EVENT LISTENER
    // ==========================================
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AccidentDetailsUiEvent.NavigateNext -> onNavigateNext(uiState)
                is AccidentDetailsUiEvent.ShowError -> {
                    globalSnackbar.showAppSnackbar(event.message)
                }
            }
        }
    }



    // ==========================================
    // ⭐ UI & DIALOG RENDERING
    // ==========================================

    // Show a loading overlay while waiting for GPS to connect
    AppLoadingOverlay(
        isLoading = isFetchingLocation || uiState.isLoading, // 🚀 Checks both!
        message = if (isFetchingLocation) "Locating you..." else "Saving details..."
    ) {
        AccidentDetailsScreen(
            uiState = uiState,
            onAction = { action ->
                // 🚀 Intercept the button click to launch permissions


                if (action is AccidentDetailsAction.OnUseCurrentLocationClick) {
                    if (hasLocationPermission(context)) {
                        verifyGpsAndFetch()
                    } else {
                        locationPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
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
