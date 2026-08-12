package com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.viewmodel

import androidx.lifecycle.ViewModel
import com.policyboss.customer.feature.policyVault.model.policyVaultModel.AddPolicyType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/*
**************************************************
Without SavedStateHandle:
You have to pass the argument from the Composable into a ViewModel function.

Kotlin
// In your UI
val viewModel: ClaimGuideViewModel = hiltViewModel()
LaunchedEffect(Unit) {
    viewModel.loadGuide(args.productType) // The UI has to tell the ViewModel
}
With SavedStateHandle:
The ViewModel is completely self-sufficient.

Kotlin
@HiltViewModel
class ClaimGuideViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle // System hands this in automatically
) : ViewModel() {

    // ViewModel grabs the argument itself instantly!
    private val productType = savedStateHandle.toRoute<Dest.ClaimGuide>().productType
}
***************************************************
 */
@HiltViewModel
class ClaimGuideViewModel @Inject constructor() : ViewModel() {

    // 🚀 1. Changed type from String to AddPolicyType
    //private val productType: AddPolicyType = savedStateHandle.toRoute<Dest.ClaimGuide>().productType

    private val _isFetching = MutableStateFlow(false)
    val isFetching = _isFetching.asStateFlow()


    // We expose a function for the UI to trigger the fetch
    fun fetchGuideForProduct(type: AddPolicyType) {
        // Now you have full access to your enum properties here!
        // Example: type.name (gives "CAR") or type.displayTitle (gives "Car Insurance")

        when(type) {
            AddPolicyType.CAR -> { /* Fetch Car Guide */ }
            AddPolicyType.HEALTH -> { /* Fetch Health Guide */ }
            // etc...
            else -> {}
        }
    }
}