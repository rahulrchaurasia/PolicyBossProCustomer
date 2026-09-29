package com.policyboss.customer.utils.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

// ==========================================
// ⭐ REUSABLE PERMISSION CHECKERS
// ==========================================

/**
 * Highly reusable generic checker for ANY single permission (e.g., Camera, Notifications).
 * Example usage: hasPermission(context, Manifest.permission.CAMERA)
 */
fun hasPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

/**
 * Specific helper for Location because Android allows either Fine OR Coarse.
 */
fun hasLocationPermission(context: Context): Boolean {
    val fineLocation = hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
    val coarseLocation = hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

    return fineLocation || coarseLocation
}