package com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state

import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

sealed interface ClaimSupportUiEvent {

    // Split navigation based on the user's intent
    data class NavigateToFileClaimFlow(val product: AddPolicyType) : ClaimSupportUiEvent
    data class NavigateToClaimGuide(val product: AddPolicyType) : ClaimSupportUiEvent

    // Direct Navigations (No bottom sheet required)
    data object OpenCashlessGarage : ClaimSupportUiEvent
    data object OpenInsurerContacts : ClaimSupportUiEvent
    data object OpenFaq : ClaimSupportUiEvent

    // 🚀 Change this from a 'data object' to a 'data class' with a parameter
    data class OpenSupportDialer(val phoneNumber: String) : ClaimSupportUiEvent

    data class ShowSnackbar(val message: String) : ClaimSupportUiEvent
}