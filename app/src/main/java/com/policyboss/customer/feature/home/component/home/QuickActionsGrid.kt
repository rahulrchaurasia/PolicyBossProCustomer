package com.policyboss.customer.feature.home.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.ui.unit.dp


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.home.component.home.card.QuickActionCard
import com.policyboss.customer.feature.home.model.QuickAction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

@Composable
fun QuickActionsGrid(
    modifier: Modifier = Modifier,
    actions: List<QuickAction>,
    onClick: (String) -> Unit
) {
    val rows = actions.chunked(2)

    // Note: If you need the ENTIRE grid to move up to overlap a header,
    // pass that offset into this composable's parameters:
    // QuickActionsGrid(modifier = Modifier.offset(y = (-42).dp))

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp) // <-- This handles your row spacing cleanly
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // <-- Handles column spacing
            ) {
                rowItems.forEach { action ->
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        action = action,
                        onClick = { onClick(action.id) }
                    )
                }

                // Fill empty space if the last row has fewer than 2 items
                repeat(2 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun QuickActionsGridPreview() {

    PolicyBossCustomerTheme {

        Column {

            QuickActionsGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.Background)
                    .padding(horizontal = 24.dp),
                actions = AppDummyData.quickActions,
                onClick = {}
            )
        }

    }
}