package com.policyboss.customer.feature.claimSupport.repository

import com.policyboss.customer.core.Resource
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.SubmittedClaim
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.ClaimDraft

import kotlinx.coroutines.flow.MutableStateFlow

import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimStatus

import com.policyboss.customer.feature.dummyData.AppDummyData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.milliseconds

@Singleton
class MockClaimRepository @Inject constructor() : ClaimRepository {

    // 1. Hold dummy data in memory
    private val _claims = MutableStateFlow(AppDummyData.dummySubmittedClaims)
    override val submittedClaimsFlow: Flow<List<SubmittedClaim>> = _claims.asStateFlow()


    override suspend fun submitClaimToServer(draft: ClaimDraft): Resource<SubmittedClaim> {
        return try {
            delay(2000.milliseconds) // Simulate network delay

            // 2. Create a fake claim from the user's draft
            val newClaim = SubmittedClaim(
                id = System.currentTimeMillis().toString(),
                productType = draft.productType!!,
                status = ClaimStatus.UNDER_REVIEW,
                claimNumber = "#CLM-${(10000..99999).random()}",
                registrationNumber = draft.lookupValue,
                insurerName = "Tata AIG Insurance",
                createdDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                subStatus = "Claim registered"
            )

            // 3. Update the Flow (The UI will see this instantly!)
            _claims.update { currentList -> listOf(newClaim) + currentList }

            Resource.Success(newClaim)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}