package com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.card



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors


@Composable
fun PrivacyNote(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_shield_trust), // Replace with your shield icon
            contentDescription = "Privacy Shield",
            tint = Color.Unspecified // Keeps the original colors of your SVG/Drawable
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "No harm to privacy, no spams, trusted security",
            color = AppColors.TextSecondaryDark, // Slate gray
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}