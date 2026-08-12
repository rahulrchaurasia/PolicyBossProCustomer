package com.policyboss.customer.feature.policyVault.model.policyVaultState

import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyVaultPolicy


sealed interface PolicyVaultUiEvent {

    data object SyncMail : PolicyVaultUiEvent

    data object AddPolicy : PolicyVaultUiEvent

    data class RenewPolicy(
        val policy: PolicyVaultPolicy
    ) : PolicyVaultUiEvent

    data class ViewPolicy(
        val policy: PolicyVaultPolicy
    ) : PolicyVaultUiEvent



    data object ShowSortBottomSheet : PolicyVaultUiEvent


    data class NavigateToAddManualPolicy(val type: AddPolicyType) : PolicyVaultUiEvent

    data class ShowSnackbar(
        val message: String
    ) : PolicyVaultUiEvent
}