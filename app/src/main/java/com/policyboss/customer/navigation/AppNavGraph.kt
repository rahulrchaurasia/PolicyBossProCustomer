package com.policyboss.customer.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.policyboss.customer.navigation.graphs.authGraph
import com.policyboss.customer.navigation.graphs.mainGraph
import com.policyboss.customer.ui.CustomSplashScreen

/*


                    App Launch
                         │
                         ▼
                  RootViewModel
                         │
                Check Session
                         │
           ┌─────────────┴─────────────┐
           │                           │
           ▼                           ▼
     Not Logged In               Logged In
           │                           │
           ▼                           ▼
     CustomSplash                 MainGraph
           │                           │
           ▼                           ▼
       AuthGraph                    Home
           │
           ▼
       Welcome
           │
           ▼
        Login
           │
           ▼
          OTP
 */


@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: Dest
) {

    val navigator =
        remember { AppNavigator(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Dest.CustomSplash> {

            CustomSplashScreen(

                onTimeout = {

                    navigator.navigateTo(
                        Dest.AuthGraph
                    ) {

                        popUpTo<Dest.CustomSplash> {
                            inclusive = true
                        }
                    }
                }
            )
        }

        authGraph(navigator)

        mainGraph(navigator)
    }
}

//@Composable
//fun AppNavGraph(
//    navController: NavHostController,
//    startDestination: Dest
//) {
//   // val isLoggedIn by appDataManager.isLoggedIn.collectAsState()
//    val navigator = remember { AppNavigator(navController) }
//
//    NavHost(
//        navController = navController,
//        startDestination = Dest.Splash // ✅ Object, not string
//    ) {
//        composable<Dest.Splash> {
//            SplashScreen(
//
//                onTimeout = {
////                    val target = if (isLoggedIn) Dest.MainGraph else Dest.AuthGraph
////
////
////                    navigator.navigateTo(target) {
////                        popUpTo<Dest.Splash> { inclusive = true }
////                    }
//
//                    navigator.navigateToWelcome()
//                }
//            )
//        }
//
//
//        // ✅ ADD THIS
//
//
//        // Attach Feature Modules
//        authGraph(navigator)
//        mainGraph(navigator)
//    }
//}