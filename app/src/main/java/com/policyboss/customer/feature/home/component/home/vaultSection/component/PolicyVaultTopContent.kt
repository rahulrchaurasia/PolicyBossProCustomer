package com.policyboss.customer.feature.home.component.home.vaultSection.component



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.vault.VaultPolicy

import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PolicyVaultTopContent(

    policy: VaultPolicy,

    onViewDetailsClick: () -> Unit
) {

    Box {

        PolicyHeaderImage(
            carImage = policy.vehicleImage
        )

        ExpiryBadge(

            text = policy.daysLeft,

            modifier = Modifier.align(
                Alignment.TopEnd
            )
        )

        Column(

            modifier = Modifier

                .align(
                    Alignment.CenterEnd
                )
        ) {

            Text(

                text = policy.vehicleName,

                color = Color.White
            )

            Text(

                text = policy.vehicleNumber,

                color = Color.White
            )

            Row(

                modifier = Modifier.clickable {

                    onViewDetailsClick()
                }
            ) {

                Text(

                    text = "View details",

                    color = Color.White
                )

                Icon(

                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,

                    contentDescription = null,

                    tint = Color.White
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