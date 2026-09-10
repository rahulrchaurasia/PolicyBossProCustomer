package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.core.Resource
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.model.claimDetailState.AccidentDetailsUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.model.state.ThirdPartyDetailsUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.repository.ClaimRepository
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.AddPolicyType

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject





// ********************************************************
//            Shared claim journey data
// ********************************************************
data class ClaimDraft(
    val productType: AddPolicyType? = null,
    val lookupType: LookupType = LookupType.VEHICLE_NUMBER,
    val lookupValue: String = "",
    val incidentDate: String = "",
    val incidentTime: String = "",
    val location: String = "",
    val description: String = "",
    val otherDriverName: String = "",
    val otherDriverPhone: String = "",
    val damagePhotos: List<Uri> = emptyList(),
    val policeReportUri: Uri? = null,
    val driversLicenseUri: Uri? = null
)
sealed interface ClaimJourneyEvent {
    data class Loading(val isLoading: Boolean) : ClaimJourneyEvent
    data object SubmissionSuccess : ClaimJourneyEvent
    data class ShowError(val message: String) : ClaimJourneyEvent
}

enum class LookupType { POLICY_NUMBER, VEHICLE_NUMBER }


// ==========================================
// The Journey ViewModel (Scoped to the Graph)
// ==========================================
@HiltViewModel
class ClaimJourneyViewModel @Inject constructor(
    // 🚀 1. Inject your Repository here so we can hit the API
    private val repository: ClaimRepository
) : ViewModel() {

    private val _claimDraft = MutableStateFlow(ClaimDraft())
    val claimDraft = _claimDraft.asStateFlow()

    private val _journeyEvent = MutableSharedFlow<ClaimJourneyEvent>()
    val journeyEvent = _journeyEvent.asSharedFlow()

    fun setProductType(type: AddPolicyType) {
        _claimDraft.update { it.copy(productType = type) }
    }

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

    fun saveThirdPartyDetails(uiState: ThirdPartyDetailsUiState) {
        _claimDraft.update { currentDraft ->
            currentDraft.copy(
                otherDriverName = uiState.driverName,
                otherDriverPhone = uiState.phoneNumber
            )
        }
    }

    fun saveDamagePhotos(photos: List<Uri>) {
        _claimDraft.update { it.copy(damagePhotos = photos) }
    }

    fun savePoliceReport(uri: Uri?) {
        _claimDraft.update { it.copy(policeReportUri = uri) }
    }

    fun saveDriversLicense(uri: Uri) {
        _claimDraft.update { it.copy(driversLicenseUri = uri) }
    }

    // ==========================================
    // ⭐ FINAL STEP: Submit to API via Repository
    // ==========================================
    fun submitFinalClaim() {
        viewModelScope.launch {
            // 1. Tell UI to show a loading state
            _journeyEvent.emit(ClaimJourneyEvent.Loading(true))

            // 2. Push the completed draft to the Repository
            val result = repository.submitClaimToServer(_claimDraft.value)

            // 3. Hide loading state
            _journeyEvent.emit(ClaimJourneyEvent.Loading(false))

            // 4. Handle response
            when (result) {
                is Resource.Success -> {
                    // This tells the UI to navigate to ClaimSuccess
                    _journeyEvent.emit(ClaimJourneyEvent.SubmissionSuccess)
                }
                is Resource.Error -> {
                    // This tells the UI to show a Snackbar
                    _journeyEvent.emit(ClaimJourneyEvent.ShowError(result.message))
                }
            }
        }
    }

    fun clearJourney() {
        _claimDraft.value = ClaimDraft()
    }
}