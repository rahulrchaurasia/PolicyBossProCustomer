package com.policyboss.customer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.policyboss.customer.core.LocalNavController
import com.policyboss.customer.navigation.AppNavGraph
import com.policyboss.customer.root.RootViewModel
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import dagger.hilt.android.AndroidEntryPoint

/*
##################### Graphical Representation #####################
com.example.myapp
│
├── MainActivity.kt
│
├── data/
│   ├── remote/
│   ├── local/
│   ├── repository/
│
├── domain/
│   ├── model/
│   ├── repository/
│   ├── usecase/
│
├── navigation/
│   ├── Dest.kt
│   ├── AppNavigator.kt
│   ├── RootNavGraph.kt
│
├── presentation/
│   │
│   ├── main/
│   │   ├── MainScreen.kt
│   │   ├── BottomNavItem.kt
│   │
│   ├── home/
│   │   ├── HomeRoute.kt
│   │   ├── HomeScreen.kt
│   │   ├── HomeViewModel.kt
│   │   ├── HomeUiState.kt
│   │   ├── HomeAction.kt
│   │   │
│   │   ├── model/
│   │   │   ├── BannerUi.kt
│   │   │   ├── QuickActionUi.kt
│   │   │   ├── ArticleUi.kt
│   │   │   ├── PolicyUi.kt
│   │   │
│   │   ├── components/
│   │   │   ├── HeaderSection.kt
│   │   │   ├── PromoBannerCarousel.kt
│   │   │   ├── BannerCard.kt
│   │   │   ├── QuickActionsGrid.kt
│   │   │   ├── QuickActionCard.kt
│   │   │   ├── CuratedPoliciesSection.kt
│   │   │   ├── PolicyCategoryItem.kt
│   │   │   ├── PolicyVaultSection.kt
│   │   │   ├── BosspediaSection.kt
│   │   │   ├── ArticleCard.kt
│   │   │   ├── FooterTrustSection.kt
│   │   │   ├── EmptyPolicyVault.kt
│   │
│   ├── profile/
│   ├── cart/
│   ├── wishlist/
│
├── ui/
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Typography.kt
│   │   ├── Theme.kt
│   │   ├── Dimens.kt
│   │
│   ├── components/
│   │   ├── PBToolbar.kt
│   │   ├── PBButton.kt
│   │   ├── PBLoading.kt
│   │   ├── PBCard.kt
│   │   ├── PBEmptyState.kt
│
├── utils/
│   ├── Extensions.kt
│   ├── Constants.kt
│
├── di/
│   ├── AppModule.kt
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val rootViewModel: RootViewModel =
                hiltViewModel()

            val uiState by rootViewModel.uiState
                .collectAsStateWithLifecycle()

            splashScreen.setKeepOnScreenCondition {
                uiState.isLoading
            }

            uiState.startDestination?.let { startDestination ->

                PolicyBossCustomerTheme {

                    val navController =
                        rememberNavController()
                     // remark : below
                    CompositionLocalProvider(
                        LocalNavController provides navController
                    ) {

                        AppNavGraph(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }

        }
    }
}

/*
LocalNavController provides navController means:

"Store this navController in the Compose tree so that any child composable
can access it without passing it as a parameter."
 */
