package com.policyboss.customer.feature.home.component.home.earningOpportunitySection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.AppColors


@Composable
fun PrivilegeBottomBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Join the privilege for ",
            color = AppColors.WarningYellow,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "₹999",
            color = AppColors.WarningYellow,
            textDecoration = TextDecoration.LineThrough, // Strikethrough style
            fontWeight = FontWeight.Medium
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // FREE Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color.White)
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = "FREE",
                color = AppColors.DarkBackground,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp
            )
        }
        
        Spacer(modifier = Modifier.width(4.dp))

        Icon(
            // 1. Change 'Default' or 'Filled' to 'AutoMirrored.Filled'
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Join",
            tint = AppColors.WarningYellow
        )
    }
}
@Preview(
    showBackground = true,
    backgroundColor = 0xFF1A1A1A
)
@Composable
fun PrivilegeBottomBarPreview() {
    PrivilegeBottomBar(
        modifier = Modifier
            .background(Color(0xFF1A1A1A))
            .padding(16.dp),
        onClick = {}
    )
}