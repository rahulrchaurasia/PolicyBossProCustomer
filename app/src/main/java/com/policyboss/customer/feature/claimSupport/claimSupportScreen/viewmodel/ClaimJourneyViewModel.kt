package com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsUiState
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


// ********************************************************
//            Shared claim journey data
//********************************************************
data class ClaimDraft(
    val productType: AddPolicyType? = null,
    // Step 1: Accident Details
    val lookupType: LookupType = LookupType.VEHICLE_NUMBER,
    val lookupValue: String = "",
    val incidentDate: String = "",
    val incidentTime: String = "",
    val location: String = "",
    val description: String = "",
    // Step 2 & 3: Future screens
    val otherDriverName: String = "",
    val otherDriverPhone: String = "",
    val damagePhotos: List<String> = emptyList()
    // Add more fields as the journey grows
)

enum class LookupType { POLICY_NUMBER, VEHICLE_NUMBER }

// 2. The Journey ViewModel (Scoped to the Graph)
@HiltViewModel
class ClaimJourneyViewModel @Inject constructor() : ViewModel() {

    private val _claimDraft = MutableStateFlow(ClaimDraft())
    val claimDraft = _claimDraft.asStateFlow()

    fun setProductType(type: AddPolicyType) {
        _claimDraft.update { it.copy(productType = type) }
    }

    // ==========================================
    // ⭐ STEP 1: Save Accident Details
    // ==========================================
    fun saveAccidentDetails(uiState: AccidentDetailsUiState) {
        _claimDraft.update { currentDraft ->
            currentDraft.copy(
                lookupType = uiState.selectedLookupType,
                lookupValue = uiState.lookupValue,
                incidentDate = uiState.incidentDate,
                incidentTime = uiState.incidentTime,
                location = uiState.location,
                description = uiState.description
            )
        }
    }

    // ==========================================
    // ⭐ STEP 2: Save Third Party Details
    // ==========================================
    fun saveThirdPartyDetails(uiState: ThirdPartyDetailsUiState) {
        _claimDraft.update { currentDraft ->
            currentDraft.copy(
                otherDriverName = uiState.driverName,
                otherDriverPhone = uiState.phoneNumber
            )
        }
    }

    fun clearJourney() {
        _claimDraft.value = ClaimDraft() // Reset when journey is cancelled/finished
    }




}