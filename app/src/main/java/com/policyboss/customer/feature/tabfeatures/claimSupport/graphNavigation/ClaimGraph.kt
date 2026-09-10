package com.policyboss.customer.feature.tabfeatures.claimSupport.graphNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.policyboss.customer.anim.NavigationAnimations
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.ui.AccidentDetailsRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimDetails.viewmodel.AccidentDetailsViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimGuide.ui.ClaimGuideRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.claimGuide.viewmodel.ClaimGuideViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.ui.DamagePhotosRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.viewmodel.DamagePhotosViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.driversLicense.DriversLicenseRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.policyReport.PoliceReportRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel.DriversLicenseViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.viewmodel.PoliceReportViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.ui.FileClaimRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.fileClaim.viewmodel.FileClaimViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.ui.ThirdPartyDetailsRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.thirdPartyDetail.viewmodel.ThirdPartyDetailsViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.ClaimSupportRoute
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.ClaimJourneyViewModel
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.viewmodel.ClaimViewModel

import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest


// fun : "HOW I organize/build the graph in code"
fun NavGraphBuilder.claimGraph(
   // navController: NavHostController,
    appNavigator: AppNavigator,
    padding: PaddingValues
) {

   // "CREATE the real graph"
    navigation<Dest.ClaimGraph>(
        startDestination = Dest.ClaimSupport
    ) {

      // ==========================================
        // 1. CLAIM SUPPORT (Start of Journey)
        // ==========================================
        composable<Dest.ClaimSupport> { backStackEntry ->

           // 1. Get the Hilt-injected ViewModel
         //   val viewModel: ClaimViewModel = hiltViewModel()

            // ⭐ GET PARENT VIEWMODEL (Graph Scoped)
            //Give you parent : which is Dest.ClaimGraph
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)

            // ⭐ GET CHILD VIEWMODEL (Screen Scoped)
            val screenViewModel: ClaimViewModel = hiltViewModel()

            // 2. Call your Route Composable and map the callbacks to your Navigator
            ClaimSupportRoute(
                viewModel = screenViewModel,
                contentPadding = padding,

                onNavigateToFileClaim = { product ->
                    // Example: pass the enum name or ID to your type-safe route
                    // navigator.navigateTo(Dest.FileClaim(product.name))
                    // 1. Save data to the shared journey
                    journeyViewModel.setProductType(product)
                    // But we used pass argument  2. Navigate (No need to pass arguments in the route anymore!)
                    appNavigator.navigateTo(Dest.FileClaim(productType = product))
                    //Navigation stores those arguments as part of the FileClaim destination's route/back-stack entry.
                },

                onNavigateToClaimGuide = { product ->
                    // Pass the Enum's name as a string to the route
                    journeyViewModel.setProductType(product)
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
        composable<Dest.ClaimGuide>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed
        ) { backStackEntry ->

            // 1. Extract the argument safely from the route
            val args = backStackEntry.toRoute<Dest.ClaimGuide>()
            val productType = args.productType // "Motor", "Health", etc.

            // ⭐ GET PARENT VIEWMODEL (Same instance as above!)
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }

            //This line is what makes it graph-scoped:
            //hiltViewModel(parentEntry)
            //because parentEntry represents:
            //Dest.ClaimGraph

            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)


            // ⭐ GET CHILD VIEWMODEL (Screen Scoped)
            val guideViewModel: ClaimGuideViewModel = hiltViewModel()

            // Observe the shared state
            val draft by journeyViewModel.claimDraft.collectAsStateWithLifecycle()

            ClaimGuideRoute(
                productType = productType,
                viewModel = guideViewModel,
                onBackClick = { appNavigator.navigateBack() }
            )
            // 4. Pass everything to the Route

        }

           //FileClaim
        // 3. TARGET SCREEN: FileClaim
        composable<Dest.FileClaim>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed
        )
        { backStackEntry ->
            val args = backStackEntry.toRoute<Dest.FileClaim>()
            // 2. Get the SHARED Parent ViewModel (Scoped to Dest.ClaimGraph)
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)

            // ⭐ GET CHILD VIEWMODEL (Screen Scoped)

            // 3. PERSONAL Child ViewModel (Scoped to this specific screen)
            val fileClaimViewModel: FileClaimViewModel = hiltViewModel()
            // Observe the shared state
            val draft by journeyViewModel.claimDraft.collectAsStateWithLifecycle()

            //ClaimGuideViewModel
            FileClaimRoute(
                productType = args.productType,

                viewModel = fileClaimViewModel,

                onNavigateBack =  { appNavigator.navigateBack() },
                onNavigateClose =  { appNavigator.navigateBack() },
                onNavigateNext = {

                    appNavigator.navigateTo(Dest.AccidentDetails)
                }

            )

//            FileClaimRoute(
//                productType = draft.productType!!,
//                onBackClick = { appNavigator.navigateBack() },
//                viewModel = guideViewModel
//            )
        }


        // ⭐ Screen 4 TARGET SCREEN: AccidentDetails
        composable<Dest.AccidentDetails>(
            enterTransition = { NavigationAnimations.slideInRight }, // Moving forward
            exitTransition = { NavigationAnimations.slideOutLeft },  // Pushed back when next screen opens
            popEnterTransition = { NavigationAnimations.slideInLeft }, // Returning to this screen
            popExitTransition = { NavigationAnimations.slideOutRight } // Back button pressed
            // ... transitions ...
        )
        // backStackEntry represents: Dest.AccidentDetails
        { backStackEntry ->


            // Get the SAME parent ViewModel because we are still in ClaimGraph!
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)

            val accidentDetailsViewModel: AccidentDetailsViewModel = hiltViewModel()

            AccidentDetailsRoute(
                viewModel = accidentDetailsViewModel,
                onNavigateBack = { appNavigator.navigateBack() },
                onNavigateNext = {  accidentUiState ->
                    // ⭐ 1. Save to the Journey
                    //journeyViewModel.saveAccidentDetails(accidentUiState)

                    // ⭐ 2. Navigate to Third Party Details
                    appNavigator.navigateTo(Dest.ThirdPartyDetails)

                }
            )
        }


        // In your NavGraphBuilder.claimGraph extension:

        // ==========================================
        // ⭐ Screen 5: Third Party Details
        // ==========================================
        composable<Dest.ThirdPartyDetails>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft },
            popEnterTransition = { NavigationAnimations.slideInLeft },
            popExitTransition = { NavigationAnimations.slideOutRight }
        ) { backStackEntry ->

            // ⭐ GET SHARED PARENT VIEWMODEL
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)

            // ⭐ GET CHILD VIEWMODEL (Screen Scoped)
            val thirdPartyViewModel: ThirdPartyDetailsViewModel = hiltViewModel()

            // Call the Route instead of the Screen directly
            ThirdPartyDetailsRoute(
                viewModel = thirdPartyViewModel,
                onNavigateBack = { appNavigator.navigateBack() },
                onNavigateNext = { thirdPartyUiState ->

                    // 1. Save state to Journey
                     journeyViewModel.saveThirdPartyDetails(thirdPartyUiState)

                    // 2. Navigate to Step 3 (e.g., Upload Documents)

                    appNavigator.navigateTo(Dest.DamagePhotos)
                }
            )
        }


        // ==========================================
        // ⭐ Screen 6: Damage Photos (Step 3/5)
        // ==========================================
        composable<Dest.DamagePhotos>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft },
            popEnterTransition = { NavigationAnimations.slideInLeft },
            popExitTransition = { NavigationAnimations.slideOutRight }
        ) { backStackEntry ->

            // ⭐ GET SHARED PARENT VIEWMODEL
            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)

            // ⭐ GET CHILD VIEWMODEL (Screen Scoped)
            val damagePhotosViewModel: DamagePhotosViewModel = hiltViewModel()

            DamagePhotosRoute(
                viewModel = damagePhotosViewModel,
                onNavigateBack = { appNavigator.navigateBack() },
                onNavigateNext = { photosList ->

                    // 1. Save the list of Uris to your shared Journey ViewModel
                     journeyViewModel.saveDamagePhotos(photosList)

                    // 2. Navigate to Step 4 (e.g., Bank Details or Summary)
                     appNavigator.navigateTo(Dest.PoliceReport)
                }
            )
        }

        // ==========================================
        // ⭐ Screen 7: Police Report (Step 4/5)
        // ==========================================
        composable<Dest.PoliceReport>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft },
            popEnterTransition = { NavigationAnimations.slideInLeft },
            popExitTransition = { NavigationAnimations.slideOutRight }
        ) { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)
            val policeReportViewModel: PoliceReportViewModel = hiltViewModel()

            PoliceReportRoute(
                viewModel = policeReportViewModel,
                onNavigateBack = { appNavigator.navigateBack() },
                onNavigateNext = { documentUri ->
                    // 1. Save optional URI to the Journey
                    journeyViewModel.savePoliceReport(documentUri)

                    // 2. Navigate to Driver's license
                    appNavigator.navigateTo(Dest.DriversLicense)
                }
            )
        }

        // ==========================================
        // ⭐ Screen 8: Driver's license (Step 5/5)
        // ==========================================
        composable<Dest.DriversLicense>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft },
            popEnterTransition = { NavigationAnimations.slideInLeft },
            popExitTransition = { NavigationAnimations.slideOutRight }
        ) { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                appNavigator.getBackStackEntry<Dest.ClaimGraph>()
            }
            val journeyViewModel: ClaimJourneyViewModel = hiltViewModel(parentEntry)
            val driversLicenseViewModel: DriversLicenseViewModel = hiltViewModel()


            DriversLicenseRoute(
                viewModel = driversLicenseViewModel,
                journeyViewModel = journeyViewModel, // 🚀 Pass it in here
                onNavigateBack = { appNavigator.navigateBack() },
                onNavigateToSuccess = {

                    // 1. Wipe the draft so the next claim starts fresh
                    journeyViewModel.clearJourney()



                    // 2. Pop all the way back to the root Claims screen
                    // inclusive = false means we DESTROY the form screens, but KEEP ClaimSupport
                    appNavigator.popBackToRoute(Dest.ClaimSupport, inclusive = false)
                }
            )
        }


    }
}

