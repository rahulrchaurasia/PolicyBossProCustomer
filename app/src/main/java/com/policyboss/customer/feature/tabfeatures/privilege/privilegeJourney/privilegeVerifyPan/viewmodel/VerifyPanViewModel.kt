package com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.policyboss.customer.core.datastore.AppDataManager
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanEvent
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeJourney.privilegeVerifyPan.state.VerifyPanUiState
import com.policyboss.customer.navigation.Dest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class VerifyPanViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle , // 👈 1. Inject SavedStateHandle
    private val appDataManager: AppDataManager // 👈 1. Inject AppDataManager
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

        // 👈 2. Pre-fill the Full Name from DataStore
        viewModelScope.launch {
            // Use .first() to read the value exactly once when the screen opens.
            // This prevents overwriting the user's input if they decide to edit it.
            val storedName = appDataManager.userName.first()

            // Check against your default "Guest User" fallback or empty strings
            if (storedName != "Guest User" && storedName.isNotBlank()) {
                _uiState.update { it.copy(fullName = storedName) }
            }
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
           is VerifyPanAction.OnConfirmClick -> {
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
            return
        }

        if (currentState.dob.isBlank()) {
            _uiState.update { it.copy(isDobError = true, dobErrorMessage = "Date of birth is required") }
            return
        }

        // Clear PAN error if fixed
        _uiState.update { it.copy(isFullNameError = false, fullNameErrorMessage = null) }
        _uiState.update { it.copy(isDobError = false, dobErrorMessage = null) }

        viewModelScope.launch {

            // 1. Show the loader
            // 1. Tell UI to show a loading state
            _uiEvent.send(VerifyPanEvent.Loading(true))

            _uiState.update { it.copy(isLoading = true) }

            // 2. Simulate API Call / Validation delay (e.g., 1.5 seconds)
            delay(1500.milliseconds)

            // Optionally save the verified name back to DataStore
            // appDataManager.saveUserName(currentState.fullName)

            // 3. Hide the loader
           // _uiState.update { it.copy(isLoading = false) }
            _uiEvent.send(VerifyPanEvent.Loading(false))


            // 4. Navigate Next
            _uiEvent.send(VerifyPanEvent.NavigateNext)
        }
    }
}