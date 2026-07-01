package com.policyboss.customer.navigation.graphs.moduleGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.feature.privilege.ui.PrivilegeRoute
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest



fun NavGraphBuilder.privilegeGraph(
    navigator: AppNavigator,
    padding: PaddingValues
) {

    navigation<Dest.PrivilegeGraph>(
        startDestination = Dest.Privilege
    ) {

        composable<Dest.Privilege> {

            PrivilegeRoute (
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