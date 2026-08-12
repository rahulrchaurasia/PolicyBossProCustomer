package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyVaultPolicy
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.badgeUi
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.tab.PolicyVaultTopContent
import com.policyboss.customer.ui.components.badge.PolicyStatusBadge
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.card.PolicyDetailsCard
import com.policyboss.customer.ui.theme.gradients.AppGradients


@Composable
fun PolicyVaultCard(
    policy: PolicyVaultPolicy,
    modifier: Modifier = Modifier,
    onRenewClick: () -> Unit,
    onViewDetailsClick: () -> Unit
) {

    val badge = policy.badgeUi()
    // 1. Use a Box as the root to allow elements (like the badge) to float over the padding
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(brush =  AppGradients.PolicyCardGradient)
    ) {

        // 2. The Badge goes here, outside the inner padding, pinned to the absolute top-right


        PolicyStatusBadge(

            text = badge.text,

            backgroundColor = badge.backgroundColor,

            textColor = badge.textColor,

            modifier = Modifier
                .align(Alignment.TopEnd)
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
