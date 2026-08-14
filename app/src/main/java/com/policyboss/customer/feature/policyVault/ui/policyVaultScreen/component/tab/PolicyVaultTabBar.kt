package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyCategory
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyVaultTabItem

@Composable
fun PolicyVaultTabBar(
    modifier: Modifier = Modifier,
    tabs: List<PolicyVaultTabItem>,
    selectedCategory: PolicyCategory,
    onTabSelected: (PolicyCategory) -> Unit,


) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF3F4F6))
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 4.dp, vertical = 6.dp)
    ) {

        tabs.forEach { tab ->

            PolicyVaultTab(

                item = tab,

                selected = tab.category == selectedCategory,

                onClick = {

                    onTabSelected(tab.category)
                }
            )
        }
    }
}

////

