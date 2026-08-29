package com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.claimListSection.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

@Composable
 fun ClaimDetailRow(label: String, value: String, isValueBold: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = AppColors.TextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            color = AppColors.TextSecondaryDark,
           // fontWeight = if (isValueBold) FontWeight.Bold else FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Claim Detail Row")
@Composable
fun ClaimDetailRowPreview() {
    PolicyBossCustomerTheme {
        Column(modifier = Modifier.padding(16.dp)) {

            // 1. Preview with a BOLD value (e.g., Claim ID)
            ClaimDetailRow(
                label = "Claim",
                value = "#CLM-90234711",
                isValueBold = true
            )

            // 2. Preview with a NORMAL value (e.g., Insurer Name)
            ClaimDetailRow(
                label = "Insurer",
                value = "Tata AIG Insurance",
                isValueBold = false
            )
        }
    }
}