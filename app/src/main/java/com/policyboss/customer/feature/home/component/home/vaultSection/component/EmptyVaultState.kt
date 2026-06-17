package com.policyboss.customer.feature.home.component.home.vaultSection.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.vault.VaultPolicy

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
 fun EmptyVaultState() {

    Box(

        modifier = Modifier

            .fillMaxWidth()

            .padding(vertical = 24.dp),

        contentAlignment = Alignment.Center
    ) {

        Text(

            text = "No policies available"
        )
    }
}