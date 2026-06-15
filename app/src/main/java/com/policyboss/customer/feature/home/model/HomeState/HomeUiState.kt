package com.policyboss.customer.feature.home.model.HomeState

import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction

/*
        User Click
           ↓
        HomeScreen
           ↓
        onAction(HomeAction)
           ↓
        HomeRoute
           ↓
        ViewModel (if business logic/API required)
           ↓
        UiState OR UiEvent
           ↓
        HomeRoute
           ↓
        Navigation / Snackbar
 */
// The UI State for the Home Screen ONLY
data class HomeUiState(
    val isLoading: Boolean = false,
    val userName: String = "Rahul",
    val userInitials: String = "RC",

    // Lists driving the UI
    val promoBanners: List<PromoBanner> = emptyList(),
    val curatedPolicies: List<String> = emptyList(),
    val quickActions: List<QuickAction> = emptyList(),
    val earningBanners: List<EarningBanner> = emptyList(),


// Fix: Add a default value here
    val showPolicyBottomSheet: Boolean = false  // for   BottomSheet
)