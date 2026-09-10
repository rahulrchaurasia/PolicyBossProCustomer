package com.policyboss.customer.utils.location

import android.content.Context
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

sealed interface LocationSettingsResult {
    object Satisfied : LocationSettingsResult
    data class Resolvable(val intentSender: IntentSenderRequest) : LocationSettingsResult
    object Unresolvable : LocationSettingsResult
}

suspend fun checkLocationHardwareSettings(
    context: Context
): LocationSettingsResult = suspendCancellableCoroutine { continuation ->

    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, 
        1_000L
    ).build()

    val settingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
        .build()

    LocationServices
        .getSettingsClient(context)
        .checkLocationSettings(settingsRequest)
        .addOnSuccessListener {
            // 🚀 Prevent crash if coroutine was cancelled
            if (continuation.isActive) {
                continuation.resume(LocationSettingsResult.Satisfied)
            }
        }
        .addOnFailureListener { exception ->
            // 🚀 Prevent crash if coroutine was cancelled
            if (!continuation.isActive) return@addOnFailureListener

            if (exception is ResolvableApiException) {
                try {
                    val request = IntentSenderRequest.Builder(exception.resolution).build()
                    continuation.resume(LocationSettingsResult.Resolvable(request))
                } catch (e: Exception) {
                    continuation.resume(LocationSettingsResult.Unresolvable)
                }
            } else {
                continuation.resume(LocationSettingsResult.Unresolvable)
            }
        }
}