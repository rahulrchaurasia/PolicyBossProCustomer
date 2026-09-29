package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state.EmailPanAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state.EmailPanEvent
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.EmailPanScreen.state.EmailPanUiState

import com.policyboss.customer.utils.AppValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrivilegeEmailPanViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EmailPanUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<EmailPanEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: EmailPanAction) {
        when (action) {
            is EmailPanAction.OnEmailChanged -> {
                _uiState.update { it.copy(email = action.email, isEmailError = false, emailErrorMessage = null) }
            }
            is EmailPanAction.OnPanChanged -> {
                // Enforce uppercase and restrict to max 10 characters for PAN
                val formattedPan = action.pan.uppercase().take(10)
                _uiState.update { it.copy(panNumber = formattedPan, isPanError = false, panErrorMessage = null) }
            }
            is EmailPanAction.OnContinueClick -> validateAndSubmit()


        }
    }

    private fun validateAndSubmit() {
        val state = _uiState.value


        // 1. Validate Email (Stops here if fails)
        if (!AppValidator.isValidEmail(state.email)) {
            _uiState.update {
                it.copy(isEmailError = true, emailErrorMessage = "Please enter a valid email address")
            }
            viewModelScope.launch { _uiEvent.emit(EmailPanEvent.FocusEmail) }
            return
        }
        // 2. Validate PAN (Stops here if fails)
        if (!AppValidator.isValidPanCard(state.panNumber)) {
            _uiState.update {
                it.copy(isPanError = true, panErrorMessage = "Please enter a valid PAN card number")
            }
            viewModelScope.launch { _uiEvent.emit(EmailPanEvent.FocusPan) }
            return
        }
        // Clear PAN error if fixed
        _uiState.update { it.copy(isPanError = false, panErrorMessage = null) }
        // 3. All valid, proceed
        viewModelScope.launch {
            _uiEvent.emit(EmailPanEvent.NavigateNext)
        }
    }
}