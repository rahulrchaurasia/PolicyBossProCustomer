package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.viemodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.state.SyncProcessingAction
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.state.SyncProcessingEvent
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.state.SyncProcessingState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SyncContactsProcessingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SyncProcessingState())
    val uiState: StateFlow<SyncProcessingState> = _uiState.asStateFlow()

    // Channel for one-time events (Navigation, Toasts)
    private val _uiEvent = Channel<SyncProcessingEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        // We can trigger the initial action right away
        onAction(SyncProcessingAction.StartSync)
    }

    // Single entry point for all UI interactions
    fun onAction(action: SyncProcessingAction) {
        when (action) {
            is SyncProcessingAction.StartSync -> startSyncProcess()
            is SyncProcessingAction.CompleteProfileClicked -> handleCompleteProfile()
        }
    }

    private fun startSyncProcess() {
        viewModelScope.launch {
            for (i in 1..10) {
                delay(300) 
                _uiState.update { 
                    it.copy(
                        progress = i / 10f, 
                        contactsFound = (219 * (i / 10f)).toInt()
                    ) 
                }
            }
            
            _uiState.update { 
                it.copy(
                    isSyncing = false,
                    isSuccess = true,
                    progress = 1f,
                    contactsFound = 219
                )
            }
        }
    }

    private fun handleCompleteProfile() {
        viewModelScope.launch {
            // Do any backend tracking here if needed, then trigger navigation event
            _uiEvent.send(SyncProcessingEvent.NavigateToHome)
        }
    }
}