package com.policyboss.customer.feature.policyVault.ui.policyVaultScreen.component.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyCategory
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.PolicyVaultTabItem

/*
Note :
📝 The Golden Rule of stickyHeader

The Rule: A stickyHeader MUST be placed directly inside the main LazyColumn.

Why? The LazyColumn is the "boss" of the scrolling. It can only pin items it can directly see and manage.

The Problem: If you hide your Tab Bar inside another container (like a standard Column or your old PolicyVaultContent), the LazyColumn just sees one giant, unbreakable block of UI. It cannot reach inside that block to "grab" the Tab Bar and pin it.

The Fix: Unpack the items. Put the Header, the Tab Bar, and the List directly into the LazyColumn so it can control them individually.
 */
@Composable
fun PolicyVaultStickyTabs(
    tabs: List<PolicyVaultTabItem>,
    selectedCategory: PolicyCategory,
    backgroundColor: Color,
    elevation: Dp,
    onTabSelected: (PolicyCategory) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        // Same color as Status Bar.
        color = backgroundColor,

        // 0.dp → 2.5.dp as user scrolls.
        shadowElevation = elevation // 👈 Smoothly animates based on scroll
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
        ) {
            PolicyVaultTabBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                tabs = tabs,
                selectedCategory = selectedCategory,
                onTabSelected = onTabSelected
            )
        }
    }
}