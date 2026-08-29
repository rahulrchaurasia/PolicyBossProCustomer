package com.policyboss.customer.feature.claimSupport.repository

import com.policyboss.customer.core.Resource
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.SubmittedClaim
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.ClaimDraft
import kotlinx.coroutines.flow.Flow

interface ClaimRepository {
    // Allows the "My Claims" tab to constantly listen for new claims
    val submittedClaimsFlow: Flow<List<SubmittedClaim>>
    
    // The POST request to submit a claim
    suspend fun submitClaimToServer(draft: ClaimDraft): Resource<SubmittedClaim>
}