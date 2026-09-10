package com.policyboss.customer.utils.location

sealed interface LocationResult {
    data class Success(
        val address: String,
        val lat: Double,
        val lng: Double
    ) : LocationResult

    data object NoFixAvailable : LocationResult
    data object PermissionDenied : LocationResult
    data object Unknown : LocationResult
}
