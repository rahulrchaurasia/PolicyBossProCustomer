package com.policyboss.customer.ui.components.datePicker



import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    dateConstraint: DateConstraint = DateConstraint.Any
) {
    // 1. Initialize state with our constraints and initial month
    val datePickerState = rememberDatePickerState(
        selectableDates = dateConstraint.toSelectableDates(),
        initialDisplayedMonthMillis = dateConstraint.getInitialDisplayedMonthMillis()
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                // 👇 UX IMPROVEMENT: Button is disabled if no date is picked
                enabled = datePickerState.selectedDateMillis != null,
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val dateStr = formatter.format(Date(millis))
                        onDateSelected(dateStr)
                    }
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(name = "Date Picker Dialog", showBackground = true)
@Composable
private fun AppDatePickerDialogPreview() {
    PolicyBossCustomerTheme {
        AppDatePickerDialog(
            onDateSelected = { /* Do nothing in preview */ },
            onDismiss = { /* Do nothing in preview */ }
        )
    }
}