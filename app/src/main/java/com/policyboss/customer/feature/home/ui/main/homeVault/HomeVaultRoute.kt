package com.policyboss.customer.feature.home.ui.main.homeVault





// Shared Imports
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


@Composable
fun HomeVaultRoute(
    contentPadding: PaddingValues,
    onBackClick: () -> Unit,
    onRenewClick: (VaultPolicy) -> Unit,
    onViewDetailsClick: (VaultPolicy) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val policies = AppDummyData.vaultPolicies
    val filteredPolicies = if (selectedTab == 0) policies else policies.filter { it.tabId == selectedTab }

//    HomeAllVaultScreen(
//        contentPadding = contentPadding,
//        selectedTab = selectedTab,
//        policies = filteredPolicies,
//        onTabSelected = { selectedTab = it },
//        onBackClick = onBackClick,
//        onRenewClick = onRenewClick,
//        onViewDetailsClick = onViewDetailsClick
//    )

    // Call your new Scaffold-backed screen here
    HomeAllVaultDummyScreen(
        contentPadding = contentPadding,
        selectedTab = selectedTab,
        policies = filteredPolicies,
        onTabSelected = { selectedTab = it },
        onBackClick = onBackClick,
        onRenewClick = onRenewClick,
        onViewDetailsClick = onViewDetailsClick
    )
}


// =======================================================
// PREVIEWS
// =======================================================

@Preview(showBackground = true, showSystemUi = true, name = "Home All Vault - Populated")
@Composable
private fun HomeAllVaultScreenPreview() {
    PolicyBossCustomerTheme {
        HomeAllVaultScreen(
            contentPadding = PaddingValues(0.dp),
            selectedTab = 0,
            policies = AppDummyData.vaultPolicies, // Uses dummy data
            onTabSelected = {},
            onBackClick = {},
            onRenewClick = {},
            onViewDetailsClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Home All Vault - Empty")
@Composable
private fun HomeAllVaultScreenEmptyPreview() {
    PolicyBossCustomerTheme {
        HomeAllVaultScreen(
            contentPadding = PaddingValues(0.dp),
            selectedTab = 2, // Assume tab 2 (CV) has no policies
            policies = emptyList(), // Pass empty list to test empty state
            onTabSelected = {},
            onBackClick = {},
            onRenewClick = {},
            onViewDetailsClick = {}
        )
    }
}