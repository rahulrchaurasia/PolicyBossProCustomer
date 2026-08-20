package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.LookupType

data class AccidentDetailsUiState(
    val selectedLookupType: LookupType = LookupType.VEHICLE_NUMBER,
    val lookupValue: String = "",
    val incidentDate: String = "",
    val incidentTime: String = "",
    val location: String = "",
    val description: String = ""
)