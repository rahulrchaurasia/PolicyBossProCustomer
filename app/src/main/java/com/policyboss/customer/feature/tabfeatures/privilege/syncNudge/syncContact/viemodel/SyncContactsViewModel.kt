package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.viemodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.state.SyncContactsAction
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.state.SyncContactsEvent
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.state.SyncContactsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// 1. Define the UI State

@HiltViewModel
class SyncContactsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SyncContactsUiState())
    val uiState: StateFlow<SyncContactsUiState> = _uiState.asStateFlow()

    // Use a Channel for one-time events (Navigation, Permission Requests)
    private val _uiEvent = Channel<SyncContactsEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // 1. Handle UI Actions
    fun onAction(action: SyncContactsAction) {
        when (action) {
            is SyncContactsAction.OnSyncContactsClick -> {
                // Instantly tell the UI to ask for permission
                viewModelScope.launch {
                    _uiEvent.send(SyncContactsEvent.RequestContactPermission)
                }
            }
        }
    }

    // 2. Called by the UI when permission is successfully granted
    fun onPermissionGranted() {
        viewModelScope.launch {
            _uiEvent.send(SyncContactsEvent.NavigateNext)
        }
    }
}