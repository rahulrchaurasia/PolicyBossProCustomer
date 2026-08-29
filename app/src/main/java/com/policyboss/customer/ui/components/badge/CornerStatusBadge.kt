package com.policyboss.customer.ui.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun CornerStatusBadge(
    text: String,
    badgeColor: Color,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
    cardCornerRadius: Int = 16 // Matches the parent card's curve
) {
    Row(
        modifier = modifier
            .background(
                color = badgeColor,
                // This creates the "half curve, half rectangle" shape
                shape = RoundedCornerShape(
                    topStart = 0.dp,                      // Flat top-left
                    topEnd = cardCornerRadius.dp,         // Curves with the card's top-right corner
                    bottomEnd = 0.dp,                     // Flat bottom-right
                    bottomStart = cardCornerRadius.dp     // Curved bottom-left
                )
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // The tiny status dot
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(color = contentColor, shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(6.dp))

        // The text
        Text(
            text = text,
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, name = "Corner Status Badges")
@Composable
fun CornerStatusBadgePreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Preview 1: Under Review (Yellow)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    // Simulating the parent Card background and shape
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                CornerStatusBadge(
                    text = "Under Review",
                    badgeColor = AppColors.PrimaryYellow, // AppColors.PrimaryYellow
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

            // Preview 2: Approved (Green)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                CornerStatusBadge(
                    text = "Approved",
                    badgeColor = AppColors.SuccessGreen, // AppColors.SuccessGreen
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

            // Preview 3: Rejected (Red)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                CornerStatusBadge(
                    text = "Rejected",
                    badgeColor = AppColors.RedBadges, // AppColors.RedBadges
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}