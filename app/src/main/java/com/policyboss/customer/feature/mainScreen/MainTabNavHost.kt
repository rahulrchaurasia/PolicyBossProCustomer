package com.policyboss.customer.feature.mainScreen

/*
Why putting it in AppRoot or NavGraph causes issues
If you define the navigation logic (the navController) inside a child graph,
 the parent (the Scaffold which holds the Bottom Bar) cannot see it.
  This is why you get an "issue with no controller."
 */
/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CustomBottomNavigationBar to the four feature graphs.
 Once that's in place, building HomeNavGraph, ClaimSupportNavGraph,
  PolicyVaultNavGraph, and PrivilegeNavGraph becomes straightforward
  and keeps each feature self-contained.

 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
//@Composable
//fun MainTabNavHost(
//    navController: NavHostController, // This is your inner tab NavController!
//    appNavigator: AppNavigator,// This is your inner tab NavController!
//    padding: PaddingValues
//) {
//
//    NavHost(
//        navController = navController,
//        startDestination = Dest.HomeGraph
//    ) {
//
//        homeGraph(
//            navController = navController,
//            appNavigator = appNavigator,
//            padding = padding,
//
//        )
//
//        claimGraph(
//            navController = navController,
//            navigator = appNavigator,
//            padding = padding
//        )
//
//        vaultGraph(
//            navController = navController,
//            navigator = appNavigator,
//            padding = padding
//        )
//
//        privilegeGraph(
//            navController = navController,
//            appNavigator = appNavigator,
//            padding = padding
//        )
//    }
//}