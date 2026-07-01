package com.policyboss.customer.feature.claimSupport.model.claimSupportState


data class ClaimSupportUiState(
    val isLoading: Boolean = false,
    val currentSetupStep: Int = 1
)