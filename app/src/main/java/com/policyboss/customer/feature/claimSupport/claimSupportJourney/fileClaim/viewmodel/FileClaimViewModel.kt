package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimAction
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimUiEvent
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState.FileClaimUiState


import com.policyboss.customer.feature.dummyData.AppDummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileClaimViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FileClaimUiState())
    val uiState: StateFlow<FileClaimUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FileClaimUiEvent>()
    val uiEvent: SharedFlow<FileClaimUiEvent> = _uiEvent.asSharedFlow()

    fun onAction(action: FileClaimAction) {
        when (action) {
            is FileClaimAction.LoadRequirements -> {
                // Fetch the list from the centralized data source
                val reqs = AppDummyData.getClaimRequirements(action.productType)

                _uiState.update {
                    it.copy(
                        productType = action.productType,
                        requirements = reqs
                    )
                }
            }
//            FileClaimAction.OnBackClick -> {
//                viewModelScope.launch { _uiEvent.emit(FileClaimUiEvent.NavigateBack) }
//            }
//            FileClaimAction.OnCloseClick -> {
//                viewModelScope.launch { _uiEvent.emit(FileClaimUiEvent.NavigateClose) }
//            }
            FileClaimAction.OnContinueClick -> {
                // Perform any screen-specific validation here before proceeding
                viewModelScope.launch { _uiEvent.emit(FileClaimUiEvent.NavigateToNextStep) }
            }
        }
    }
}