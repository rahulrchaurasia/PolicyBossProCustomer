package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state

// State holds all UI data
data class VerifyPanUiState(
    val panNumber: String = "NILPS5066N", // Pre-filled from previous step
    val isPanValid: Boolean = true,
    val fullName: String = "",
    val isFullNameError: Boolean = false,
    val fullNameErrorMessage: String? = null,
    val dob: String = "",
    val isDobError: Boolean = false,
    val dobErrorMessage: String? = null,
    val showDatePicker: Boolean = false
)

// Actions represent user intents
sealed interface VerifyPanAction {
    data class OnFullNameChanged(val name: String) : VerifyPanAction
    data class OnDobSelected(val dob: String) : VerifyPanAction
    object OnDobClick : VerifyPanAction
    object OnDismissDatePicker : VerifyPanAction
    object OnConfirmClick : VerifyPanAction
}

// Events represent one-off triggers (Navigation, Snackbars)
sealed interface VerifyPanEvent {
    object NavigateNext : VerifyPanEvent
    object NavigateBack : VerifyPanEvent
    object CloseJourney : VerifyPanEvent
    data class ShowError(val message: String) : VerifyPanEvent
}