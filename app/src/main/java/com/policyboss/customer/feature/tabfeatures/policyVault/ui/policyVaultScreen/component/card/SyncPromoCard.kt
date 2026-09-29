package com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumBold
import com.policyboss.customer.ui.theme.bodyMediumNormal
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
fun SyncPromoCard(
    modifier: Modifier = Modifier,
    onSyncClick: () -> Unit
) {


    val textColor = AppColors.GoldText // The brownish/bronze color
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppColors.HighlightCardBackground, shape = RoundedCornerShape(24.dp))
            .border(width = 1.dp, color = AppColors.HighlightCardBorder, shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        // Header & Button Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fetch your policies\nfrom your email.",
                color = textColor,
                style = MaterialTheme.typography.bodyMediumBold,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.width(8.dp))

            // Dark Sync Button
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(AppColors.BrandDark) // Dark Navy/Black
                    .clickable { onSyncClick() }
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sync mail", 
                    color = Color.White,
                    style = MaterialTheme.typography.labelMediumSemiBold,

                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_sync2), // Replace with your sync icon
                    contentDescription = "Sync", 
                    tint = AppColors.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        
        HorizontalDivider(color = AppColors.HighlightCardDividerLight, thickness = 1.dp)
        
        Spacer(modifier = Modifier.height(20.dp))

        // Bullet Points
        BulletPointText(text = "Over 50,000 mails synced with us", textColor = textColor)
        Spacer(modifier = Modifier.height(12.dp))
        BulletPointText(text = "One-view access to all your policies", textColor = textColor)
        Spacer(modifier = Modifier.height(12.dp))
        BulletPointText(text = "Simplified renewals, ensuring you never\nmiss a deadline.", textColor = textColor)
    }
}

@Composable
fun BulletPointText(
    text: String,
    textColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        Box(
            modifier = Modifier
                .padding(top = 7.dp, end = 10.dp)
                .size(6.dp)
                .clip(CircleShape)
                .background(textColor)
        )

        Text(
            text = text,
            modifier = Modifier.weight(1f),
            color = textColor,
            style = MaterialTheme.typography.bodyMediumNormal
        )
    }
}



@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SyncPromoCardPreview() {
    // Wrapping in MaterialTheme ensures standard typography is applied
    MaterialTheme {
        SyncPromoCard(
            modifier = Modifier.padding(16.dp), // Adds padding so it doesn't touch the preview edges
            onSyncClick = {
                // Mock action for preview
                println("Sync mail clicked!")
            }
        )
    }
}