package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
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
            is AccidentDetailsAction.OnLookupTypeChanged -> _uiState.update { it.copy(selectedLookupType = action.type) }
            is AccidentDetailsAction.OnLookupValueChanged -> _uiState.update { it.copy(lookupValue = action.value) }
            is AccidentDetailsAction.OnDateChanged -> _uiState.update { it.copy(incidentDate = action.date) }
            is AccidentDetailsAction.OnTimeChanged -> _uiState.update { it.copy(incidentTime = action.time) }
            is AccidentDetailsAction.OnLocationChanged -> _uiState.update { it.copy(location = action.location) }
            is AccidentDetailsAction.OnDescriptionChanged -> _uiState.update { it.copy(description = action.desc) }
            AccidentDetailsAction.OnUseCurrentLocation -> {
                // Trigger location fetch logic here
                _uiState.update { it.copy(location = "Fetching...") }
            }
            AccidentDetailsAction.OnContinueClick -> {
                // Validate form fields here before proceeding
//                if (_uiState.value.lookupValue.isBlank()) {
//                    viewModelScope.launch { _uiEvent.emit(AccidentDetailsUiEvent.ShowError("Please enter vehicle/policy number")) }
//                    return
//                }
                viewModelScope.launch { _uiEvent.emit(AccidentDetailsUiEvent.NavigateNext) }
            }
        }
    }
}