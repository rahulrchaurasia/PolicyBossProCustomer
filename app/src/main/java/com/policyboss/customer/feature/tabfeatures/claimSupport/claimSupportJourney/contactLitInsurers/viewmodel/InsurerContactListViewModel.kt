package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state.InsurerContactListAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state.InsurerContactListEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.contactLitInsurers.state.InsurerContactListUiState


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsurerContactListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(InsurerContactListUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<InsurerContactListEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    // Keep the master list in memory to filter against
    private val allInsurers = AppDummyData.insurerContactList

    init {
        // Load initial data
        _uiState.update { it.copy(insurers = allInsurers) }
    }

    fun onAction(action: InsurerContactListAction) {
        when (action) {
            is InsurerContactListAction.OnSearchQueryChanged -> {
                val query = action.query
                val filteredList = if (query.isBlank()) {
                    allInsurers
                } else {
                    allInsurers.filter { it.name.contains(query, ignoreCase = true) }
                }
                _uiState.update { it.copy(searchQuery = query, insurers = filteredList) }
            }
            
            InsurerContactListAction.OnToggleViewMode -> {
                _uiState.update { it.copy(isGridView = !it.isGridView) }
            }
            
            is InsurerContactListAction.OnInsurerClicked -> {
                viewModelScope.launch {
                    _uiEvent.emit(InsurerContactListEvent.ShowToast("Clicked Insurer: ${action.insurerId}"))
                }
            }
        }
    }
}