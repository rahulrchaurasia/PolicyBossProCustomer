package com.policyboss.customer.navigation.graphs


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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.policyboss.customer.R
import com.policyboss.customer.feature.mainScreen.customBottomNavBar.BottomNavItem
import com.policyboss.customer.feature.mainScreen.customBottomNavBar.CustomBottomNavigationBar
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest
import com.policyboss.customer.ui.CustomSplashScreen
import kotlin.system.exitProcess

// 📁 AppRoot.kt (This replaces AppNavGraph and MainScreen completely)

/*
NavHostController = the real engine. It can do everything: navigate, pop, read back stack, get back stack entries, access saved state — all of it.
AppNavigator = a wrapper you built around that engine. It doesn't have new power of its own — it just holds a private reference to the real NavController and exposes only the specific pieces you decide screens are allowed to use (navigateTo, navigateBack, navigateToTab, etc.).


 */
/*

Q: Why did we need NavHostController in the first place?
Because one specific thing — sharing a ViewModel between Dest.Privilege
 and Dest.PrivilegeStories — requires calling getBackStackEntry<Dest.PrivilegeGraph>(). That method exists only on NavController/NavHostController, not anywhere else. So to do that one thing, privilegeGraph had to receive the controller as a parameter.

Q: What's the problem with just passing it in directly?
NavHostController can do everything — navigate anywhere, pop anything, read the whole app's back stack.
 Handing the whole thing to privilegeGraph gives it far more power than it actually needs (it only needed that one method), and it opens the door for anyone to bypass AppNavigator and call navController.navigate(...) directly — which is exactly what caused the repeated crashes earlier in this thread (wrong controller, wrong tree).

Q: So how do we resolve it using only AppNavigator?
Give AppNavigator its own version of that one method, so it wraps it instead of exposing the whole controller:
kotlin class AppNavigator(private val navController: NavController) {

    inline fun <reified T : Any> getBackStackEntry(): NavBackStackEntry {
        return navController.getBackStackEntry<T>()
    }

    // ... your other existing methods
}

Q: Then what changes in privilegeGraph?
kotlin// Before
navController.getBackStackEntry<Dest.PrivilegeGraph>()

// After
appNavigator.getBackStackEntry<Dest.PrivilegeGraph>()
Same result. navController parameter is no longer needed anywhere in the function.
Q: What's the one-sentence rule to remember?
Whenever a screen needs a new capability from NavController,
 add a small method for it on AppNavigator — never pass the raw controller down just to get that one thing.
*/
@Composable
fun AppRoot(
    navController: NavHostController,
    startDestination: Dest
) {
    val appNavigator = remember(navController) { AppNavigator(navController) }

    val currentDestination = appNavigator.currentDestination()





    // 1. Single Source of Truth for Tab Data
    val bottomNavItems = remember {
        listOf(
            BottomNavItem(
                icon = R.drawable.ic_explore,
                title = "Explore",
                graphDestination = Dest.HomeGraph,
                rootScreen = Dest.Home::class
            ),
            BottomNavItem(
                icon = R.drawable.ic_claim,
                title = "Claim Support",
                graphDestination = Dest.ClaimGraph,
                rootScreen = Dest.ClaimSupport::class
            ),
            BottomNavItem(
                icon = R.drawable.ic_security,
                title = "Policy Vault",
                graphDestination = Dest.VaultGraph,
                rootScreen = Dest.PolicyVault::class
            ),
            BottomNavItem(
                icon = R.drawable.ic_privilege,
                title = "Privilege",
                graphDestination = Dest.PrivilegeGraph,
                rootScreen = Dest.Privilege::class,
                preserveOriginalColor = true,
                iconSize = 28.dp
            )
        )
    }

//    val shouldShowBottomBar = bottomNavItems.any { item ->
//        currentDestination?.hasRoute(item.destination::class) == true
//    }

    // 2. VISIBILITY LOGIC (Leaf Match Only)
    // Checks if the current leaf destination exactly matches any of our root screens.
    // E.g., If we are on Dest.AddManualPolicy, this returns false.



    /**
     * Show BottomBar only on the root screen of each tab.
     *
     * Visible:
     * Home
     * ClaimSupport
     * PolicyVault
     * Privilege
     *
     * Hidden:
     * Profile
     * AddManualPolicy
     * PrivilegeStories
     * JoinPrivilege
     */
    val shouldShowBottomBar = bottomNavItems.any { item ->
        currentDestination?.route == item.rootScreen.qualifiedName
    }


    val context = LocalContext.current
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        if (currentDestination?.hasRoute<Dest.Home>() == true) {
            showExitDialog = true
        } else {
            appNavigator.navigateBack()
        }
    }

    if (showExitDialog) {
        ExitConfirmationDialog(
            onConfirm = { (context as? Activity)?.finish() ?: exitProcess(0) },
            onDismiss = { showExitDialog = false }
        )
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                CustomBottomNavigationBar(
                    items = bottomNavItems,
                    currentDestination = currentDestination,
                    onTabSelected = { destination -> appNavigator.navigateToTab(destination) }
                )
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = startDestination   // dynamic — decided by RootViewModel
        ) {

            composable<Dest.CustomSplash> {
                CustomSplashScreen(
                    onTimeout = {
                        appNavigator.navigateTo(Dest.AuthGraph) {
                            popUpTo<Dest.CustomSplash> { inclusive = true }
                        }
                    }
                )
            }

            authGraph(appNavigator)

            mainGraph(
               // navController = navController,
                navigator = appNavigator,
                padding = padding
            )
        }
    }
}

// * =========================================================
// * EXIT CONFIRMATION DIALOG
// * =========================================================
// */

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