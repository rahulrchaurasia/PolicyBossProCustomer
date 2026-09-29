package com.policyboss.customer.ui.components.bullet


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import com.policyboss.customer.ui.theme.AppColors
@Composable
 fun InfoBullet(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = "•", color = AppColors.WarningYellow, modifier = Modifier.padding(end = 8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium, color = AppColors.WarningYellow)
    }
}