package com.policyboss.customer.feature.policyVault.viewmodel

import androidx.lifecycle.ViewModel
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultUiEvent
import com.policyboss.customer.feature.policyVault.model.policyVaultState.PolicyVaultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


@HiltViewModel
class PolicyVaultViewModel @Inject constructor() : ViewModel() {


    //region Declaration
    // Private mutable state
    private val _uiState = MutableStateFlow(PolicyVaultUiState())
    // Public immutable state for Compose to observe
    val uiState: StateFlow<PolicyVaultUiState> = _uiState.asStateFlow()

    // The Channel for one-time navigation/snackbars
    private val _uiEvent = Channel<PolicyVaultUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


}