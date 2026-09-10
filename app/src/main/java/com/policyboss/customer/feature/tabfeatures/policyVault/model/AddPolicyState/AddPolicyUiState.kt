package com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState

import androidx.compose.runtime.Immutable

@Immutable
data class AddPolicyUiState(
    val carNumber: String = "",
    val policyNumber: String = "",
    val isLoading: Boolean = false, // <-- Added this
    val carNumberError: String? = null, // Add Error State
    val policyNumberError: String? = null, // Add Error State

) {
    // The form is valid if at least one field has text
    val isFormValid: Boolean
        get() = carNumber.isNotBlank() || policyNumber.isNotBlank()
}