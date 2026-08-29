package com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimSupportMenu
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state.ClaimAction
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state.ClaimSupportUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state.ClaimUiState
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state.ProductSelectionContext
import com.policyboss.customer.feature.claimSupport.repository.ClaimRepository
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClaimViewModel @Inject constructor(
    private val repository: ClaimRepository // Injected!
) : ViewModel() {

    private val _uiState = MutableStateFlow(ClaimUiState())
    val uiState: StateFlow<ClaimUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ClaimSupportUiEvent>()
    val uiEvent: SharedFlow<ClaimSupportUiEvent> = _uiEvent.asSharedFlow()


    // 1. Add a state specifically to hold the data for the nested flow
    private val _activeFlowProduct = MutableStateFlow<AddPolicyType?>(null)
    val activeFlowProduct: StateFlow<AddPolicyType?> = _activeFlowProduct.asStateFlow()

    init {
        // 🚀 Auto-updates UI when the Repository Flow changes
        viewModelScope.launch {
            repository.submittedClaimsFlow.collect { claimsList ->
                _uiState.update { it.copy(myClaims = claimsList) }
            }
        }
    }
    fun onAction(action: ClaimAction) {
        when (action) {
            is ClaimAction.OnTabSelected -> {
                _uiState.update { it.copy(selectedTab = action.tab) }
            }
            ClaimAction.OnFileClaimClick -> {
                // Open sheet for filing a claim
                _uiState.update { it.copy(productSelectionContext = ProductSelectionContext.FileNewClaim) }
            }
            ClaimAction.OnDismissBottomSheet -> {
                dismissProductBottomSheet()
            }
            is ClaimAction.OnProductSelected -> {
                onProductSelected(action.product)
            }
            ClaimAction.OnSupportCallClick -> {
                viewModelScope.launch { _uiEvent.emit(ClaimSupportUiEvent.OpenSupportDialer) }
            }
            is ClaimAction.OnSupportMenuClick -> {
                onSupportMenuClicked(action.menu)
            }
        }
    }

    private fun dismissProductBottomSheet() {
        _uiState.update { it.copy(productSelectionContext = null) }
    }

    private fun onProductSelected(product: AddPolicyType) {
        // 1. Capture the context (why was the sheet open?)
        val context = _uiState.value.productSelectionContext

        // 2. Hide the bottom sheet immediately
        dismissProductBottomSheet()

        // 3. Emit the correct navigation event based on context
        viewModelScope.launch {
            when (context) {
                ProductSelectionContext.FileNewClaim -> {
                    // 2. Save it to the shared state BEFORE navigating
                    _activeFlowProduct.value = product
                    _uiEvent.emit(ClaimSupportUiEvent.NavigateToFileClaimFlow(product))
                }
                ProductSelectionContext.ViewClaimGuide -> {

                    // 2. Save it to the shared state BEFORE navigating
                    _activeFlowProduct.value = product
                    _uiEvent.emit(ClaimSupportUiEvent.NavigateToClaimGuide(product))
                }
                null -> { /* Ignore if context is somehow null */ }
            }
        }
    }

    private fun onSupportMenuClicked(menu: ClaimSupportMenu) {
        viewModelScope.launch {
            when (menu) {
                ClaimSupportMenu.FILE_GUIDE -> {
                    // This menu item requires a product, so open the bottom sheet with context
                    _uiState.update { it.copy(productSelectionContext = ProductSelectionContext.ViewClaimGuide) }
                }
                ClaimSupportMenu.CASHLESS_GARAGE -> {
                    _uiEvent.emit(ClaimSupportUiEvent.OpenCashlessGarage)
                }
                ClaimSupportMenu.INSURER_CONTACT -> {
                    _uiEvent.emit(ClaimSupportUiEvent.OpenInsurerContacts)
                }
                ClaimSupportMenu.FAQ -> {
                    _uiEvent.emit(ClaimSupportUiEvent.OpenFaq)
                }
            }
        }
    }


    // Optional: Call this when the flow is closed/cancelled to clear memory
    fun clearActiveFlow() {
        _activeFlowProduct.value = null
    }
}

