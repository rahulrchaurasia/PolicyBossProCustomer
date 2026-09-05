package com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.drivingLicense.DriversLicenseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriversLicenseViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DriversLicenseUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<DriversLicenseUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: DriversLicenseAction) {
        when (action) {
            is DriversLicenseAction.OnDocumentSelected -> {
                // 🚀 1. CLEAR THE ERROR the moment the user selects a photo
                _uiState.update { it.copy(documentUri = action.uri, errorMessage = null) }
            }
            DriversLicenseAction.OnRemoveDocument -> _uiState.update { it.copy(documentUri = null) }
            DriversLicenseAction.OnSubmitClick -> {
                val uri = _uiState.value.documentUri

                if (uri == null) {
                    viewModelScope.launch {
                      //  _uiEvent.emit(DriversLicenseUiEvent.ShowError("Please upload your Driver's license"))
                        // 🚀 2. SHOW INLINE ERROR instead of emitting a Toast
                        _uiState.update { it.copy(errorMessage = "Please upload your Driver's License") }

                    }
                }
                else {
                    viewModelScope.launch {
                        _uiEvent.emit(DriversLicenseUiEvent.SubmitClaim(uri))
                    }
                }
            }
        }
    }
}