package com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.component.home.vaultSection.component.EmptyVaultState
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultAction
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultUiState
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.tab.PolicyVaultTabBar


@Composable
fun PolicyVaultContent(

    uiState: PolicyVaultUiState,

    onAction: (PolicyVaultAction) -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

    ) {

        PolicyVaultTabBar(

            tabs = AppDummyData.policyVaultTabs,

            selectedCategory = uiState.selectedCategory,

            onTabSelected = { category ->

                onAction(
                    PolicyVaultAction.OnCategorySelected(category)
                )
            }
        )

        Spacer(

            Modifier.height(20.dp)
        )

        Text(

            text = "Total policies: ${uiState.policies.size}",

            style = MaterialTheme.typography.bodyMedium,

            fontWeight = FontWeight.SemiBold
        )

        Spacer(

            Modifier.height(16.dp)
        )

        if (uiState.policies.isEmpty()) {

            EmptyVaultState()

        } else {

            PolicyList(

                policies = uiState.policies,

                onRenewClick = {},

                onViewDetailsClick = {}
            )
        }
    }
}