package com.policyboss.customer.feature.claimSupport.model.claimSupportState



sealed interface ClaimSupportUiEvent {
    object event1 : ClaimSupportUiEvent

    data class ShowSnackbar(val message: String) : ClaimSupportUiEvent
    // ...
}