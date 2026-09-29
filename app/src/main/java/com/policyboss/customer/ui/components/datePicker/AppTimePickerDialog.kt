package com.policyboss.customer.ui.components.datePicker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTimePickerDialog(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = java.util.Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(java.util.Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(java.util.Calendar.MINUTE),
        is24Hour = false
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val cal = java.util.Calendar.getInstance().apply {
                    set(java.util.Calendar.HOUR_OF_DAY, timePickerState.hour)
                    set(java.util.Calendar.MINUTE, timePickerState.minute)
                }
                val formatter = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
                onTimeSelected(formatter.format(cal.time))
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        text = {
            TimePicker(state = timePickerState)
        }
    )
}

@Preview(name = "Time Picker Dialog", showBackground = true)
@Composable
private fun AppTimePickerDialogPreview() {
    PolicyBossCustomerTheme {
        AppTimePickerDialog(
            onTimeSelected = { /* Do nothing in preview */ },
            onDismiss = { /* Do nothing in preview */ }
        )
    }
}