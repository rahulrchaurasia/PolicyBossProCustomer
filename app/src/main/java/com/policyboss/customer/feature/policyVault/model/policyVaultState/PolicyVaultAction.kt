package com.policyboss.customer.feature.policyVault.model.policyVaultState

import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyCategory
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyVaultPolicy
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.SortOption

sealed interface PolicyVaultAction1 {
    object firstClick : PolicyVaultAction
    object secondClick : PolicyVaultAction

    object OnNavigateToDetailsClick : PolicyVaultAction

    data class OnTabSelected(
        val tab: Int
    ) : PolicyVaultAction

}

sealed interface PolicyVaultAction {

    data class OnCategorySelected(
        val category: PolicyCategory
    ) : PolicyVaultAction

    data class OnSortOptionSelected(
        val sortOption: SortOption
    ) : PolicyVaultAction


    // NEW: Action for when a specific policy type is picked from the bottom sheet
    data class OnAddPolicyTypeSelected(
        val type: AddPolicyType
    ) : PolicyVaultAction

    data object OnSyncMailClick : PolicyVaultAction


    data class OnRenewClick(
        val policy: PolicyVaultPolicy
    ) : PolicyVaultAction

    data class OnViewDetailsClick(
        val policy: PolicyVaultPolicy
    ) : PolicyVaultAction



    data object OnNavigateToDetailsClick : PolicyVaultAction

}