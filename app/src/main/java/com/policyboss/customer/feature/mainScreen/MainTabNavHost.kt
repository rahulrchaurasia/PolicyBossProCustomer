package com.policyboss.customer.feature.mainScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest
import com.policyboss.customer.navigation.graphs.moduleGraph.claimGraph
import com.policyboss.customer.navigation.graphs.moduleGraph.homeGraph
import com.policyboss.customer.navigation.graphs.moduleGraph.privilegeGraph
import com.policyboss.customer.navigation.graphs.moduleGraph.vaultGraph


/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CustomBottomNavigationBar to the four feature graphs.
 Once that's in place, building HomeNavGraph, ClaimSupportNavGraph,
  PolicyVaultNavGraph, and PrivilegeNavGraph becomes straightforward
  and keeps each feature self-contained.

 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
@Composable
fun MainTabNavHost(
    navController: NavHostController,
    appNavigator: AppNavigator,
    padding: PaddingValues
) {

    NavHost(

        navController = navController,

        startDestination = Dest.HomeGraph

    ) {

        homeGraph(appNavigator, padding)

        claimGraph(appNavigator, padding)

        vaultGraph(appNavigator, padding)

        privilegeGraph(appNavigator, padding)

    }

}