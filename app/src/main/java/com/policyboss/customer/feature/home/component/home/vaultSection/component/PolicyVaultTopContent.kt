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
import com.policyboss.customer.feature.home.model.vault.VaultPolicy


@Composable
fun PolicyVaultTopContent(

    policy: VaultPolicy,

    onViewDetailsClick: () -> Unit
) {

    Box {

        PolicyHeaderImage(
            carImage = policy.carImage
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