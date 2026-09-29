package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.state



// 1. State (What the UI shows)
data class SyncProcessingState(
    val isSyncing: Boolean = true,
    val isSuccess: Boolean = false,
    val progress: Float = 0f,
    val contactsFound: Int = 0
)

// 2. Actions (What the user/UI tells the ViewModel to do)
sealed interface SyncProcessingAction {
    object StartSync : SyncProcessingAction
    object CompleteProfileClicked : SyncProcessingAction
}

// 3. Events (One-time triggers from ViewModel to UI, like Navigation)
sealed interface SyncProcessingEvent {
    object NavigateToHome : SyncProcessingEvent
    data class ShowError(val message: String) : SyncProcessingEvent
}