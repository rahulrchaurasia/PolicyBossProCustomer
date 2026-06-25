package com.policyboss.customer.feature.home.model.home

import com.policyboss.customer.feature.home.model.vault.VaultPolicy


/*
Here is how you separate the flow of data:

UiState (StateFlow): Represents what the UI is (Loading, Success, Error).

UiAction (Function): What the user does (Clicks a button).
 Flow flows UP from UI to ViewModel.

UiEvent (Channel): What the ViewModel wants the UI to do
 once (Navigate, Show a Snackbar). Data flows DOWN from ViewModel to UI.
 */

/************  Direct Navigation **********************************/
/*

Why some actions go directly to navigation?

You have:

OnProfileClick
OnViewAllVaultClick
OnExploreBosspediaClick

handled directly:

is HomeAction.OnProfileClick ->
    onNavigateToProfile()

✅ This is correct.

/************  With ViewModel Navigation **********************************/

Because these actions do NOT require:

API call
Validation
Business rule
Repository

They are pure navigation.
 */


/*

Why does JoinPrivilege go through ViewModel?

Because: OnJoinPrivilegeClick
needs:
Check eligibility
Call API
Check user role
Track analytics

then:

viewModel.onAction(action)
 */
sealed interface HomeAction {
    // Pure navigation
    object OnProfileClick : HomeAction
    object OnViewAllVaultClick : HomeAction
    object OnExploreBosspediaClick : HomeAction

    object OnAssistanceClick : HomeAction


    // API/business logic
    object OnPrivilegeBannerClick : HomeAction

    // Mark: BottomSheet Action Handle
    // region BottomSheet
    data class OnShowPolicyBottomSheetClick (
        val policy: VaultPolicy
    ): HomeAction

    object OnDismissPolicyBottomSheet : HomeAction
    //endregion


    // Actions that might require ViewModel logic
    data class OnQuickActionClick(
        val actionId: String
    ) : HomeAction

    data class OnPolicyCategoryClick(
        val category: String
    ) : HomeAction

    data class OnPromoBannerClick(
        val bannerId: String
    ) : HomeAction

    data class OnEarningBannerClick(
        val bannerId: String
    ) : HomeAction


    // Mark: vaultPolicies Action
    //region Vault Action
    data class OnVaultTabSelected(
        val index: Int
    ) : HomeAction
    //endregion


    // Mark: video Action
    //region  video Action
    data object OnVideoViewMoreClick : HomeAction
    data class OnVideoClick(val videoId: String) : HomeAction
    //endregion
}