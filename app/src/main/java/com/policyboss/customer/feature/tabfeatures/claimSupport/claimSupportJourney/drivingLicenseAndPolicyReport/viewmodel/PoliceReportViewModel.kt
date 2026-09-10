package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.model.state.policeReport.PoliceReportUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Imports omitted for brevity...

@HiltViewModel
class PoliceReportViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PoliceReportUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PoliceReportUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: PoliceReportAction) {
        when (action) {
            is PoliceReportAction.OnDocumentSelected -> _uiState.update { it.copy(documentUri = action.uri) }
            PoliceReportAction.OnRemoveDocument -> _uiState.update { it.copy(documentUri = null) }
            PoliceReportAction.OnContinueClick -> {
                // No validation needed! Optional field.
                viewModelScope.launch { 
                    _uiEvent.emit(PoliceReportUiEvent.NavigateNext(_uiState.value.documentUri))
                }
            }
        }
    }
}