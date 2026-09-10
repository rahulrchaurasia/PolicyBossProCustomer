package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state.EmailPanAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state.EmailPanEvent
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.viewmodel.PrivilegeEmailPanViewModel
import com.policyboss.customer.ui.components.snackBar.LocalAppSnackbar
import com.policyboss.customer.utils.extension.showAppSnackbar

@Composable
fun PrivilegeEmailPanRoute(
    viewModel: PrivilegeEmailPanViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val globalSnackbar = LocalAppSnackbar.current

    val emailFocusRequester = remember { FocusRequester() }
    val panFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is EmailPanEvent.NavigateNext -> onNavigateNext()
                is EmailPanEvent.ShowError -> globalSnackbar.showAppSnackbar(event.message)
                EmailPanEvent.FocusEmail -> emailFocusRequester.requestFocus()
                EmailPanEvent.FocusPan -> panFocusRequester.requestFocus()
            }
        }
    }

    PrivilegeEmailPanContent(
        uiState = uiState,
        onEmailChange = { viewModel.onAction(EmailPanAction.OnEmailChanged(it)) },
        onPanChange = { viewModel.onAction(EmailPanAction.OnPanChanged(it)) },
        onContinueClick = { viewModel.onAction(EmailPanAction.OnContinueClick) },
        onBackClick = onNavigateBack,
        modifier = modifier
    )
}