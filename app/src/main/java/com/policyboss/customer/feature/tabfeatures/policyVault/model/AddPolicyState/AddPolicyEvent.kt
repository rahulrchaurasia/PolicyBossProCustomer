package com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState

sealed interface AddPolicyEvent {
    // One-time events sent from the ViewModel to the UI (like navigation)
   // data class SaveAndNavigate(val carNumber: String, val policyNumber: String) : AddPolicyEvent

    object NavigateBack : AddPolicyEvent

    data class ShowToast(val message: String) : AddPolicyEvent
}