package com.policyboss.customer.feature.home.component.home.assistanceSection


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

@Composable
fun AssistanceSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = "Still having doubts?",
            style = MaterialTheme.typography.labelLarge,

            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = AppColors.SupportCardBackground
            ),

            border = BorderStroke(
                width = 1.dp,
                color = AppColors.BrandBlueLight
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    ),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_headset
                    ),

                    contentDescription = null,

                    tint = AppColors.BluePrimary,

                    modifier = Modifier.size(40.dp)
                )

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Get Assistance",

                        style = MaterialTheme.typography.bodyMediumSemiBold,


                        color = AppColors.TextPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Connect with your RM",

                        style = MaterialTheme.typography.bodyMedium,

                        color = AppColors.TextSecondaryDark
                    )
                }


                Icon(

                    painter = painterResource(
                        id = R.drawable.ic_chevron_right
                    ),

                    contentDescription = null,

                    tint = AppColors.TextPrimary,

                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AssistanceSectionPreview() {

    PolicyBossCustomerTheme {

        AssistanceSection(

            modifier = Modifier.padding(
                horizontal = 24.dp,
                vertical = 24.dp,
            ),

            onClick = {}
        )
    }
}