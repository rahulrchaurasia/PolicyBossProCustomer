package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.state

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.Insurer
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.VehicleType

data class CashlessGarageUiState(
    val selectedVehicle: VehicleType = VehicleType.CAR,
    val location: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isAutoLocation: Boolean = false,
    val locationError: String? = null,

    // Insurer data grouped here
    val insurers: List<Insurer> = emptyList(), // Added this
    val selectedInsurer: Insurer? = null,
    val insurerError: String? = null,

    val isLoading: Boolean = false
)