package com.policyboss.customer.feature.home.component.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.unit.dp


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.component.home.card.QuickActionCard

@Composable
fun QuickActionsGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Renew & Earn",
                subtitle = "Become a 'Privileged user'",
                isPro = true,
                onClick = {},
                        imageRes = R.drawable.ic_money,

                isNew = false
            )
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Policy Vault",
                subtitle = "All your policies in one place",
                onClick = {},
                imageRes = R.drawable.ic_money,
                isPro = true,
                isNew = false
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Claim Support",
                subtitle = "Guided claim filing & assistance",
                onClick = {},
                imageRes = R.drawable.ic_money,
                isPro = false,
                isNew = true
            )
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "BOSSPedia",
                subtitle = "Daily insights about insurance",
                imageRes = R.drawable.ic_money,
                isPro = false,
                isNew = false,
                onClick = {}
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun QuickActionPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            QuickActionsGrid()
        }
    }
}
