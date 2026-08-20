package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState

import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.RequirementItem
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

// 1. UI State: Holds all data needed to draw the screen
data class FileClaimUiState(
    val isLoading: Boolean = false,
    val productType: AddPolicyType? = null,
    val requirements: List<RequirementItem> = emptyList()
)