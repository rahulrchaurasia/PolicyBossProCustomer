package com.policyboss.customer.utils.permission

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun PermissionSettingsDialog(
    title: String = "Permission Denied",
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title, color = AppColors.TextPrimary) },
        text = { Text(text = message, color = AppColors.TextSecondary) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Open Settings", color = AppColors.BrandBlue)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = AppColors.TextSecondary)
            }
        },
        containerColor = AppColors.White
    )
}