package com.policyboss.customer.feature.policyVault.model.policyVaultState

import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportAction
import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportUiEvent

data class PolicyVaultUiState(
    val isLoading: Boolean = false,
    val currentSetupStep: Int = 1
)

sealed interface PolicyVaultUiEvent {
    object event1 : PolicyVaultUiEvent

    data class ShowSnackbar(val message: String) : PolicyVaultUiEvent
    // ...
}

sealed interface PolicyVaultAction {
    object firstClick : PolicyVaultAction
    object secondClick : PolicyVaultAction
    // ...
}