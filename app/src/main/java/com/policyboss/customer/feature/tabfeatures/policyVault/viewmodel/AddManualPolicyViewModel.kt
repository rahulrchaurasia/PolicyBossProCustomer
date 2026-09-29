package com.policyboss.customer.feature.tabfeatures.policyVault.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState.AddPolicyAction
import com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState.AddPolicyEvent
import com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState.AddPolicyUiState

import com.policyboss.customer.utils.AppValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

// Inject this ViewModel using your DI container (e.g., @HiltViewModel or Koin)
class AddManualPolicyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddPolicyUiState())
    val uiState = _uiState.asStateFlow()

//    private val _uiEvent = MutableSharedFlow<AddPolicyEvent>()
//    val uiEvent = _uiEvent.asSharedFlow()

    // 1. Use a Channel instead of SharedFlow for one-time events
    private val eventChannel = Channel<AddPolicyEvent>()
    val uiEvent = eventChannel.receiveAsFlow()

    fun onAction(action: AddPolicyAction) {
        when (action) {
            is AddPolicyAction.OnCarNumberChanged -> {
                _uiState.update { it.copy(carNumber = action.value) }
            }
            is AddPolicyAction.OnPolicyNumberChanged -> {
                _uiState.update { it.copy(policyNumber = action.value) }
            }
            AddPolicyAction.OnSubmit -> {

                val state = _uiState.value

                // . (Your mock logic goes here) Save to DB or Network
                // e.g., repository.savePolicy(state.carNumber)

                // 2. Run Validation
                var carError: String? = null
                var policyError: String? = null
                var isValid = true

                if (state.carNumber.isNotBlank() && !AppValidator.isValidVehicleNumber(state.carNumber)) {
                    carError = "Enter a valid car number (e.g., MH12AB1234)"
                    isValid = false
                }

                if (state.policyNumber.isNotBlank() && !AppValidator.isValidPolicyNumber(state.policyNumber)) {
                    policyError = "Policy number must be at least 5 characters"
                    isValid = false
                }

                // 3. Apply errors if validation failed
                if (!isValid) {
                    _uiState.update { it.copy(carNumberError = carError, policyNumberError = policyError) }
                    return // Stop execution, don't submit!
                }

                // 4. If valid, proceed with API Call
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true) }

                    kotlinx.coroutines.delay(1500.milliseconds) // Mock API Call

                    _uiState.update { it.copy(isLoading = false) }
                    eventChannel.send(AddPolicyEvent.ShowToast("Policy successfully added!"))
                    eventChannel.send(AddPolicyEvent.NavigateBack)
                }
            }
        }
    }
}