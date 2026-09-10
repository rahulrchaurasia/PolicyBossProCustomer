package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyUiEvent

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
                _uiState.update { it.copy(driverName = action.name, nameError = null) }
            }
            is ThirdPartyDetailsAction.OnPhoneNumberChanged -> {
                // Only allow digits and restrict to 10 characters max
                val digitsOnly = action.number.filter { it.isDigit() }.take(10)
                _uiState.update { it.copy(phoneNumber = digitsOnly, phoneError = null) }
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

