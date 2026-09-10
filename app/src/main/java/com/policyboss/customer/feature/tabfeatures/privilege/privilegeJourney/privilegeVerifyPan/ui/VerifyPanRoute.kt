package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanEvent
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.viewmodel.VerifyPanViewModel
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
            }
        }
    }

    VerifyPanScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        onBackClick = onNavigateBack,
        onCloseClick = onCloseJourney,
        modifier = modifier
    )
}

