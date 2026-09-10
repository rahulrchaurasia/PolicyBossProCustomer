package com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.component.home.vaultSection.component.EmptyVaultState
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultAction
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultState.PolicyVaultUiState
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.bottomSheet.AddPolicyBottomSheet
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.bottomSheet.SortByBottomSheet
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.PolicyList
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.PolicyVaultHeader
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.SortByButton
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.card.PrivacyNote
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.card.SyncPromoCard
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.component.tab.PolicyVaultStickyTabs

import com.policyboss.customer.ui.components.button.AddPolicyButton
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.gradients.AppGradients
import kotlinx.coroutines.launch



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PolicyVaultScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: PolicyVaultUiState,
    onAction: (PolicyVaultAction) -> Unit
) {
    var showSortBottomSheet by remember { mutableStateOf(false) }
    var showAddPolicyBottomSheet by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    // =========================================================
    // ⭐ CONTINUOUS SCROLL PROGRESS ⭐
    // =========================================================
    val collapseProgress by remember {
        derivedStateOf {

            val headerItem =
                listState.layoutInfo.visibleItemsInfo
                    .firstOrNull { it.index == 0 }

            if (headerItem != null) {

                // Header is still visible.
                //
                // offset:
                //   0 px      = header at original position
                //   -50 px    = header moved 50 px upward
                //   -height   = header completely disappeared
                //
                val scrolledPx =
                    (-headerItem.offset)
                        .coerceAtLeast(0)

                (
                        scrolledPx.toFloat() /
                                headerItem.size.toFloat()
                        )
                    .coerceIn(0f, 1f)

            } else {

                // Header is no longer visible.
                // Therefore, the sticky tab has reached its pinned state.
                1f
            }
        }
    }

    // =========================================================
    // ⭐ GESTURE-DRIVEN UI STATES ⭐
    // =========================================================

    // Smoothly blends from Transparent -> AppBarBlue exactly matching the thumb
    val unifiedTopBarColor = androidx.compose.ui.graphics.lerp(
        start = Color.Transparent,
        stop = AppColors.AppBarBlue,
        fraction = collapseProgress
    )

    // Smoothly lifts from 0dp -> 4dp elevation

    val tabElevation by remember {
        derivedStateOf {
            (collapseProgress * 2.5f).dp
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppGradients.ScreenSurfaceGradient)
    ) {

        // 👇 ADD HERE
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    contentPadding.calculateTopPadding()
                )
                .background(
                    unifiedTopBarColor
                )
        )

        // =====================================================
        // MAIN LIST
        // =====================================================
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = contentPadding.calculateTopPadding()),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = contentPadding.calculateBottomPadding() + 24.dp
            )
        ) {
            // 1. Header
            item {
                PolicyVaultHeader(
                    onSyncMailClick = { onAction(PolicyVaultAction.OnSyncMailClick) }
                )
                Spacer(Modifier.height(20.dp))
            }

            // 2. ⭐ STICKY TAB BAR
            stickyHeader(contentType = "policy_vault_sticky_tabs") {
                PolicyVaultStickyTabs(
                    tabs = AppDummyData.policyVaultTabs,
                    selectedCategory = uiState.selectedCategory,
                    // Same color used by the status bar.
                    // This makes the two surfaces feel like one layer.
                    backgroundColor = unifiedTopBarColor,

                    // Elevation gradually appears as the tabs become sticky.
                    elevation = tabElevation,

                    onTabSelected = { category ->
                        onAction(PolicyVaultAction.OnCategorySelected(category))
                    }
                )

            }

            // 3. Total Policies Count
            item {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Total policies: ${uiState.policies.size}",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(16.dp))
            }

            // 4. Policy List Content
            item {
                if (uiState.policies.isEmpty()) {
                    EmptyVaultState()
                } else {
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        PolicyList(
                            policies = uiState.policies,
                            onRenewClick = { /* Handle inside PolicyList */ },
                            onViewDetailsClick = { /* Handle inside PolicyList */ }
                        )
                    }
                }
            }

            // 5. Add Policy Button
            item {
                AddPolicyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                    onClick = { showAddPolicyBottomSheet = true }
                )
            }

            // 6. Sync Promo Card
            item {
                Spacer(Modifier.height(40.dp))
                SyncPromoCard(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onSyncClick = { onAction(PolicyVaultAction.OnSyncMailClick) }
                )
            }

            // 7. Privacy & Security Note
            item {
                Spacer(Modifier.height(32.dp))
                PrivacyNote()
            }

            // 8. Divider
            item {
                Spacer(Modifier.height(32.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = AppColors.Dividers
                )
            }

            // 9. Sort By Button
            item {
                SortByButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { showSortBottomSheet = true }
                )
            }
        }

        // =====================================================
        // ⭐ STATUS BAR OVERLAY (DRAWN LAST = ON TOP) ⭐
        // =====================================================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(contentPadding.calculateTopPadding())
                .background(unifiedTopBarColor)
        )
    }

    // --- Bottom Sheets ---
    if (showSortBottomSheet) {
        SortByBottomSheet(
            currentSelection = uiState.selectedSortOption,
            onOptionSelected = { selectedSort ->
                onAction(PolicyVaultAction.OnSortOptionSelected(selectedSort))
                showSortBottomSheet = false
                coroutineScope.launch { listState.animateScrollToItem(0) }
            },
            onDismissRequest = { showSortBottomSheet = false }
        )
    }

    if (showAddPolicyBottomSheet) {
        AddPolicyBottomSheet(
            onDismissRequest = { showAddPolicyBottomSheet = false },
            onOptionSelected = { selectedType ->
                onAction(PolicyVaultAction.OnAddPolicyTypeSelected(selectedType))
                showAddPolicyBottomSheet = false
            }
        )
    }
}

@Preview(
    name = "Policy Vault Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PolicyVaultScreenPreview() {

    PolicyBossCustomerTheme {

        PolicyVaultScreen(
            modifier = Modifier,
            contentPadding = PaddingValues(),
            uiState = PolicyVaultUiState(),
            onAction = {}
        )
    }
}