package com.policyboss.customer.utils.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberPermissionHandler(
    onResult: (Boolean) -> Unit,
    onPermanentlyDenied: (List<String>) -> Unit
): (Array<String>) -> Unit {

    val context = LocalContext.current
    val activity = context as AppCompatActivity

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        val permanentlyDenied = mutableListOf<String>()

        val allGranted = permissions.entries.all { entry ->
            val granted = entry.value
            if (!granted && !activity.shouldShowRequestPermissionRationale(entry.key)) {
                permanentlyDenied.add(entry.key)
            }
            granted
        }

        when {
            allGranted -> onResult(true)
            permanentlyDenied.isNotEmpty() -> onPermanentlyDenied(permanentlyDenied)
            else -> onResult(false)
        }
    }

    return { permissions ->
        launcher.launch(permissions)
    }
}