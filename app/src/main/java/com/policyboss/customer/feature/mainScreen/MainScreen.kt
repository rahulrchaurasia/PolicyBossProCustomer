package com.policyboss.customer.feature.mainScreen


import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.policyboss.customer.R
import com.policyboss.customer.feature.mainScreen.customBottomNavBar.CustomBottomNavigationBar
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest
import kotlin.system.exitProcess

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
@Composable
fun MainScreen(
    appNavigator: AppNavigator
) {

    /**
     * =====================================================
     * TAB NAV CONTROLLER
     * =====================================================
     */
    val tabNavController = rememberNavController()

    /**
     * =====================================================
     * TAB NAVIGATOR
     * =====================================================
     */
    val tabNavigator = remember(tabNavController) {
        AppNavigator(tabNavController)
    }

    /**
     * =====================================================
     * CURRENT DESTINATION
     * =====================================================
     */
    val currentBackStackEntry by tabNavController.currentBackStackEntryAsState()

    val currentDestination = currentBackStackEntry?.destination

    /**
     * =====================================================
     * BOTTOM NAV ITEMS
     * =====================================================
     */
    val bottomNavItems = remember {

                listOf(

            BottomNavItem(
                icon =  R.drawable.ic_explore,
                title = "Explore",
                destination = Dest.Home,

                ),

            BottomNavItem(
                icon = R.drawable.ic_claim,
                title = "Claim Support",
                destination = Dest.ClaimSupport
            ),

            BottomNavItem(
                icon = R.drawable.ic_security,
                title = "Policy Vault",
                destination = Dest.PolicyVault
            ),

            BottomNavItem(
                icon = R.drawable.ic_privilege,
                title = "Privilege",
                destination = Dest.Privilege,
                preserveOriginalColor = true,
                iconSize = 28.dp
            )
        )

    }

    /**
     * =====================================================
     * EXIT DIALOG
     * =====================================================
     */
    val context = LocalContext.current

    var showExitDialog by remember {

        mutableStateOf(false)

    }

    BackHandler {

        showExitDialog = true

    }

    if (showExitDialog) {

        ExitConfirmationDialog(

            onConfirm = {

                (context as? Activity)?.finish()
                    ?: exitProcess(0)

            },

            onDismiss = {

                showExitDialog = false

            }

        )

    }

    /**
     * =====================================================
     * UI
     * =====================================================
     */
    Scaffold(

        bottomBar = {

            CustomBottomNavigationBar(

                items = bottomNavItems,

                currentDestination = currentDestination,

                onTabSelected = { destination ->

                    tabNavigator.navigateToTab(destination)

                }

            )

        }

    ) { padding ->

        MainTabNavHost(

            navController = tabNavController,

            appNavigator = appNavigator,

            padding = padding

        )

    }

}


//******************************************************************
  //region Comment Old
//@Composable
//fun MainScreen(
//    appNavigator: AppNavigator
//) {
//
//    val context = LocalContext.current
//
//    var showExitDialog by remember {
//        mutableStateOf(false)
//    }
//
//    /**
//     * =====================================================
//     * SINGLE SOURCE OF TRUTH
//     * =====================================================
//     */
//    var selectedTab by remember {
//        mutableStateOf<Dest>(Dest.Home)
//    }
//
//    /**
//     * =====================================================
//     * BOTTOM NAV ITEMS
//     * =====================================================
//     */
//    val navItems = remember {
//
//        listOf(
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
//    }
//
//    val selectedIndex = navItems.indexOfFirst {
//        it.destination == selectedTab
//    }
//
//    /**
//     * =====================================================
//     * BACK HANDLER
//     * =====================================================
//     *
//     * IF USER IS NOT ON HOME:
//     * -> RETURN TO HOME
//     *
//     * IF USER IS ALREADY ON HOME:
//     * -> SHOW EXIT DIALOG
//     *
//     */
//    BackHandler {
//
//        if (selectedTab != Dest.Home) {
//
//            selectedTab = Dest.Home
//
//        } else {
//
//            showExitDialog = true
//        }
//    }
//
//    /**
//     * =====================================================
//     * EXIT DIALOG
//     * =====================================================
//     */
//    if (showExitDialog) {
//
//        ExitConfirmationDialog(
//
//            onConfirm = {
//
//                (context as? Activity)?.finish()
//                    ?: exitProcess(0)
//            },
//
//            onDismiss = {
//
//                showExitDialog = false
//            }
//        )
//    }
//
//    /**
//     * =====================================================
//     * MAIN SCAFFOLD
//     * =====================================================
//     */
//    Scaffold(
//
//        bottomBar = {
//
//            CustomBottomNavigationBar(
//
//                items = navItems,
//
//                selectedIndex = selectedIndex,
//
//                onItemSelected = { index ->
//
//                    if (index != selectedIndex) {
//
//                        selectedTab = navItems[index].destination
//                    }
//                }
//            )
//        }
//
//    ) { padding ->
//
//        /**
//         * =================================================
//         * TAB CONTENT HOST
//         * =================================================
//         */
//        TabContentHost(
//
//            selectedTab = selectedTab,
//
//            appNavigator = appNavigator,
//            padding = padding,
//
//            )
//    }
//}

//endregion


/*
 * =========================================================
 * EXIT CONFIRMATION DIALOG
 * =========================================================
 */

@Composable
fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(
                text = "Exit App",
                style = MaterialTheme.typography.titleLarge
            )
        },

        text = {

            Text(
                text = "Are you sure you want to exit?",
                style = MaterialTheme.typography.bodyMedium
            )
        },

        confirmButton = {

            TextButton(

                onClick = onConfirm,

                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {

                Text("Yes")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text("No")
            }
        },

        containerColor = MaterialTheme.colorScheme.surface,

        tonalElevation = 6.dp
    )
}

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

