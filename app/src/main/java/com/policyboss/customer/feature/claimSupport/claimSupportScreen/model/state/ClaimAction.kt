package com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.state

import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimSupportMenu
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimTab
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType


sealed interface ClaimAction {

    data class OnTabSelected(
        val tab: ClaimTab
    ) : ClaimAction

    data object OnFileClaimClick : ClaimAction

    data object OnSupportCallClick : ClaimAction

    data object OnDismissBottomSheet : ClaimAction

    data class OnProductSelected(
        val product: AddPolicyType
    ) : ClaimAction

    data class OnSupportMenuClick(
        val menu: ClaimSupportMenu
    ) : ClaimAction
}