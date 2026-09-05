package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.LookupType

data class AccidentDetailsUiState(

    val selectedLookupType: LookupType = LookupType.VEHICLE_NUMBER,
    val lookupValue: String = "",
    val lookupError: String? = null, // New

    val incidentDate: String = "",
    val dateError: String? = null,   // New

    val incidentTime: String = "",
    val timeError: String? = null,   // New

    val location: String = "",
    val description: String = ""
)