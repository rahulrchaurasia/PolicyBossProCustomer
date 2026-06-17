package com.policyboss.customer.feature.home.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.feature.home.model.HomeState.HomeAction
import com.policyboss.customer.feature.home.model.HomeState.HomeUiEvent
import com.policyboss.customer.feature.home.model.HomeState.HomeUiState
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch


import kotlinx.coroutines.flow.receiveAsFlow

import javax.inject.Inject


/*
User Click
   ↓
HomeScreen
   ↓
onAction(HomeAction)
   ↓
HomeRoute
   ↓
ViewModel (if business logic/API required)
   ↓
UiState OR UiEvent
   ↓
HomeRoute
   ↓
Navigation / Snackbar
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    // Private mutable state
    private val _uiState = MutableStateFlow(HomeUiState())
    // Public immutable state for Compose to observe
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // The Channel for one-time navigation/snackbars
    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        fetchHomeData()
    }


    private fun fetchHomeData() {

        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    isLoading = true
                )
            }

            delay(2000)

            _uiState.update {

                it.copy(

                    isLoading = false,

                    promoBanners =
                        HomeDummyData.promoBanners,

                    quickActions =
                        HomeDummyData.quickActions,

                    earningBanners =
                        HomeDummyData.earningBanners,

                    curatedPolicies =
                        HomeDummyData.curatedPolicies,

                    vaultPolicies =
                        HomeDummyData.vaultPolicies
                )
            }
        }
    }


    //region onAction Hamdling
    fun onAction(
        action: HomeAction
    ) {

        when (action) {
            is HomeAction.OnShowPolicyBottomSheetClick ->
                handlePolicyClick(action)

            is HomeAction.OnDismissPolicyBottomSheet ->
                handleDismiss()

            is HomeAction.OnPrivilegeBannerClick ->
                handlePrivilegeClick()

            else -> {

            }
        }
    }

    private fun handlePolicyClick(action: HomeAction.OnShowPolicyBottomSheetClick) {
        _uiState.update {
            it.copy(
                //showPolicyBottomSheet = true,
                selectedVaultPolicy = action.policy
            )
        }
    }

    private fun handleDismiss() {
        _uiState.update {
            it.copy(
               // showPolicyBottomSheet = false
                selectedVaultPolicy = null
            )
        }
    }
    private fun handlePrivilegeClick() {
        handleJoinPrivilege()
    }

    private fun handleJoinPrivilege() {

        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    isLoading = true
                )
            }

            delay(1500)

            val isEligible = true

            _uiState.update {

                it.copy(
                    isLoading = false
                )
            }

            if (isEligible) {

                _uiEvent.send(

                    HomeUiEvent
                        .NavigateToPrivilegeSuccess
                )
            } else {

                _uiEvent.send(

                    HomeUiEvent
                        .ShowSnackbar(
                            "Not eligible"
                        )
                )
            }
        }
    }

    //endregion


}