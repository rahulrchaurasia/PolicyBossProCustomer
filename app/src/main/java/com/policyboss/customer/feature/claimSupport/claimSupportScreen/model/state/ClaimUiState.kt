package com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state

import androidx.compose.runtime.Immutable
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimItem
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimTab


// 1. Add this interface to define WHY the sheet is open
sealed interface ProductSelectionContext {
    data object FileNewClaim : ProductSelectionContext
    data object ViewClaimGuide : ProductSelectionContext
}

@Immutable
data class ClaimUiState(
    val selectedTab: ClaimTab = ClaimTab.MY_CLAIMS,

    val claims: List<ClaimItem> = emptyList(),

    val isLoading: Boolean = false,

    val isRefreshing: Boolean = false,

    // 2. Replace the boolean with the Context
    val productSelectionContext: ProductSelectionContext? = null
)