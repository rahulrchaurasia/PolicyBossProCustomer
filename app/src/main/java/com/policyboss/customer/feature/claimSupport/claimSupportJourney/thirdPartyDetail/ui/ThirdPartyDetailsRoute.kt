package com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.viewmodel.ThirdPartyDetailsViewModel


// Make sure to import your ViewModel location
// import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.viewmodel.ThirdPartyDetailsViewModel

@Composable
fun ThirdPartyDetailsRoute(
    viewModel: ThirdPartyDetailsViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: (ThirdPartyDetailsUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Handle one-time events (Navigation, Snackbars)
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is ThirdPartyUiEvent.NavigateNext -> {
                    // Pass the validated state back to the NavGraph to save in the Journey
                    onNavigateNext(uiState)
                }
                is ThirdPartyUiEvent.ShowError -> {
                    // Show a snackbar or toast here in the future
                }
            }
        }
    }

    // Render the UI
    ThirdPartyDetailsScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onNavigateBack,
        modifier = modifier
    )
}