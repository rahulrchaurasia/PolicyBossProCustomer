package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.feature.policyVault.ui.PolicyVaultRoute
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest


fun NavGraphBuilder.vaultGraph(
    navigator: AppNavigator,
    padding: PaddingValues
) {

    navigation<Dest.VaultGraph>(
        startDestination = Dest.PolicyVault
    ) {

        composable<Dest.PolicyVault> {

            PolicyVaultRoute (
                contentPadding = padding,
                onNavigateToQuiz = {
                    navigator.navigateTo(Dest.JoinPrivilege)
                }
            )
        }

//        composable<Dest.ClaimDetails> {
//
//            ClaimDetailsScreen()
//
//        }
    }
}