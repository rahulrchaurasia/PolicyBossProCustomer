package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.ui

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
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.viewmodel.CashlessGarageViewModel
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

@Composable
fun CashlessGarageRoute(
    viewModel: CashlessGarageViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context.findActivity()
    val coroutineScope = rememberCoroutineScope()
    val globalSnackbar = LocalAppSnackbar.current

    var showLocationSettingsDialog by remember { mutableStateOf(false) }
    var isFetchingLocation by remember { mutableStateOf(false) }


    // ==========================================
    // LOCATION FETCH LOGIC (Reused from Accident Details)
    // ==========================================
    val performLocationFetch: () -> Unit = {
        isFetchingLocation = true
        coroutineScope.launch {
            try {
                when (val result = fetchCurrentLocationAndAddress(context)) {
                    is LocationResult.Success -> {
                        viewModel.onAction(
                            CashlessGarageAction.OnCurrentLocationFetched(
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

    val gpsResolutionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            performLocationFetch()
        } else {
            coroutineScope.launch { globalSnackbar.showAppSnackbar("Location services must be enabled to auto-fill your address.") }
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

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isFineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val isCoarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (isFineGranted || isCoarseGranted) {
            verifyGpsAndFetch()
        } else {
            val shouldShowRationale = activity?.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) == true ||
                    activity?.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) == true

            if (shouldShowRationale) {
                coroutineScope.launch { globalSnackbar.showAppSnackbar("Location access is needed to find garages near you.") }
            } else {
                showLocationSettingsDialog = true
            }
        }
    }

    // ==========================================
    // EVENTS
    // ==========================================
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CashlessGarageUiEvent.NavigateNext -> onNavigateToResults()
                is CashlessGarageUiEvent.ShowError -> globalSnackbar.showAppSnackbar(event.message)
            }
        }
    }

    // ==========================================
    // UI RENDERING
    // ==========================================
    AppLoadingOverlay(
        isLoading = isFetchingLocation || uiState.isLoading,
        message = if (isFetchingLocation) "Locating you..." else "Searching..."
    ) {
        CashlessGarageScreen(

            uiState = uiState,
            onAction = { action ->
                if (action is CashlessGarageAction.OnUseCurrentLocationClick) {
                    if (hasLocationPermission(context)) {
                        verifyGpsAndFetch()
                    } else {
                        locationPermissionLauncher.launch(
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
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

    if (showLocationSettingsDialog) {
        PermissionRationaleDialog(
            title = "Location Permission Required",
            description = "You have permanently denied location access. Please enable it in Settings.",
            onDismiss = { showLocationSettingsDialog = false }
        )
    }
}

