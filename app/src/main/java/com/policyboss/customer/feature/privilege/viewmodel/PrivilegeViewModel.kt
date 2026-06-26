package com.policyboss.customer.feature.privilege.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeAction
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeUiEvent
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrivilegeViewModel @Inject constructor(
    // TODO: Inject your Repositories or UseCases here later
    // private val privilegeRepository: PrivilegeRepository
) : ViewModel() {

    // 1. UI STATE: Holds the data the screen needs to draw itself.
    private val _uiState = MutableStateFlow(PrivilegeUiState())
    val uiState: StateFlow<PrivilegeUiState> = _uiState.asStateFlow()

    // 2. UI EVENTS: A Channel for fire-and-forget events (Navigation, Toasts, Intents)
    // We use a Channel because events should only be consumed once.
    private val _uiEvent = Channel<PrivilegeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // 3. ACTIONS: The single entry point for all UI interactions
    fun onAction(action: PrivilegeAction) {
        when (action) {
            is PrivilegeAction.OnSetupAccountClick -> {
                // Example: Trigger navigation event
               // sendEvent(PrivilegeUiEvent.NavigateToSetupSteps)
            }
            
            is PrivilegeAction.OnJoinPrivilegeClick -> {
                // Example: Call an API, show loading, then handle result
                joinPrivilege()
            }
            
//            is PrivilegeAction.OnGetAssistanceClick -> {
//                // Example: Trigger the dialer intent event
//                // (Replace with actual RM phone number from your data)
//                sendEvent(PrivilegeUiEvent.OpenDialer(phoneNumber = "+919876543210"))
//            }
        }
    }

    // --- Private Helper Methods ---

    private fun joinPrivilege() {
        viewModelScope.launch {
            // Update state to show a loading spinner
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                // TODO: Make your API call here
                // val result = privilegeRepository.joinPrivilege()
                
                // On Success:
                _uiState.value = _uiState.value.copy(isLoading = false)
                sendEvent(PrivilegeUiEvent.ShowSnackbar("Successfully joined Privilege!"))
                
            } catch (e: Exception) {
                // On Error:
                _uiState.value = _uiState.value.copy(isLoading = false)
                sendEvent(PrivilegeUiEvent.ShowSnackbar(e.message ?: "An error occurred"))
            }
        }
    }

    /**
     * Helper function to send one-time events cleanly.
     */
    private fun sendEvent(event: PrivilegeUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}