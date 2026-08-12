package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyItem
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.card.PolicyOptionCard

// Define the columns constant just like the Home Screen


private const val SHEET_COLUMNS = 3




@Composable
fun AddPolicyBottomSheetContent(
    options: List<AddPolicyItem>, // Using the data class we made earlier
    onOptionSelected: (AddPolicyType) -> Unit
) {
    val horizontalSpacing = 12.dp
    val verticalSpacing = 16.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)

    ) {
        // Reusing your chunked logic!
        options.chunked(SHEET_COLUMNS).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)
            ) {
                // 1. Draw the actual cards
                rowOptions.forEach { option ->
                    PolicyOptionCard(
                        modifier = Modifier.weight(1f),
                        title = option.title,
                        iconRes = option.iconRes,
                        backgroundColor = option.backgroundColor,
                        iconTint = option.iconTint,
                        onClick = { onOptionSelected(option.type) }
                    )
                }

                // 2. The Fix: Fill empty spots so items don't stretch
                val emptySpots = SHEET_COLUMNS - rowOptions.size
                repeat(emptySpots) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AddPolicyBottomSheetContentPreview() {
    // Mock data specifically for visualizing the grid layout
    val previewOptions = listOf(
        AddPolicyItem(AddPolicyType.CAR, "Car", R.drawable.ic_car, Color(0xFFF0F5FF), Color(0xFF3B82F6)),
        AddPolicyItem(AddPolicyType.BIKE, "Bike", R.drawable.ic_bike, Color(0xFFF0F5FF), Color(0xFF3B82F6)),
        AddPolicyItem(AddPolicyType.CV, "CV", R.drawable.ic_cv, Color(0xFFF0F5FF), Color(0xFF3B82F6)),
        AddPolicyItem(AddPolicyType.HEALTH, "Health", R.drawable.ic_health, Color(0xFFF0FDF4), Color(0xFF22C55E)),
        AddPolicyItem(AddPolicyType.LIFE, "Life", R.drawable.ic_life, Color(0xFFFEF2F2), Color(0xFFEF4444)),
        AddPolicyItem(AddPolicyType.TRAVEL, "Travel", R.drawable.ic_travel, Color(0xFFF5F3FF), Color(0xFF8B5CF6)),
        AddPolicyItem(AddPolicyType.SMELINE, "SMELINE", R.drawable.ic_smeline, Color(0xFFFFFBEB), Color(0xFFF59E0B))
    )

    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            AddPolicyBottomSheetContent(
                options = previewOptions,
                onOptionSelected = { /* Preview Action */ }
            )
        }
    }
}