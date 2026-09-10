package com.policyboss.customer.feature.tabfeatures.policyVault.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyCategory
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyVaultPolicy
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.SortOption
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultAction
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultUiEvent
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PolicyVaultViewModel @Inject constructor() : ViewModel() {

    // ------------------------------------------------------------
    // Master Data
    // ------------------------------------------------------------

    private val allPolicies =
        AppDummyData.mockPolicies

    // 1. Add SortOption to State


    private val _uiState = MutableStateFlow(
        PolicyVaultUiState(
            policies = allPolicies,
            selectedCategory = PolicyCategory.ALL,
            selectedSortOption = SortOption.UPCOMING_RENEWALS // Default sort
        )
    )

    val uiState =  _uiState.asStateFlow()


    // ----------------------------------------------------------------
    // One-time Events
    // ----------------------------------------------------------------

    private val _uiEvent = Channel<PolicyVaultUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // ----------------------------------------------------------------
    // Actions
    // ----------------------------------------------------------------

    fun onAction(
        action: PolicyVaultAction
    ) {

        when (action) {

            is PolicyVaultAction.OnCategorySelected -> {

                _uiState.update { it.copy(selectedCategory = action.category) }
                applyFiltersAndSort() // Re-evaluate list
            }

            is PolicyVaultAction.OnSortOptionSelected -> {
                _uiState.update { it.copy(selectedSortOption = action.sortOption) }
                applyFiltersAndSort() // Re-evaluate list
            }
            PolicyVaultAction.OnSyncMailClick -> {

                onSyncMailClick()
            }



            is PolicyVaultAction.OnRenewClick -> {

                onRenewClick(action.policy)
            }

            is PolicyVaultAction.OnViewDetailsClick -> {

                onViewDetailsClick(action.policy)
            }
//            is PolicyVaultAction.OnAddPolicyTypeSelected -> {
//
//                viewModelScope.launch {
//                    _uiEvent.send(PolicyVaultUiEvent.NavigateToAddManualPolicy(action.type))
//
//                }
//            }


            else -> {

            }
        }
    }


    // 2. Centralized filtering and sorting logic
    private fun applyFiltersAndSort() {
        val currentState = _uiState.value

        // Step A: Filter by Category
        val filteredList = if (currentState.selectedCategory == PolicyCategory.ALL) {
            allPolicies
        } else {
            allPolicies.filter { it.category == currentState.selectedCategory }
        }

        // Step B: Apply Sorting
        val sortedList = when (currentState.selectedSortOption) {
            SortOption.UPCOMING_RENEWALS -> {
                // Assuming positive daysLeft means upcoming, sort ascending
                filteredList.sortedBy { it.daysLeft }
            }
            SortOption.MOST_RECENT -> {
                // Highest timestamp first
                filteredList.sortedByDescending { it.issueDateMillis }
            }
            SortOption.PREMIUM_HIGH_TO_LOW -> {
                filteredList.sortedByDescending { it.premiumAmount }
            }
            SortOption.PREMIUM_LOW_TO_HIGH -> {
                filteredList.sortedBy { it.premiumAmount }
            }
        }

        // Step C: Push updated list to UI
        _uiState.update { it.copy(policies = sortedList) }
    }


    private fun onSyncMailClick() {

        viewModelScope.launch {

            _uiEvent.send(
                PolicyVaultUiEvent.SyncMail
            )
        }
    }

    private fun onAddPolicyClick() {

        viewModelScope.launch {

            _uiEvent.send(
                PolicyVaultUiEvent.AddPolicy
            )
        }
    }

    private fun onRenewClick(
        policy: PolicyVaultPolicy
    ) {

        viewModelScope.launch {

            _uiEvent.send(
                PolicyVaultUiEvent.RenewPolicy(policy)
            )
        }
    }

    private fun onViewDetailsClick(
        policy: PolicyVaultPolicy
    ) {

        viewModelScope.launch {

            _uiEvent.send(
                PolicyVaultUiEvent.ViewPolicy(policy)
            )
        }
    }
}
