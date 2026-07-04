package com.policyboss.customer.feature.claimSupport.viewmodel

import androidx.lifecycle.ViewModel
import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportUiEvent
import com.policyboss.customer.feature.claimSupport.model.claimSupportState.ClaimSupportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


//Mark : HomeViewModel decides which experience to show.
@HiltViewModel
class ClaimSupportViewModel @Inject constructor() : ViewModel() {


    //region Declaration
    // Private mutable state
    private val _uiState = MutableStateFlow(ClaimSupportUiState())
    // Public immutable state for Compose to observe
    val uiState: StateFlow<ClaimSupportUiState> = _uiState.asStateFlow()

    // The Channel for one-time navigation/snackbars
    private val _uiEvent = Channel<ClaimSupportUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //endregion



}