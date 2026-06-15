package com.policyboss.customer.ui.components


import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.dp



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import com.policyboss.customer.ui.theme.trustBadgeBackground

@Composable
fun TrustBadgesSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()

            .background(MaterialTheme.colorScheme.trustBadgeBackground)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TrustBadgeText("HASSLE FREE")
        Spacer(Modifier.width(8.dp))

        TrustBadgeSeparator()
        Spacer(Modifier.width(8.dp))

        TrustBadgeText("100% SECURE")
        Spacer(Modifier.width(8.dp))

        TrustBadgeSeparator()
        Spacer(Modifier.width(8.dp))

        TrustBadgeText("NO SPAMS")
    }
}