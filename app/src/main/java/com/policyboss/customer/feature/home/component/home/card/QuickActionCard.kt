package com.policyboss.customer.feature.home.component.home.card


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Surface


import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.captionSmall

@Composable
fun QuickActionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageRes: Int,
    isPro: Boolean = false,
    isNew: Boolean = false,
    onClick: () -> Unit
) {
    // Determine card styling based on isPro
    val cardBackground = if (isPro) Color.White else Color(0xFFF9F9F9) // Adjust to your AppColors
    val cardBorder = if (isPro) BorderStroke(1.5.dp, Color(0xFFFFD54F)) else null // WarningYellow

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardBackground)
            .then(if (cardBorder != null) Modifier.border(cardBorder, RoundedCornerShape(20.dp)) else Modifier)
            .clickable { onClick() }
    ) {
        // --- MAIN CONTENT ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Replaced Placeholder with actual Image from ViewModel
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(58.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // Pushes text to the bottom

            // 1. Update Title to use your new custom property
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.captionSmall.copy(
                    color = Color.Gray // Or whatever color Figma specifies for this
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold // Overrides the Normal weight to match Figma 600
                )
            )
        }

        // --- TOP RIGHT BADGES ---
        if (isPro) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                    .background(Color(0xFFFFB300)) // WarningYellow
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White, // Adjust to your gold/inner color
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Pro mode",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF5D4037) // WarningText
                    )
                )
            }
        } else if (isNew) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                    .background(Color(0xFFE0F2F1)) // Light Mint Green
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "New policy",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF00796B) // Dark Teal
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 400)
@Composable
fun QuickActionCardPreview() {
    // Wrap in your app's theme so the Geist font applies in the preview!
    MaterialTheme(
        // typography = AppTypography // Uncomment if your theme is set up this way
    ) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 1. The Pro Mode Card
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Renew & Earn",
                    subtitle = "Become a 'Privileged user'",
                    imageRes = R.drawable.ic_money, // Using your existing drawable
                    isPro = true,
                    isNew = false,
                    onClick = {}
                )

                // 2. The New Policy Card
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Policy Vault",
                    subtitle = "All your policies in one place",
                    imageRes = R.drawable.ic_dashboard2, // Using your existing drawable
                    isPro = false,
                    isNew = true,
                    onClick = {}
                )
            }
        }
    }
}