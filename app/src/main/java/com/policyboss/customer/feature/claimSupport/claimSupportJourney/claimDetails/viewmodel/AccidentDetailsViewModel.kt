package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.LookupType
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
class AccidentDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AccidentDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<AccidentDetailsUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: AccidentDetailsAction) {
        when (action) {
            is AccidentDetailsAction.OnLookupTypeChanged -> {

                // ✅ When toggling types, clear the text field and reset errors
                _uiState.update {
                    it.copy(
                        selectedLookupType = action.type,
                        lookupValue = "",
                        lookupError = null
                    )
                }
            }
            is AccidentDetailsAction.OnLookupValueChanged -> {
                // ✅ Force uppercase formatting belongs HERE as they type
                val formattedValue = action.value.uppercase()
                _uiState.update {
                    it.copy(
                        lookupValue = formattedValue,
                        lookupError = null
                    )
                }
                _uiState.update { it.copy(lookupValue = action.value, lookupError = null) }
            }
            is AccidentDetailsAction.OnDateChanged -> {
                _uiState.update { it.copy(incidentDate = action.date, dateError = null) }
            }
            is AccidentDetailsAction.OnTimeChanged -> {
                _uiState.update { it.copy(incidentTime = action.time, timeError = null) }
            }
            is AccidentDetailsAction.OnLocationChanged -> {
                _uiState.update { it.copy(location = action.location) }
            }
            is AccidentDetailsAction.OnDescriptionChanged -> {
                _uiState.update { it.copy(description = action.desc) }
            }
            is AccidentDetailsAction.OnUseCurrentLocation -> {
                // To be implemented in the next step
            }
            is AccidentDetailsAction.OnContinueClick -> validateAndSubmit()

            is AccidentDetailsAction.OnCurrentLocationFetched -> {
                _uiState.update {
                    it.copy(
                        location = action.address,
                        latitude = action.lat,
                        longitude = action.lng,
                        locationError = null
                    )
                }
            }
        }
    }

    private fun validateAndSubmit() {
        val state = _uiState.value

        // 0. Clear all previous errors first
        _uiState.update { it.copy(lookupError = null, dateError = null, timeError = null) }

        // 1. Validate Lookup Value using AppValidator
        if (state.selectedLookupType == LookupType.VEHICLE_NUMBER) {
            // Use the centralized vehicle number validator
            if (!AppValidator.isValidVehicleNumber(state.lookupValue)) {
                _uiState.update { it.copy(lookupError = "Please enter a valid vehicle number") }
                return // 🛑 Stop here
            }
        } else {
            // Use the centralized policy number validator
            if (!AppValidator.isValidPolicyNumber(state.lookupValue)) {
                _uiState.update { it.copy(lookupError = "Please enter a valid policy number") }
                return // 🛑 Stop here
            }
        }

        // 2. Validate Date
        // For simple empty checks on dropdowns/pickers, .isBlank() in the ViewModel is perfectly fine.
        if (state.incidentDate.isBlank()) {
            _uiState.update { it.copy(dateError = "Please select the date of incident") }
            return // 🛑 Stop here
        }

        // 3. Validate Time
        if (state.incidentTime.isBlank()) {
            _uiState.update { it.copy(timeError = "Please select the time of incident") }
            return // 🛑 Stop here
        }

        // 4. If the code reaches this point, EVERYTHING is valid!
        viewModelScope.launch {
            _uiEvent.emit(AccidentDetailsUiEvent.NavigateNext)
        }
    }
}