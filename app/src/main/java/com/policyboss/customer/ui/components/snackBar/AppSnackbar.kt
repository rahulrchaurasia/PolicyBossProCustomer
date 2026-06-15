package com.policyboss.customer.ui.components.snackBar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppSnackbar(
    data: SnackbarData,
    type: SnackbarType
) {

    Snackbar(

        shape = RoundedCornerShape(14.dp),

        containerColor =
            MaterialTheme.colorScheme.secondary,

        contentColor = Color.White
    ) {

        Text(
            text = data.visuals.message
        )
    }
}