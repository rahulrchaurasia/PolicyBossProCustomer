package com.policyboss.customer.navigation


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


//@Composable
//fun AppNavGraph(
//    navController: NavHostController,
//    startDestination: Dest
//) {
//
//    val navigator =
//        remember { AppNavigator(navController) }
//
//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ) {
//
//        composable<Dest.CustomSplash> {
//
//            CustomSplashScreen(
//
//                onTimeout = {
//
//                    navigator.navigateTo(
//                        Dest.AuthGraph
//                    ) {
//
//                        popUpTo<Dest.CustomSplash> {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
//
//        authGraph(navigator)
//
//        mainGraph(navigator)
//    }
//}
