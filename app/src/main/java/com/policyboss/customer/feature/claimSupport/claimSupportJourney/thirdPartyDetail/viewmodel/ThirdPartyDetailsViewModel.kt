package com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdPartyDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ThirdPartyDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ThirdPartyUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: ThirdPartyDetailsAction) {
        when (action) {
            is ThirdPartyDetailsAction.OnDriverNameChanged -> {
                _uiState.update { it.copy(driverName = action.name) }
            }
            is ThirdPartyDetailsAction.OnPhoneNumberChanged -> {
                // Example: Only allow digits
                val digitsOnly = action.number.filter { it.isDigit() }
                _uiState.update { it.copy(phoneNumber = digitsOnly) }
            }
            ThirdPartyDetailsAction.OnContinueClick -> {
                viewModelScope.launch {
                    // Trigger navigation event to the Route
                    _uiEvent.emit(ThirdPartyUiEvent.NavigateNext)
                }
            }
        }
    }
}

