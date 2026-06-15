package com.policyboss.customer.feature.home.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.policyboss.customer.R
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
            // 1. Show loading state if needed
            _uiState.update { it.copy(isLoading = true) }

            // 2. Simulate a network call or DB fetch (Replace with actual repository call)
            delay(500)

            // 3. Update the state with the fetched data
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    curatedPolicies = listOf("Car", "Bike", "CV", "Health", "Life", "Travel"),

                    // Add your dynamic banners here
                    //BannerDestination has BannerAction and base on BannerAction we take decison
                    promoBanners = listOf(
                        PromoBanner(
                            id = "renew_earn",
                            tagText = "RENEW & EARN",
                            title = "Become a ‘Privileged user’ and earn instantly on renewals",
                            buttonText = "Complete Setup",
                            imageRes = R.drawable.ic_banner1, // Replace with your actual drawable
                            isYellowTheme = true, // Triggers the yellow gradient
                            destination = BannerDestination.Privilege
                        ),
                        PromoBanner(
                            id = "renew_car",
                            tagText = "RENEW CAR INSURANCE",
                            title = "Act fast — your ‘Honda Amaze’ protection expires in 21 days",
                            buttonText = "Renew Now",
                            imageRes = R.drawable.ic_banner2, // Replace with your actual drawable
                            isYellowTheme = false, // Triggers the blue gradient
                            destination = BannerDestination.PolicyAction(
                                BannerAction.RenewCar
                                    )

                        ),
                        PromoBanner(
                            id = "build_portfolio_renew",
                            tagText = "BUILD YOUR POLICY PORTFOLIO",
                            title = "Link and access all your policies in just one click",
                            buttonText = "Renew Now",
                            imageRes = R.drawable.ic_banner3, // Replace with your actual drawable
                            isYellowTheme = false,
                            destination = BannerDestination.PolicyAction(
                                BannerAction.RenewLife
                            )
                        ),
                        PromoBanner(
                            id = "renew_life",
                            tagText = "RENEW LIFE INSURANCE",
                            title = "Act fast — your ‘life insurance’ expires in 21 days",
                            buttonText = "Renew Now",
                            imageRes = R.drawable.ic_banner5, // Replace with your actual drawable
                            isYellowTheme = false,
                            destination = BannerDestination.PolicyAction(
                                BannerAction.BuildPortfolioRenew
                            )
                        ),
                        PromoBanner(
                            id = "build_portfolio_sync",
                            tagText = "BUILD YOUR POLICY PORTFOLIO",
                            title = "Link and access all your policies in just one click",
                            buttonText = "Sync Email",
                            imageRes = R.drawable.ic_banner4, // Replace with your actual drawable
                            isYellowTheme = false,
                            destination = BannerDestination.PolicyAction(
                                BannerAction.BuildPortfolioSync
                            )
                        )

                    ),
                    quickActions = listOf(
                        QuickAction("renew", "Renew & Earn", "Become a 'Privileged user'", imageRes = R.drawable.ic_money, isPro = true),
                        QuickAction("vault", "Policy Vault", "All your policies in one place",imageRes = R.drawable.ic_dashboard2,),
                        QuickAction("claim", "Claim Support", "Guided claim filing & assistance", imageRes = R.drawable.ic_dashboard3,),
                        QuickAction("bosspedia", "BOSSPedia", "Daily insights about insurance", imageRes = R.drawable.ic_dashboard4,)
                    ),
                    earningBanners = listOf(
                        // Make sure to replace these with your actual drawable resources
                        EarningBanner("1", "Earnings instantly hit your account upon renewal", R.drawable.ic_money),
                        EarningBanner("2", "Help your network save smart, while boosting your income.", R.drawable.ic_earning_banner2),
                        EarningBanner("3", "Compare and secure the best policies curated for you", R.drawable.ic_earning_banner3),
                        EarningBanner("4", "See transparent potential earnings on each policy", R.drawable.ic_earning_banner2)
                    )
                )
            }
        }
    }

    fun onAction(
        action: HomeAction
    ) {

        when(action){

            HomeAction.OnShowPolicyBottomSheetClick -> {

                _uiState.update {

                    it.copy(
                        showPolicyBottomSheet = true
                    )
                }
            }

            HomeAction.OnDismissPolicyBottomSheet -> {

                _uiState.update {

                    it.copy(
                        showPolicyBottomSheet = false
                    )
                }
            }

            HomeAction.OnPrivilegeBannerClick -> {

                handleJoinPrivilege()
            }

            else -> Unit
        }
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



}