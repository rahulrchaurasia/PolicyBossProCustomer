package com.policyboss.customer.feature.privilege.model.privilegeState



// 3. One-time events from ViewModel to UI
sealed interface PrivilegeUiEvent {
    object NavigateToQuiz : PrivilegeUiEvent

    data class ShowSnackbar(val message: String) : PrivilegeUiEvent
    // ...
}