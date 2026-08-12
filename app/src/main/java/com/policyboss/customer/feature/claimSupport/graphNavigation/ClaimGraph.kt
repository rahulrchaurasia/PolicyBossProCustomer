package com.policyboss.customer.feature.claimSupport.graphNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.ui.ClaimGuideRoute
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.claimGuide.viewmodel.ClaimGuideViewModel
import com.policyboss.customer.feature.claimSupport.claimSupportJourney.fileClaim.ui.FileClaimRoute
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.ClaimSupportRoute
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.viewmodel.ClaimViewModel
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest


fun NavGraphBuilder.claimGraph(
   // navController: NavHostController,
    appNavigator: AppNavigator,
    padding: PaddingValues
) {

    navigation<Dest.ClaimGraph>(
        startDestination = Dest.ClaimSupport
    ) {

        composable<Dest.ClaimSupport> {

            // 1. Get the Hilt-injected ViewModel
            val viewModel: ClaimViewModel = hiltViewModel()

            // 2. Call your Route Composable and map the callbacks to your Navigator
            ClaimSupportRoute(
                viewModel = viewModel,
                contentPadding = padding,

                onNavigateToFileClaim = { product ->
                    // Example: pass the enum name or ID to your type-safe route
                    // navigator.navigateTo(Dest.FileClaim(product.name))
                    appNavigator.navigateTo(Dest.FileClaim(productType = product))
                },

                onNavigateToClaimGuide = { product ->
                    // Pass the Enum's name as a string to the route
                    appNavigator.navigateTo(Dest.ClaimGuide(productType = product))
                },

                onNavigateToCashlessGarage = {
                    // navigator.navigateTo(Dest.CashlessGarage)
                },

                onNavigateToInsurerContacts = {
                    // navigator.navigateTo(Dest.InsurerContacts)
                },

                onNavigateToFaqs = {
                    // navigator.navigateTo(Dest.Faqs)
                }
            )
        }
// 2. TARGET SCREEN: CLAIM GUIDE
        composable<Dest.ClaimGuide> { backStackEntry ->

            // 1. Extract the argument safely from the route
            val args = backStackEntry.toRoute<Dest.ClaimGuide>()
            val productType = args.productType // "Motor", "Health", etc.

            // 2. Get the SHARED Parent ViewModel (Scoped to Dest.ClaimGraph)
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val sharedClaimViewModel: ClaimViewModel = hiltViewModel(parentEntry)

            // 3. Get the PERSONAL Child ViewModel (Scoped to Dest.ClaimGuide)
            val guideViewModel: ClaimGuideViewModel = hiltViewModel()

            // 4. Observe the shared state from the Parent
            val activeProduct by sharedClaimViewModel.activeFlowProduct.collectAsStateWithLifecycle()

            ClaimGuideRoute(
                productType = productType,
                sharedViewModel = sharedClaimViewModel,
                viewModel = guideViewModel,
                onBackClick = { appNavigator.navigateBack() }
            )
            // 4. Pass everything to the Route

        }

        //FileClaim
        // 3. TARGET SCREEN: FileClaim
        composable<Dest.FileClaim> { backStackEntry ->
            val args = backStackEntry.toRoute<Dest.FileClaim>()
            // 2. Get the SHARED Parent ViewModel (Scoped to Dest.ClaimGraph)
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val sharedClaimViewModel: ClaimViewModel = hiltViewModel(parentEntry)

            // 3. Get the PERSONAL Child ViewModel (Scoped to Dest.ClaimGuide)
            val guideViewModel: ClaimGuideViewModel = hiltViewModel()

            // 4. Observe the shared state from the Parent
            val activeProduct by sharedClaimViewModel.activeFlowProduct.collectAsStateWithLifecycle()

            FileClaimRoute(
                productType = args.productType ,
                onBackClick = { appNavigator.navigateBack() },
                viewModel = guideViewModel
            )
        }



    }
}

