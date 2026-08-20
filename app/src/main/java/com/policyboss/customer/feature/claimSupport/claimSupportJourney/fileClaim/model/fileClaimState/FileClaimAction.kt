package com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.model.fileClaimState

import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

// 2. Actions: Events triggered BY the User (or System) TO the ViewModel
sealed class FileClaimAction {
    data class LoadRequirements(val productType: AddPolicyType) : FileClaimAction()

    object OnContinueClick : FileClaimAction()
}