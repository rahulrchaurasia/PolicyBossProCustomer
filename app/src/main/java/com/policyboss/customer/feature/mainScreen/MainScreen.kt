package com.policyboss.customer.feature.mainScreen


import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.navigation.Dest

// 8. Main Home Screen with Bottom Navigation


//import com.interstellar.rahulpihujetpackdemo.rootGraph.graph.route

/*

 MainActivity
   ↓
RootNavGraph
   ↓
 ├── AuthGraph
 └── MainGraph
        ↓
     MainScreen (Bottom Tabs)
        ↓
     Home / Cart / Profile


OLD :---->
MainScreen
   └── NavHost (tabs)
RootNavGraph
   └── NavHost (main)
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

MainActivity
   ↓
RootNavGraph (ONLY NavHost)
   ↓
 ├── AuthGraph
 └── MainGraph
        ↓
     MainScreen (UI ONLY)
        ↓
     Dest.Home / Cart / Profile handled in MainGraph


🧠 6. Why We Pass Navigator Everywhere?

You asked:

“we pass navigator everywhere so we can navigate — is that logic?”

👉 YES — and it’s correct.

Think like this:
AppNavigator = Coordinator (SwiftUI)



Yes — single MainGraph is correct
✔ Yes — pass navigator everywhere
✔ No — Compose doesn’t use main.home like SwiftUI
✔ Yes — naming should be mainGraph, not mainNavigation
 */


/**************** VVIMP **************************/
/*

“MainScreen has Scaffold → all child screens follow padding”

wo CLEAN Patterns (pick ONE and stay consistent)
✅ ✅ Pattern 1 (MOST recommended)

👉 MainScreen owns layout (TopBar + BottomBar)
👉 Screens are PURE content

✔ Structure:
MainScreen (Scaffold)
 ├── TopAppBar ✅
 ├── BottomBar ✅
 └── Content (Home/Profile/etc)
✔ Then:
❌ Remove TopAppBar from HomeScreen
❌ Remove TopAppBar from ProfileScreen
 */



/**
 * =========================================================
 * MAIN SCREEN - FINAL MODERN PRODUCTION ARCHITECTURE
 * =========================================================
 *
 * WHY THIS STRUCTURE?
 *
 * ✅ Single source of truth
 * ✅ Route based architecture
 * ✅ Clean separation
 * ✅ Pure UI screens
 * ✅ Easy navigation
 * ✅ Easy testing
 * ✅ Easy previews
 * ✅ Scalable
 * ✅ Production ready
 * ✅ Modern Compose architecture
 *
 * FLOW:
 *
 * MainScreen
 *    ↓
 * TabContentHost
 *    ↓
 * HomeRoute
 *    ↓
 * HomeScreen
 *    ↓
 * Components
 *
 */





/**
 * =========================================================
 * MAIN SCREEN :---> Tab View / Home Screen Journey ,
 * =========================================================
 */



/*
AppNavHost
│
├── AuthGraph
│
├── SplashGraph
│
│
└── MainGraph
      │
      └── MainScreen
             │
             ├── Scaffold
             │
             ├── BottomBar
             │
             └── NavHost
                     │
                     ├── HomeGraph
                     │      ├── Home
                     │      ├── Profile
                     │      └── Bosspedia
                     │
                     ├── ClaimGraph
                     │      ├── Claim List
                     │      └── Claim Detail
                     │
                     ├── VaultGraph
                     │      ├── Vault
                     │      └── Policy Detail
                     │
                     └── PrivilegeGraph
                            ├── Privilege
                            ├── JoinPrivilege
                            ├── Quiz
                            └── RewardDetail
// */

/*
Don't put BottomNavItem in the Graph: It's just configuration data.Don't put NavController in the Graph:
 Always inject it from the MainScreen (the parent) into the child graph functions as a parameter.
  The Tab Bar needs the controller: The onItemSelected lambda in your CustomBottomNavigationBar must
   call navController.navigate(...) to actually perform the action.  By passing the navController as an argument to homeGraph, claimGraph, etc.,
you ensure every screen has a handle on the same "engine" without creating new ones.
 */
//@Composable
//fun MainScreen(
//    appNavigator: AppNavigator
//) {
//
//    /**
//     * =====================================================
//     * TAB NAV CONTROLLER
//     * =====================================================
//     */
//    val tabNavController = rememberNavController()
//
//    /**
//     * =====================================================
//     * TAB NAVIGATOR
//     * =====================================================
//     */
//    val tabNavigator = remember(tabNavController) {
//        AppNavigator(tabNavController)
//    }
//
//    /**
//     * =====================================================
//     * CURRENT DESTINATION
//     * =====================================================
//     */
//    val currentBackStackEntry by tabNavController.currentBackStackEntryAsState()
//
//    val currentDestination = currentBackStackEntry?.destination
//
//    /**
//     * =====================================================
//     * BOTTOM NAV ITEMS
//     * =====================================================
//     */
//    val bottomNavItems = remember {
//
//                listOf(
//
//            BottomNavItem(
//                icon =  R.drawable.ic_explore,
//                title = "Explore",
//                destination = Dest.Home,
//
//                ),
//
//            BottomNavItem(
//                icon = R.drawable.ic_claim,
//                title = "Claim Support",
//                destination = Dest.ClaimSupport
//            ),
//
//            BottomNavItem(
//                icon = R.drawable.ic_security,
//                title = "Policy Vault",
//                destination = Dest.PolicyVault
//            ),
//
//            BottomNavItem(
//                icon = R.drawable.ic_privilege,
//                title = "Privilege",
//                destination = Dest.Privilege,
//                preserveOriginalColor = true,
//                iconSize = 28.dp
//            )
//        )
//
//    }
//
//    /**
//     * =====================================================
//     * EXIT DIALOG
//     * =====================================================
//     */
//    val context = LocalContext.current
//
//    var showExitDialog by remember {
//
//        mutableStateOf(false)
//
//    }
//
//    BackHandler {
//
//        showExitDialog = true
//
//    }
//
//    if (showExitDialog) {
//
//        ExitConfirmationDialog(
//
//            onConfirm = {
//
//                (context as? Activity)?.finish()
//                    ?: exitProcess(0)
//
//            },
//
//            onDismiss = {
//
//                showExitDialog = false
//
//            }
//
//        )
//
//    }
//
//    /**
//     * =====================================================
//     * UI
//     * =====================================================
//     */
//    Scaffold(
//
//        bottomBar = {
//
//            CustomBottomNavigationBar(
//
//                items = bottomNavItems,
//
//                currentDestination = currentDestination,
//
//                onTabSelected = { destination ->
//
//                    tabNavigator.navigateToTab(destination)
//
//                }
//
//            )
//
//        }
//
//    ) { padding ->
//
//        MainTabNavHost(
//
//            navController = tabNavController,
//
//            appNavigator = appNavigator,
//
//            padding = padding
//
//        )
//
//    }
//
//}
//
//
//
///*
// * =========================================================
// * EXIT CONFIRMATION DIALOG
// * =========================================================
// */
//
//@Composable
//fun ExitConfirmationDialog(
//    onConfirm: () -> Unit,
//    onDismiss: () -> Unit
//) {
//
//    AlertDialog(
//
//        onDismissRequest = onDismiss,
//
//        title = {
//
//            Text(
//                text = "Exit App",
//                style = MaterialTheme.typography.titleLarge
//            )
//        },
//
//        text = {
//
//            Text(
//                text = "Are you sure you want to exit?",
//                style = MaterialTheme.typography.bodyMedium
//            )
//        },
//
//        confirmButton = {
//
//            TextButton(
//
//                onClick = onConfirm,
//
//                colors = ButtonDefaults.textButtonColors(
//                    contentColor = MaterialTheme.colorScheme.error
//                )
//            ) {
//
//                Text("Yes")
//            }
//        },
//
//        dismissButton = {
//
//            TextButton(
//                onClick = onDismiss
//            ) {
//
//                Text("No")
//            }
//        },
//
//        containerColor = MaterialTheme.colorScheme.surface,
//
//        tonalElevation = 6.dp
//    )
//}

/**
 * =========================================================
 * BOTTOM NAV MODEL
 * =========================================================
 */

data class BottomNavItem(

    val title: String,

    val icon: Int,

    val destination: Dest,

    val preserveOriginalColor: Boolean = false,

    val iconSize: Dp = 24.dp
)
// val destination: KClass<out Dest>,

