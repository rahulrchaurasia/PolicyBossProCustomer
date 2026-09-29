package com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState

sealed interface AddPolicyAction {
    data class OnCarNumberChanged(val value: String) : AddPolicyAction
    data class OnPolicyNumberChanged(val value: String) : AddPolicyAction
    object OnSubmit : AddPolicyAction
}