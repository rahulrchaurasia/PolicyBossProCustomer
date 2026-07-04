package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.feature.claimSupport.ui.ClaimSupportRoute
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest


fun NavGraphBuilder.claimGraph(
   // navController: NavHostController,
    navigator: AppNavigator,
    padding: PaddingValues
) {

    navigation<Dest.ClaimGraph>(
        startDestination = Dest.ClaimSupport
    ) {

        composable<Dest.ClaimSupport> {

            ClaimSupportRoute(
                contentPadding = PaddingValues(0.dp),
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

