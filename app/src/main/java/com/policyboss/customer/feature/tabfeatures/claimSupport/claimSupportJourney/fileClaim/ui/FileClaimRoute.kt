package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.viewmodel.FileClaimViewModel
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.AddPolicyType


@Composable
fun FileClaimRoute(

    productType: AddPolicyType,
    viewModel: FileClaimViewModel,
    onNavigateBack: () -> Unit,
    onNavigateClose: () -> Unit,
    onNavigateNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 1. Initial Load
    LaunchedEffect(productType) {
        viewModel.onAction(FileClaimAction.LoadRequirements(productType))
    }

    // 2. Handle Navigation Events
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {

                FileClaimUiEvent.NavigateToNextStep -> onNavigateNext()
            }
        }
    }

    // 3. Render the UI
    FileClaimScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
        onBackClick = onNavigateBack,
        onCloseClick = onNavigateClose,
        onContinueClick = onNavigateNext,
    )

}