package com.policyboss.customer.feature.home.component.home.currentPolicySection

import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R

// Required imports for Preview
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

@Composable
fun PolicyCategoryCard(
    title: String,
    iconRes: Int,
    backgroundColor: Color,
    isComingSoon: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(backgroundColor)
                .clickable(onClick = onClick)
                .padding(
                    top = 28.dp,
                    bottom = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Preserve XML gradients
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMediumSemiBold,

            )
        }

        if (isComingSoon) {
            ComingSoonBadge()
        }
    }
}

// --- Previews Below ---

@Preview(showBackground = true, name = "Policy Category - Default")
@Composable
fun PolicyCategoryCardPreview() {
    MaterialTheme {
        PolicyCategoryCard(
            title = "Health Insurance",
            iconRes = R.drawable.ic_health,  // Replace with your actual R.drawable icon
            backgroundColor = Color(0xFFE3F2FD), // Light Blue for preview
            isComingSoon = false,
            modifier = Modifier.padding(16.dp),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Policy Category - Coming Soon")
@Composable
fun PolicyCategoryCardComingSoonPreview() {
    MaterialTheme {
        PolicyCategoryCard(
            title = "Life Insurance",
            iconRes = R.drawable.ic_life, // Replace with your actual R.drawable icon
            backgroundColor = Color(0xFFFFF3E0), // Light Orange for preview
            isComingSoon = true,
            modifier = Modifier.padding(16.dp),
            onClick = {}
        )
    }
}

// (Dummy implementation of ComingSoonBadge so the preview compiles out of the box if you haven't written it yet)
@Composable
fun ComingSoonBadge(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = AppColors.WhiteBg,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = "Coming soon",
            color = Color(0xFF475467),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}