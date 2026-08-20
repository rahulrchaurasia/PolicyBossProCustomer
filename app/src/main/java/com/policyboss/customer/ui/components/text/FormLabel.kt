package com.policyboss.customer.ui.components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors

// Don't forget to import your specific AppColors!
// import com.policyboss.customer.ui.theme.AppColors
@Composable
fun FormLabel(text: String) {
    Text(
        text = text,

        style = MaterialTheme.typography.headlineSmall,
        color = AppColors.TextPrimary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}