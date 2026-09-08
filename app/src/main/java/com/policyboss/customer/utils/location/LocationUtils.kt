package com.policyboss.customer.utils.location



import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import java.util.Locale

@SuppressLint("MissingPermission") // We will check permissions before calling this
suspend fun fetchCurrentLocationAndAddress(context: Context): Triple<String, Double, Double>? {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    return try {
        // 1. Get exact current location
        val location = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).await() ?: return null

        val lat = location.latitude
        val lng = location.longitude

        // 2. Convert to human-readable address
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, lng, 1)

        val readableAddress = if (!addresses.isNullOrEmpty()) {
            val address = addresses[0]
            // Combine locality and admin area (e.g., "Andheri West, Maharashtra")
            listOfNotNull(address.subLocality, address.locality, address.adminArea)
                .joinToString(", ")
        } else {
            "$lat, $lng" // Fallback if geocoding fails
        }

        Triple(readableAddress, lat, lng)
    } catch (e: Exception) {
        null
    }
}