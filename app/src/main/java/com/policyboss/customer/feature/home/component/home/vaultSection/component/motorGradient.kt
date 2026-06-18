package com.policyboss.customer.feature.home.component.home.vaultSection.component

import androidx.compose.foundation.layout.Box
import com.policyboss.customer.feature.home.model.vault.VaultPolicy

import com.policyboss.customer.ui.components.button.PrimaryCTAButton



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.ui.components.card.PolicyDetailsCard


//private val motorCardGradient = Brush.horizontalGradient(
//
//    colors = listOf(
//
//        Color(0xFF58AAFF),
//
//        Color(0xFF3B95FF),
//
//        Color(0xFF2F86F0)
//    )
//)

private val motorCardGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFF7FBCFF), // Lighter blue at the top-left
        Color(0xFF1887FF)  // Deeper blue at the bottom-right
    ),
    // This creates the diagonal angle (Top-Left to Bottom-Right)
    start = Offset(0f, 0f),
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
)

@Composable
fun MotorPolicyCard(
    policy: VaultPolicy,
    modifier: Modifier = Modifier,
    onRenewClick: () -> Unit,
    onViewDetailsClick: () -> Unit
) {
    // 1. Use a Box as the root to allow elements (like the badge) to float over the padding
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(brush = motorCardGradient)
    ) {

        // 2. The Badge goes here, outside the inner padding, pinned to the absolute top-right
        ExpiryBadge(
            text = policy.daysLeft,
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(end = 24.dp)
        )

        // 3. The Column holds the actual content with the 16dp padding
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Padding applies to content, NOT the badge
        ) {

            // Note: We pass the policy down, but the badge logic is removed from here
            PolicyVaultTopContent(
                policy = policy,
                onViewDetailsClick = onViewDetailsClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            PolicyDetailsCard(
                companyLogo = policy.companyLogo,
                idv = policy.idv,
                premium = policy.premium,
                expiry = policy.expiry
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryCTAButton(
                text = "Renew Now",
                onClick = onRenewClick
            )
        }
    }
}

//@Composable
//fun MotorPolicyCard(
//
//    policy: VaultPolicy,
//
//    modifier: Modifier = Modifier,
//
//    onRenewClick: () -> Unit,
//
//    onViewDetailsClick: () -> Unit
//) {
//
//    Column(
//
//        modifier = modifier
//
//            .fillMaxWidth()
//
//            .background(
//
//                brush = motorCardGradient,
//
//                shape = RoundedCornerShape(24.dp)
//            )
//
//            .padding(16.dp)
//    ) {
//
//        PolicyVaultTopContent(
//
//            policy = policy,
//
//            onViewDetailsClick = onViewDetailsClick
//        )
//
//        Spacer(
//
//            modifier = Modifier.height(16.dp)
//        )
//
//        PolicyDetailsCard(
//            companyLogo = policy.companyLogo,
//
//            idv = policy.idv,
//
//            premium = policy.premium,
//
//            expiry = policy.expiry
//        )
//
//        Spacer(
//
//            modifier = Modifier.height(16.dp)
//        )
//
//        PrimaryCTAButton(
//
//            text = "Renew Now",
//
//            onClick = onRenewClick
//        )
//    }
//}


@Preview(showBackground = true)
@Composable
private fun MotorPolicyCardPreview() {

    MotorPolicyCard(

        policy = HomeDummyData.vaultPolicies.first(),

        onRenewClick = {},

        onViewDetailsClick = {}
    )
}