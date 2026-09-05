package com.policyboss.customer.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File





/**
 * Creates a safe, persistent URI for the external camera app to write to.
 * Survives app background kills.
 */
fun Context.createTempPictureUri(): Uri {
    val directory = File(
        getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "claim_photos"
    )

    // Ensure the directory exists
    if (!directory.exists()) {
        directory.mkdirs()
    }

    // Create a unique file name to avoid overwriting
    val imageFile = File.createTempFile(
        "CLAIM_${System.currentTimeMillis()}_",
        ".jpg",
        directory
    )

    // Return the secure FileProvider Uri
    return FileProvider.getUriForFile(
        this,
        "${packageName}.fileprovider",
        imageFile
    )
}

/**
 * Deletes all temporary claim photos to free up the user's device storage.
 * Call this after a successful claim submission or when clearing the claim draft.
 */
fun Context.clearTempClaimPhotos() {
    val directory = File(
        getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "claim_photos"
    )

    if (directory.exists()) {
        directory.listFiles()?.forEach { file ->
            file.delete()
        }
    }
}