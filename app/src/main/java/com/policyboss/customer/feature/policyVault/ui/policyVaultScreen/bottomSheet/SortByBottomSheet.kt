package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.SortOption
import com.policyboss.customer.ui.theme.AppColors

// Ensure you import your R file and the SortOption enum
// import your.package.name.R
// import your.package.name.models.SortOption
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortByBottomSheet(
    currentSelection: SortOption,
    onOptionSelected: (SortOption) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
              //  .padding(bottom = 32.dp) // Extra padding for system navigation bar
            // ✅ REPLACED hardcoded 32.dp with dynamic insets
                .navigationBarsPadding()
                // Optional: Add a little extra visual padding just so the last item
                // doesn't touch the very edge of the navigation area
                .padding(bottom = 16.dp)
        ) {
            SortOption.values().forEach { option ->
                val isSelected = option == currentSelection
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onOptionSelected(option)
                            onDismissRequest()
                        }
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option.displayName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected) Color(0xFF141B26) else Color.Gray,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                    
                    if (isSelected) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check), // Standard checkmark icon
                            contentDescription = "Selected",
                            tint = AppColors.BluePrimary, // Blue tint from the image
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                
                // Add divider between items
                if (option != SortOption.values().last()) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = AppColors.Dividers,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SortByBottomSheetPreview() {
    MaterialTheme {
        SortByBottomSheet(
            // Pass a mock current selection for the preview
            currentSelection = SortOption.UPCOMING_RENEWALS,
            onOptionSelected = { selectedOption ->
                println("Selected: ${selectedOption.displayName}")
            },
            onDismissRequest = {
                println("Bottom sheet dismissed")
            }
        )
    }
}