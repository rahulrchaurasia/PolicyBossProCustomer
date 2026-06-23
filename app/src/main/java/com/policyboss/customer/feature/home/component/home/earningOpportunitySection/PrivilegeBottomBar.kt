package com.policyboss.customer.feature.home.component.home.earningOpportunitySection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors


@Composable
fun PrivilegeBottomBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
        .clickable(onClick = onClick),

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

        Icon(
            painter = painterResource(
                id = R.drawable.ic_free
            ),

            contentDescription = "Free",

            tint = Color.Unspecified,
            modifier = Modifier.height(30.dp)
                .width(61.dp)
        )
        


        Icon(

            painter = painterResource(
                id = R.drawable.ic_chevron_right
            ),

            contentDescription = null,

            tint = AppColors.WarningYellow,

            modifier = Modifier.size(24.dp)
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