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
    // 👇 FIX: Changed from AnyDate to Any to match the sealed class exactly
    dateConstraint: DateConstraint = DateConstraint.Any
) {
    val datePickerState = rememberDatePickerState(
        selectableDates = dateConstraint.toSelectableDates()
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { millis ->
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val dateStr = formatter.format(Date(millis))
                    onDateSelected(dateStr)
                } ?: onDismiss()
            }) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
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