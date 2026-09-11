package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.state

data class SyncContactsUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed interface SyncContactsAction {
    object OnSyncContactsClick : SyncContactsAction
}

sealed interface SyncContactsEvent {
    object RequestContactPermission : SyncContactsEvent // Useful if you need to ask for permission first
    object NavigateNext : SyncContactsEvent
    data class ShowError(val message: String) : SyncContactsEvent
}