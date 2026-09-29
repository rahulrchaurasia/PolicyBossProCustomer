package com.policyboss.customer.utils.location



import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds


@SuppressLint("MissingPermission")
suspend fun fetchCurrentLocationAndAddress(
    context: Context
): LocationResult {

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // ==========================================
    // STEP 1: FETCH WITH HARDWARE WARM-UP RETRY[cite: 3]
    // ==========================================
    // Attempt twice: handles cold starts when GPS was just turned on

    var location: android.location.Location? = null

    for (attempt in 1..2) {
        location = try {
            val tokenSource = CancellationTokenSource()
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                tokenSource.token
            ).await() ?: fusedLocationClient.lastLocation.await()
        } catch (e: SecurityException) {
            return LocationResult.PermissionDenied //[cite: 3]
        } catch (e: Exception) {
            null
        }

        if (location != null) break

        // Give GPS hardware 1.2 seconds to warm up after enablement
        if (attempt == 1) {
            delay(1200.milliseconds)
        }
    }

    if (location == null) {
        return LocationResult.NoFixAvailable
    }

    val lat = location.latitude //[cite: 3]
    val lng = location.longitude //[cite: 3]


    // ==========================================
    // STEP 2: CONVERT TO READABLE ADDRESS[cite: 3]
    // ==========================================
    val readableAddress = try {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, lng, 1)

        if (!addresses.isNullOrEmpty()) {
            val address = addresses[0]

            // 🚀 The Magic Method: getAddressLine(0) returns the fully formatted
            // street address (e.g., "Shop 14, MG Road, Ghatkopar West, Mumbai, 400086, Maharashtra")
            address.getAddressLine(0) ?: run {
                // Safe fallback just in case getAddressLine is null
                listOfNotNull(
                    address.thoroughfare,
                    address.subLocality,
                    address.locality,
                    address.adminArea,
                    address.postalCode
                )
                    .filter { it.isNotBlank() }
                    .joinToString(", ")
            }.ifBlank { "$lat, $lng" }

        } else {
            "$lat, $lng"
        }
    } catch (e: Exception) {
        "$lat, $lng"
    }

    return LocationResult.Success(
        address = readableAddress,
        lat = lat,
        lng = lng
    )
}