package com.policyboss.customer.feature.home.component.home.vaultSection.component



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.vault.VaultPolicy

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumSemiBold
import com.policyboss.customer.ui.theme.labelMediumSemiBold


@Composable
fun PolicyVaultTopContent(
    policy: VaultPolicy,
    onViewDetailsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp), // Reduced top padding since the badge is no longer in this composable
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Left Column (Images)
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            // Background Wavy Asset & Triangle
            Image(
                painter = painterResource(R.drawable.ic_security_check),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            // Foreground Vehicle Image
            Image(
                painter = painterResource(policy.vehicleImage), // Assuming this is added to VaultPolicy
                contentDescription = "Vehicle Image",
                contentScale = ContentScale.Fit, // CRITICAL: Prevents the car from stretching
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Right Column (Text)
        Column(
            modifier = Modifier.weight(1f)
        )
        {
            Text(
                text = policy.vehicleName,
                style = MaterialTheme.typography.labelMediumSemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = policy.vehicleNumber,
                style = MaterialTheme.typography.labelMediumSemiBold, // ✅ Perfect semantic use
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            // View Details Row
            Row(
                modifier = Modifier
                    //.clip(RoundedCornerShape(4.dp)) // Keeps the click ripple neat
                    .clickable { onViewDetailsClick() }
                    .padding(vertical = 4.dp), // slightly expand click area
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View details",
                    style = MaterialTheme.typography.labelMediumSemiBold, // Adjusted weight to match screenshot
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right), // Update with your actual chevron icon
                    contentDescription = "View Details",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFF3B95FF
)
@Composable
private fun PolicyVaultTopContentPreview() {



    val previewPolicy = VaultPolicy(
        vehicleName = "Honda Amaze",
        vehicleNumber = "MH 12 AB 3456",
        companyLogo = R.drawable.img_tata,
        vehicleImage = R.drawable.ic_car_protect,
        idv = "₹5L",
        premium = "₹6,403",
        expiry = "21.02.26",
        daysLeft = "Expires in 21 days",
        title = "Your 'Honda Amaze' is now protected! Access your policy by syncing your email",
        id = "1",
        tabId = 1
    )

    PolicyVaultTopContent(

        policy = previewPolicy,

        onViewDetailsClick = {}
    )
}