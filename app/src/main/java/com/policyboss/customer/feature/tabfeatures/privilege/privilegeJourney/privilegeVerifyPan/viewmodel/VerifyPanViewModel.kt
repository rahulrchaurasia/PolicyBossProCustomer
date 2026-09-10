package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanEvent
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanUiState
import com.policyboss.customer.navigation.Dest

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyPanViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle // 👈 1. Inject SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerifyPanUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<VerifyPanEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        // 👈 2. Extract the type-safe argument directly using Compose Navigation 2.8+ syntax
        val args = savedStateHandle.toRoute<Dest.PrivilegeVerifyPan>()
        val passedPan = args.panNumber

        // 👈 3. Custom Validation: Invalid if the last character is 'R' (case-insensitive)
        val isPanValidCheck = !passedPan.endsWith("R", ignoreCase = true)

        // 👈 4. Initialize the state with the passed data and validation result
        _uiState.update {
            it.copy(
                panNumber = passedPan,
                isPanValid = isPanValidCheck
            )
        }
    }

    fun onAction(action: VerifyPanAction) {
        when (action) {
            is VerifyPanAction.OnFullNameChanged -> {
                _uiState.update { it.copy(fullName = action.name, isFullNameError = false) }
            }
            is VerifyPanAction.OnDobClick -> {
                _uiState.update { it.copy(showDatePicker = true) }
            }
            is VerifyPanAction.OnDismissDatePicker -> {
                _uiState.update { it.copy(showDatePicker = false) }
            }
            is VerifyPanAction.OnDobSelected -> {
                _uiState.update { 
                    it.copy(
                        dob = action.dob, 
                        showDatePicker = false, 
                        isDobError = false 
                    ) 
                }
            }
            VerifyPanAction.OnConfirmClick -> {
                validateAndSubmit()
            }
        }
    }

    private fun validateAndSubmit() {
        val currentState = _uiState.value
        var isValid = true

        // Validate sequentially so multiple errors don't trigger at once if not desired,
        // though updating state together is standard.
        if (currentState.fullName.isBlank()) {
            _uiState.update { it.copy(isFullNameError = true, fullNameErrorMessage = "Name cannot be empty") }
            isValid = false
        }

        if (currentState.dob.isBlank()) {
            _uiState.update { it.copy(isDobError = true, dobErrorMessage = "Date of birth is required") }
            isValid = false
        }

        if (isValid) {
            viewModelScope.launch {
                _uiEvent.send(VerifyPanEvent.NavigateNext)
            }
        } else {
            viewModelScope.launch {
                _uiEvent.send(VerifyPanEvent.ShowError("Please fill in all required details"))
            }
        }
    }
}