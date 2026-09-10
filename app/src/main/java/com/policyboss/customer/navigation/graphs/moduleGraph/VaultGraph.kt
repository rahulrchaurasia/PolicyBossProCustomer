package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.policyboss.customer.anim.NavigationAnimations
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.addManualPolicy.AddManualPolicyRoute
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.PolicyVaultRoute
import com.policyboss.customer.feature.tabfeatures.policyVault.viewmodel.AddManualPolicyViewModel

import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest

/*
What is toRoute()?

toRoute() is an extension function provided by Navigation Compose's
type-safe navigation.

Its job is:
Read the navigation arguments from the NavBackStackEntry and convert
them back into your destination object.


example :---->

Before type-safe navigation (old way)

Previously, we had to manually read arguments:
+++++++++++++++++++++++++++++++++++++++++++++++++++++++

val type = backStackEntry.arguments
    ?.getString("policyType")

Then convert:

val policyType = AddPolicyType.valueOf(type!!)

+++++++++++++++++++++++++++++++++++++++++++++++++++++++

This was error-prone because:

You had to remember string keys. Typos caused runtime crashes.
You had to manually convert strings to enums.

With toRoute() :------------>

Everything is automatic:

val args = backStackEntry.toRoute<Dest.AddManualPolicy>()

AddManualPolicyScreen(
    policyType = args.policyType
)

No string keys, no Bundle, and no valueOf().

In your project

Your destination:

@Serializable
data class AddManualPolicy(
    val policyType: AddPolicyType
) : Dest()
 */

fun NavGraphBuilder.vaultGraph(
   // navController: NavHostController,
    navigator: AppNavigator,
    padding: PaddingValues
) {

    navigation<Dest.VaultGraph>(
        startDestination = Dest.PolicyVault
    ) {

        composable<Dest.PolicyVault> {

            PolicyVaultRoute (
                contentPadding = padding,
                onNavigateToDetails = {
                    //navigator.navigateTo(Dest.JoinPrivilege)
                },

                onNavigateToAddManualPolicy = { type ->

                    //pass Serialized argument in navigation
                    navigator.navigateTo(
                        Dest.AddManualPolicy(
                            policyType = type
                        )
                    )
                }
                // Catch the callback from the Route and tell the NavController to move!

            )
           }


        composable<Dest.AddManualPolicy>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft }
        ) { backStackEntry ->

            val args = backStackEntry.toRoute<Dest.AddManualPolicy>()

            // 1. Inject your ViewModel here (using Hilt, Koin, or default Compose ViewModel)
            // e.g., val viewModel: AddManualPolicyViewModel = hiltViewModel()
            val viewModel: AddManualPolicyViewModel = viewModel()

            // 2. Call the Route, NOT the Screen directly
            AddManualPolicyRoute(
                viewModel = viewModel,
                contentPadding = padding,
                policyType = args.policyType,

                onBackClick = {
                    navigator.navigateBack()
                },

                onCloseClick = {
                    // Usually, 'X' dismisses the flow just like the back button
                    navigator.navigateBack()
                },


                onNavigateBack = {
                    navigator.navigateBack()
                }
            )
        }


    }
}