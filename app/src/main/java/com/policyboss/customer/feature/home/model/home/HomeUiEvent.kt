package com.policyboss.customer.feature.home.model.home



/*
  You are using UDF (Unidirectional Data Flow).

             UI
             ↓
            Action
             ↓
            Route
             ↓
            ViewModel
             ↓
            State
             ↓
             UI

   This is the recommended Compose architecture.
 */

// ************************************************************
//ViewModel emits an event, and LaunchedEffect collects/observe
// hat event and performs navigation or other one-time UI actions.
//***************************************************************

/*
5. Why LaunchedEffect?

This is the part many developers misunderstand.

You have:

_uiEvent.send(
    HomeUiEvent.NavigateToPrivilegeSuccess
)

inside ViewModel.

ViewModel CANNOT navigate.

ViewModel should never know:

NavController
Navigator
Activity
Context

So ViewModel emits:

NavigateToPrivilegeSuccess

Then Route observes:

LaunchedEffect(...)
viewModel.uiEvent.collect { event ->
    when(event) {
        is HomeUiEvent.NavigateToPrivilegeSuccess ->
            onNavigateToPrivilege()
    }
}
 */

sealed interface HomeUiEvent {
    // Navigation events triggered by business logic success
    object NavigateToPrivilegeSuccess : HomeUiEvent

//    data object ShowPolicyBottomSheet : HomeUiEvent
//
//    data object HidePolicyBottomSheet : HomeUiEvent
    // UI feedback events
    data class ShowSnackbar(val message: String) : HomeUiEvent


    data class OpenDialer(
        val phoneNumber: String
    ) : HomeUiEvent

    
    // Example of future events you might need:
    // object NavigateToLogin : HomeUiEvent (e.g., if token expires)
    // object ShowRateAppDialog : HomeUiEvent
}