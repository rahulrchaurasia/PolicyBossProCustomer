package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
fun NeedHelpFooter(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Removed the Surface container so it blends seamlessly with the background gradient
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Combined into a single Text component
        Text(
            text = "Need help? Connect for Support",
            style = MaterialTheme.typography.labelMediumSemiBold,
            color = AppColors.TextPrimary
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        // Tinted to match the text color instead of primary
        Icon(
            imageVector = Icons.Rounded.Call,
            contentDescription = "Call",
            tint = AppColors.TextPrimary
        )
    }
}

// Added Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun NeedHelpFooterPreview() {
    PolicyBossCustomerTheme {
        NeedHelpFooter(
            onClick = {},
            // Adding fillMaxWidth in preview to accurately show centering
            modifier = Modifier.fillMaxWidth()
        )
    }
}