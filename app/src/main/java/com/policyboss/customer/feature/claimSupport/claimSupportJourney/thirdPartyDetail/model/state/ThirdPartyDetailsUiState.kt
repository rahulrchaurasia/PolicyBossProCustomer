package com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state

data class ThirdPartyDetailsUiState(
    val driverName: String = "",
    val nameError: String? = null, // Added

    val phoneNumber: String = "",
    val phoneError: String? = null // Added
)