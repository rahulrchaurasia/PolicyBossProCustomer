package com.policyboss.customer.feature.claimSupport.claimSupportScreen.model

import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType

data class SubmittedClaim(
    val id: String,
    val productType: AddPolicyType,
    val status: ClaimStatus,
    val claimNumber: String,
    val registrationNumber: String,
    val insurerName: String,
    val createdDate: String,
    val subStatus: String,
    // Default values for the bottom expandable section in the UI
    val businessHours: String = "8AM - 6 PM",
    val contactNumber: String = "+91 97467 80291"
)