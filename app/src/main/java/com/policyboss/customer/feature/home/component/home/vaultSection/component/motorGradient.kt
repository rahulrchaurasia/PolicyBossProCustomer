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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.ui.components.card.PolicyDetailsCard


private val motorCardGradient = Brush.horizontalGradient(

    colors = listOf(

        Color(0xFF58AAFF),

        Color(0xFF3B95FF),

        Color(0xFF2F86F0)
    )
)

@Composable
fun MotorPolicyCard(

    policy: VaultPolicy,

    modifier: Modifier = Modifier,

    onRenewClick: () -> Unit,

    onViewDetailsClick: () -> Unit
) {

    Column(

        modifier = modifier

            .fillMaxWidth()

            .background(

                brush = motorCardGradient,

                shape = RoundedCornerShape(24.dp)
            )

            .padding(16.dp)
    ) {

        PolicyVaultTopContent(

            policy = policy,

            onViewDetailsClick = onViewDetailsClick
        )

        Spacer(

            modifier = Modifier.height(16.dp)
        )

        PolicyDetailsCard(
            companyLogo = policy.companyLogo,

            idv = policy.idv,

            premium = policy.premium,

            expiry = policy.expiry
        )

        Spacer(

            modifier = Modifier.height(16.dp)
        )

        PrimaryCTAButton(

            text = "Renew Now",

            onClick = onRenewClick
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MotorPolicyCardPreview() {

    MotorPolicyCard(

        policy = HomeDummyData.vaultPolicies.first(),

        onRenewClick = {},

        onViewDetailsClick = {}
    )
}