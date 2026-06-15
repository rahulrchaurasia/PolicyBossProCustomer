package com.policyboss.customer.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.policyboss.customer.anim.NavigationAnimations
import com.policyboss.customer.feature.login.model.verifyAccount.VerifyOtpSource
import com.policyboss.customer.feature.login.ui.CreateAccountScreen
import com.policyboss.customer.navigation.AppNavigator

import com.policyboss.customer.navigation.Dest
import com.policyboss.customer.feature.onboarding.ui.OnboardingPagerScreen

import com.policyboss.customer.feature.login.ui.LoginScreen
import com.policyboss.customer.feature.login.ui.VerifyAccountScreen

/*
Current Navigation Architecture
1. Root Navigation (Global)
kotlinRootNavGraph -> NavHost with globalActions
├── Splash
├── Auth (Welcome, Login, Register)
└── MainHome (Contains bottom navigation)
2. Bottom Navigation (Local)
kotlinMainApp -> Manual tab switching with selectedIndex
├── Home
├── WishList
├── Cart
└── Profile
 */


fun NavGraphBuilder.authGraph(
    navigator: AppNavigator,

) {

    navigation<Dest.AuthGraph>(   // ✅ THIS WAS MISSING
        startDestination = Dest.Welcome
    ) {
        // Welcome Screen
        composable<Dest.Welcome>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft }
        ) {

            OnboardingPagerScreen(
                onFinish = {
                    navigator.navigateToLogin()
                }
            )
        }

        // ✅ ADD THIS - Login Screen (This was missing!)
        composable<Dest.Login>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft }
        ) {


            LoginScreen(

                onNavigateToRegister = {

                    navigator.navigateToRegister()
                },

                onNavigateToVerifyOtp = {
                        mobileNumber,
                        source ->

                    navigator.navigateTo(

                        Dest.VerifyAccount(

                            fullName = "",

                            mobileNumber = mobileNumber,

                            source = source
                        )
                    )
                }
            )
        }


        composable<Dest.Register>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft }
        ) {

//            CreateAccountScreen(
//                onLoginClick =  {
//
//                    navigator.navigateBack()
//                },
//                onSendOtpClick = {
//
//                    navigator.navigateTo(Dest.VerifyAccount)
//                }
//            )

            CreateAccountScreen(

                onLoginClick = {

                    navigator.navigateBack()
                },

                onNavigateToVerifyOtp = { fullName, mobileNumber ,source ->

                    navigator.navigateTo(

                        Dest.VerifyAccount(

                            fullName = fullName,

                            mobileNumber = mobileNumber,

                            source = source  // ← comes from event now
                        )
                    )
                }
            )

        }

        composable<Dest.VerifyAccount>(
            enterTransition = { NavigationAnimations.slideInRight },
            exitTransition = { NavigationAnimations.slideOutLeft }
        ) {


            val args =
                it.toRoute<Dest.VerifyAccount>()

            VerifyAccountScreen(

                navigator = navigator,

                fullName = args.fullName,

                mobileNumber = args.mobileNumber,

                source = args.source
            )
        }




    }



}