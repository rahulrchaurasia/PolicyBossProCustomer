package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.ui


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.viewmodel.DamagePhotosViewModel
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.extension.showAppSnackbar

@Composable
fun DamagePhotosRoute(
    viewModel: DamagePhotosViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (List<Uri>) -> Unit, // Passes data to the Parent Graph ViewModel
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 5)
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onAction(DamagePhotosAction.OnPhotosSelected(uris))
        }
    }


  // Grab the global state instantly
    val globalSnackbar = LocalAppSnackbar.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                // 🚀 Safely read the photos directly from the event payload
                is DamagePhotosUiEvent.NavigateNext -> onNavigateNext(event.photos)
                is DamagePhotosUiEvent.ShowError -> {

                    // Shows the error at the MainActivity level!
                    globalSnackbar.showAppSnackbar(message = event.message)
                }
            }
        }
    }

    DamagePhotosScreen(
        uiState = uiState,
        onAction = { action ->
            if (action is DamagePhotosAction.OnUploadClick) {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            } else {
                viewModel.onAction(action)
            }
        },
        onBackClick = onNavigateBack,
        modifier = modifier
    )
}