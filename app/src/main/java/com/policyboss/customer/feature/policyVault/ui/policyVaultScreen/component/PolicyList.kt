package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData.mockPolicies
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyVaultPolicy
import com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.card.PolicyVaultCard

// Project-specific imports (Update these paths to match your project)
// import your.package.name.models.PolicyVaultPolicy
// import your.package.name.components.MotorPolicyCard
@Composable
fun PolicyList(

    policies: List<PolicyVaultPolicy>,

    onRenewClick: (PolicyVaultPolicy) -> Unit,

    onViewDetailsClick: (PolicyVaultPolicy) -> Unit

) {

    Column {

        policies.forEach { policy ->

            PolicyVaultCard(

                policy = policy,

                onRenewClick = {

                    onRenewClick(policy)
                },

                onViewDetailsClick = {

                    onViewDetailsClick(policy)
                }
            )

            Spacer(

                Modifier.height(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun PolicyListPreview() {
    // Create a dummy list based on your PolicyVaultPolicy data class
    // Adjust the parameters below to match your actual data structure


    // Wrap in your app's custom theme if you have one
    // PolicyBossTheme {
    PolicyList(
        policies = mockPolicies,
        onRenewClick = { /* Do nothing in preview */ },
        onViewDetailsClick = { /* Do nothing in preview */ }
    )
    // }
}