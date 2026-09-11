package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanEvent
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.viewmodel.VerifyPanViewModel
import com.policyboss.customer.ui.components.loading.AppLoadingOverlay
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.extension.showAppSnackbar

@Composable
fun VerifyPanRoute(
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit,
    onCloseJourney: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VerifyPanViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showLoader by remember { mutableStateOf(false) }
    val globalSnackbar = LocalAppSnackbar.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                VerifyPanEvent.NavigateNext -> onNavigateNext()
                VerifyPanEvent.NavigateBack -> onNavigateBack()
                VerifyPanEvent.CloseJourney -> onCloseJourney()
                is VerifyPanEvent.ShowError -> {
                     globalSnackbar.showAppSnackbar(event.message)
                }

                is VerifyPanEvent.Loading -> {
                    showLoader = event.isLoading
                }
            }
        }
    }

    // 🚀 WRAP THE SCREEN IN THE OVERLAY
    AppLoadingOverlay(
        isLoading = showLoader,
        message = "" // Custom message for this screen
    ) {
        VerifyPanScreen(
            uiState = uiState,
            onAction = viewModel::onAction,
            onBackClick = onNavigateBack,
            onCloseClick = onCloseJourney,
            modifier = modifier
        )
    }


}

