package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.Insurer
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageUiEvent
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state.CashlessGarageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class CashlessGarageViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CashlessGarageUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<CashlessGarageUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    // 1. Hold the list of available insurers
    private val _insurers = MutableStateFlow<List<Insurer>>(emptyList())
    val insurers = _insurers.asStateFlow()

    // 2. Hold the currently selected insurer
    private val _selectedInsurer = MutableStateFlow<Insurer?>(null)
    val selectedInsurer = _selectedInsurer.asStateFlow()

    init {
        loadInsurers()
    }


    private fun loadInsurers() {
        // Fetch from your dummy data or repository here

        val dummyInsurers = listOf(
            Insurer("acko", "Acko General Insurance", R.drawable.ic_acko_logo),
            Insurer("tata", "Tata AIG", R.drawable.img_tata),
            Insurer("hdfc", "HDFC Ergo", R.drawable.ic_smeline)
        )

        // 2. MUST UPDATE THE UI STATE HERE
        _uiState.update { it.copy(insurers = dummyInsurers) }
    }

    fun selectInsurer(insurer: Insurer) {
        _selectedInsurer.value = insurer
    }

    fun onAction(action: CashlessGarageAction) {
        when (action) {
            is CashlessGarageAction.OnVehicleTypeSelected -> {
                _uiState.update { it.copy(selectedVehicle = action.type) }
            }
            is CashlessGarageAction.OnLocationChanged -> {
                _uiState.update {
                    it.copy(
                        location = action.location,
                        isAutoLocation = false, // Typing manually disables auto-lock
                        locationError = null
                    )
                }
            }
            is CashlessGarageAction.OnClearLocation -> {
                _uiState.update {
                    it.copy(
                        location = "",
                        latitude = null,
                        longitude = null,
                        isAutoLocation = false, // Unlock the field for manual typing
                        locationError = null
                    )
                }
            }
            is CashlessGarageAction.OnInsurerSelected -> {
                _uiState.update { it.copy(selectedInsurer = action.insurer, insurerError = null) }

            }
            is CashlessGarageAction.OnCurrentLocationFetched -> {
                _uiState.update {
                    it.copy(
                        location = action.address,
                        latitude = action.lat,
                        longitude = action.lng,
                        isAutoLocation = true,
                        locationError = null
                    )
                }
            }
            CashlessGarageAction.OnUseCurrentLocationClick -> {
                // Intercepted by the Route for permissions
            }
            CashlessGarageAction.OnFindGaragesClick -> validateAndSubmit()
        }
    }

    private fun validateAndSubmit() {
        val state = _uiState.value

        _uiState.update { it.copy(locationError = null, insurerError = null) }

        if (state.location.isBlank()) {
            _uiState.update { it.copy(locationError = "Please select a location") }
            return
        }

        // FIXED BUG: changed != null to == null
        if (state.selectedInsurer == null) {
            _uiState.update { it.copy(insurerError = "Please select an insurer") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Simulate API call
                delay(1000.milliseconds)

                val title =  "PolicyBoss"

                // 2. Define the URL
                // (Note: If your InsurerContactModel has a 'url' property, use clickedInsurer.url here instead)
                val url = "https://www.policyboss.com/"

                // 3. Emit the navigation event

                _uiEvent.emit(CashlessGarageUiEvent.NavigateNext(title = title, url = url))
            } catch (e: Exception) {
                _uiEvent.emit(CashlessGarageUiEvent.ShowError("Failed to fetch garages."))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}