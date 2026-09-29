package com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState

import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyCategory
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyVaultPolicy
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.SortOption


data class PolicyVaultUiState(

    val selectedCategory: PolicyCategory = PolicyCategory.ALL,

    val policies: List<PolicyVaultPolicy> = emptyList(),

    val selectedSortOption: SortOption = SortOption.UPCOMING_RENEWALS,

    val isLoading: Boolean = false,

    )



