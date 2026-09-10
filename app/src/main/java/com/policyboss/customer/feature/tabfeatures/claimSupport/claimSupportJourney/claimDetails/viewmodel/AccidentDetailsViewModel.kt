package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.LookupType

import com.policyboss.customer.utils.AppValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

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
                val currentType = _uiState.value.selectedLookupType

                // 🚀 1. Instantly filter out anything that isn't a letter or number
                val filteredValue = action.value.filter { it.isLetterOrDigit() }

                // 🚀 2. Apply formatting (Uppercase for Vehicle)
                val formattedValue = if (currentType == LookupType.VEHICLE_NUMBER) {
                    filteredValue.uppercase()
                } else {
                    filteredValue
                }

                // 🚀 3. Cap the maximum length so they can't type infinitely
                val cappedValue = if (currentType == LookupType.POLICY_NUMBER) {
                    formattedValue.take(12) // Policy number max length
                } else {
                    formattedValue.take(11) // Vehicle number max length
                }

                _uiState.update { it.copy(lookupValue = cappedValue, lookupError = null) }
            }
            is AccidentDetailsAction.OnDateChanged -> {
                _uiState.update { it.copy(incidentDate = action.date, dateError = null) }
            }
            is AccidentDetailsAction.OnTimeChanged -> {
                _uiState.update { it.copy(incidentTime = action.time, timeError = null) }
            }

            is AccidentDetailsAction.OnDescriptionChanged -> {
                _uiState.update { it.copy(description = action.desc) }
            }

            is AccidentDetailsAction.OnContinueClick -> validateAndSubmit()

            is AccidentDetailsAction.OnLocationChanged -> {
                // When the user manually types in the text field
                _uiState.update {
                    it.copy(
                        location = action.location,
                        latitude = null, // Clear GPS coordinates on manual typing
                        longitude = null,
                        isAutoLocation = false,
                        locationError = null
                    )
                }
            }

            // *******************************************************************************************
            //Step 2: The State Mutation (ViewModel): The ViewModel catches this action.
            // It takes the existing _uiState, copies it, overwrites the location fields with the fresh data from the action payload,
            // and saves this as the new truth.
            // *******************************************************************************************

            is AccidentDetailsAction.OnCurrentLocationFetched -> {

                // 🚀 FIX: When the GPS button succeeds, we save the readable address
                // to show in the Text Field, AND save the Lat/Lng for the server!
                _uiState.update {
                    it.copy(
                        location = action.address, // This instantly appears in the Text Field!
                        latitude = action.lat,
                        longitude = action.lng,
                        isAutoLocation = true, // ⭐ ADD THIS LINE! This triggers the UI changes!
                        locationError = null
                    )
                }
            }
           is  AccidentDetailsAction.OnUseCurrentLocationClick -> {
                // Do nothing in ViewModel.
                // The Route intercepts this before it ever gets here!
            }

            is AccidentDetailsAction.OnClearLocation -> {

                _uiState.update {
                    it.copy(
                        location = "",
                        latitude = null,
                        longitude = null,
                        isAutoLocation = false,
                        locationError = null
                    )
                }
            }


           is AccidentDetailsAction.OnSwitchToManualLocation -> {

                _uiState.update {
                    it.copy(
                        isAutoLocation = false,
                        latitude = null,
                        longitude = null
                    )
                }

            }
        }
    }

    private fun validateAndSubmit() {
        val state = _uiState.value

        // 0. Clear ALL previous errors first (added locationError)
        _uiState.update {
            it.copy(
                lookupError = null,
                dateError = null,
                timeError = null,
                locationError = null // 🚀 Added
            )
        }



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

        // 4. Validate Location (🚀 NEW)
        if (state.location.isBlank()) {
            _uiState.update { it.copy(locationError = "Please enter or fetch the accident location") }
            return // 🛑 Stop here
        }

        // 4. If the code reaches this point, EVERYTHING is valid!
        // ==========================================
        // 🚀 5. SUBMIT TO SERVER
        // ==========================================
         viewModelScope.launch {
            // A. Show the loading indicator
            _uiState.update { it.copy(isLoading = true) }

            try {
                // B. Simulate network call / Do actual API work
                kotlinx.coroutines.delay(2000.milliseconds)

                // C. On Success, navigate to the next screen!
                _uiEvent.emit(AccidentDetailsUiEvent.NavigateNext)

            } catch (e: Exception) {
                // D. On Failure, show a snackbar
                _uiEvent.emit(AccidentDetailsUiEvent.ShowError("Failed to save details. Please try again."))
            } finally {
                // E. Guarantee the loader hides, even if the app crashes
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}