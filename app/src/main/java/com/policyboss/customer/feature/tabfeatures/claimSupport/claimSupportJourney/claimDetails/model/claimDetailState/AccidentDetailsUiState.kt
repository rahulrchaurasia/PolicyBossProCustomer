package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.LookupType

data class AccidentDetailsUiState(

    val selectedLookupType: LookupType = LookupType.VEHICLE_NUMBER,
    val lookupValue: String = "",
    val lookupError: String? = null, // New

    val incidentDate: String = "",
    val dateError: String? = null,   // New

    val incidentTime: String = "",
    val timeError: String? = null,   // New

    val location: String = "",
    val description: String = "",

    val latitude: Double? = null,    // 🚀 Added for the server
    val longitude: Double? = null,   // 🚀 Added for the server
    val locationError: String? = null,

    // 🚀 Tracks if the address was auto-detected via GPS
    val isAutoLocation: Boolean = false,
    val isLoading: Boolean = false // 🚀 ADDED: Tracks server submission
)