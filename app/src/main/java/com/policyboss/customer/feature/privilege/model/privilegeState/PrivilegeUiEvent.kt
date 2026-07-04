package com.policyboss.customer.feature.privilege.model.privilegeState



// 3. One-time events from ViewModel to UI
sealed interface PrivilegeUiEvent {


    data class ShowSnackbar(val message: String) : PrivilegeUiEvent

    object NavigateToFullScreenVideo : PrivilegeUiEvent // Add this!
    // ...
}