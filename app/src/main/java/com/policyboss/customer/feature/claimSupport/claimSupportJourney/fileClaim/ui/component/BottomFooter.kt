package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun BottomFooter(onContinueClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.ClaimLightBg)
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Help Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Need help? Connect with your RM",
                style = MaterialTheme.typography.bodyMedium,

                color = AppColors.TextPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Call RM",
                tint = AppColors.TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }

        // Primary Button
        PrimaryCTAButton(
            text = "Continue",
            onClick = onContinueClick,
            containerColor = AppColors.ClaimDarkBg,
            contentColor = AppColors.White,
            arrowBackgroundColor = AppColors.White,
            arrowTint = AppColors.ClaimDarkBg
        )
    }
}