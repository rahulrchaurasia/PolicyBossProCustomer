package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state

data class EmailPanUiState(
    val email: String = "",
    val isEmailError: Boolean = false,
    val emailErrorMessage: String? = null,

    val panNumber: String = "",
    val isPanError: Boolean = false,
    val panErrorMessage: String? = null,

    val isLoading: Boolean = false
)

sealed interface EmailPanEvent {
    data object NavigateNext : EmailPanEvent
    data class ShowError(val message: String) : EmailPanEvent

    // Add focus events
    data object FocusEmail : EmailPanEvent
    data object FocusPan : EmailPanEvent
}

sealed interface EmailPanAction {
    data class OnEmailChanged(val email: String) : EmailPanAction
    data class OnPanChanged(val pan: String) : EmailPanAction
    data object OnContinueClick : EmailPanAction
}