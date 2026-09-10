package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClaimHeader(
    showDeleteMenu: Boolean,
    onDeleteAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp), // 🚀 THE FIX: Locks the height so it never shrinks!
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Claims",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Only show the trash icon if we have claims to delete
        if (showDeleteMenu) {
            IconButton(onClick = onDeleteAllClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete All Claims",
                    tint = MaterialTheme.colorScheme.primary // Makes it red
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Claim Header - Both States")
@Composable
private fun ClaimHeaderPreview() {
    // Replace MaterialTheme with PolicyBossCustomerTheme if you want exact app colors
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 1. Preview WITH the delete menu (List is not empty)
            ClaimHeader(
                showDeleteMenu = true,
                onDeleteAllClick = {}
            )

            // 2. Preview WITHOUT the delete menu (List is empty)
            ClaimHeader(
                showDeleteMenu = false,
                onDeleteAllClick = {}
            )
        }
    }
}