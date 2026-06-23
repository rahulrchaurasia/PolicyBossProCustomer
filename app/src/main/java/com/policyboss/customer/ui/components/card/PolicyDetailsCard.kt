package com.policyboss.customer.ui.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.policyboss.customer.R


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

@Composable
fun PolicyDetailsCard(
    @DrawableRes companyLogo: Int,
    idv: String,
    premium: String,
    expiry: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 12.dp), // Slightly reduced padding to maximize space
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 1. Compact Logo Section
        LogoSection(companyLogo = companyLogo)

        Spacer(modifier = Modifier.width(12.dp))

        // 2. Data Row (Takes up all remaining space)
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DataColumn(
                title = "IDV",
                value = idv,
                modifier = Modifier.weight(0.7f)
            )

            VerticalDivider(
                modifier = Modifier
                    .height(28.dp)
                    .padding(horizontal = 8.dp),
                color = AppColors.BorderSecondary // Updated
            )

            DataColumn(
                title = "Premium",
                value = premium,
                modifier = Modifier.weight(1f)
            )

            VerticalDivider(
                modifier = Modifier
                    .height(28.dp)
                    .padding(horizontal = 8.dp),
                color = AppColors.BorderSecondary // Updated
            )

            DataColumn(
                title = "Expiry",
                value = expiry,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LogoSection(
    @DrawableRes companyLogo: Int
) {
    Box(
        modifier = Modifier
            .width(63.dp) // Reduced from 100.dp to save space
            .height(44.dp) // Reduced from 58.dp
            .clip(RoundedCornerShape(8.dp))
        .background(AppColors.Background),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = companyLogo,
            contentDescription = "Company Logo",
            modifier = Modifier
                .fillMaxWidth(0.8f) // Keeps the image padded inside the gray box
                .height(32.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun DataColumn(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start // Left-aligned to match design
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMediumSemiBold, // Using your custom typography
            color = Color(0xFF111827),
            maxLines = 2, // Allows long values to wrap safely without breaking the row
            overflow = TextOverflow.Visible // Ensures no ellipses are used
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall, // Using Material 3 standard
            color = Color(0xFF6B7280),
            maxLines = 1,
            overflow = TextOverflow.Visible
        )
    }
}

// ==========================================
// PREVIEW
// ==========================================
@Preview(showBackground = true, backgroundColor = 0xFFF3F4F6)
@Composable
fun PolicyDetailsCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Standard Case
            PolicyDetailsCard(
                companyLogo = android.R.drawable.ic_dialog_info, // Placeholder for Tata AIG
                idv = "₹5L",
                premium = "₹6,403",
                expiry = "21.02.26"
            )

            // Edge Case: Extremely Long Data (Demonstrates wrapping without breaking)
            PolicyDetailsCard(
                companyLogo = android.R.drawable.ic_dialog_info,
                idv = "₹10.5L+",
                premium = "₹12,403.50",
                expiry = "21.02.2026"
            )
        }
    }
}

// ==========================================
// LOGO PREVIEW
// ==========================================
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LogoSectionPreview() {
    MaterialTheme {
        // Wrapped in a padded box just so it's easier to see the rounded corners
        // against the white preview background
        Box(modifier = Modifier.padding(24.dp)) {
            LogoSection(
                // Using a default Android icon just to see the sizing
                // replace with R.drawable.img_tata to see your actual logo
                companyLogo = android.R.drawable.ic_dialog_info
            )
        }
    }
}
