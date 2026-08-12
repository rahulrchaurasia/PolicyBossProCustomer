package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

// Make sure to import your project's R file for the icon
// import your.package.name.R

@Composable
fun SortByButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Sort by",
            style = MaterialTheme.typography.bodyMediumSemiBold,
            color = AppColors.TextPrimary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_sort_by), // Your sort icon
            contentDescription = "Sort Options",
            tint = AppColors.TextPrimary
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SortByButtonPreview() {
    // Wrapping in MaterialTheme to ensure typography loads correctly
    MaterialTheme {
        SortByButton(
            onClick = {
                // Mock action for the preview
                println("Sort button clicked!")
            }
        )
    }
}