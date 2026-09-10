package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.ClaimTab
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.state.ClaimAction

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model.state.ClaimUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.claimListSection.MyClaimsListSection
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component.ClaimHeader
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component.ClaimSegmentedControl
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component.ClaimSupportMenuSection
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component.MyClaimsEmptyState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.ui.component.NeedHelpFooter
import com.policyboss.customer.feature.tabfeatures.policyVault.ui.policyVaultScreen.bottomSheet.AddPolicyBottomSheet
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme
import com.policyboss.customer.ui.theme.gradients.AppGradients

private val FooterReservedHeight = 92.dp
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClaimSupportScreen(
    uiState: ClaimUiState,
    contentPadding: PaddingValues,
    onAction: (ClaimAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    //----------------------------------------------------------
    // Root Container
    // Holds the entire screen and floating footer.
    //----------------------------------------------------------

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppGradients.ScreenSurfaceGradient)
    ) {

        //----------------------------------------------------------
        // Main Screen Content
        //----------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                // 1. ADD SCROLLING HERE
               // .verticalScroll(scrollState)
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = contentPadding.calculateTopPadding() + 24.dp,

                    // Reserve space for bottom footer
                    //Keeps the screen content from being hidden behind the floating footer.
                     bottom = contentPadding.calculateBottomPadding() + FooterReservedHeight
                )
        ) {

            //------------------------------------------------------
            // Screen Title
            //------------------------------------------------------

            //Header()
            ClaimHeader(
                showDeleteMenu = uiState.selectedTab == ClaimTab.MY_CLAIMS && uiState.myClaims.isNotEmpty(),
                onDeleteAllClick = { onAction(ClaimAction.OnDeleteAllClaims) }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            //------------------------------------------------------
            // Segmented Control
            //------------------------------------------------------

            ClaimSegmentedControl(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(
                        ClaimAction.OnTabSelected(tab)
                    )
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )


            //------------------------------------------------------
            // Animated Tab Content
            //------------------------------------------------------

            AnimatedClaimContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                uiState = uiState,          // 🚀 PASS IT HERE
                selectedTab = uiState.selectedTab,
                onAction = onAction
            )
        }

        //----------------------------------------------------------
        // Sticky Bottom Footer
        //----------------------------------------------------------

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            HorizontalDivider(
                color = AppColors.BorderSecondary
            )

            NeedHelpFooter(
                modifier = Modifier
                    .fillMaxWidth()
                    // Keep your padding here so it pushes up from the bottom nav bar
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 0.dp, // Adds a little breathing room below the divider
                        bottom = contentPadding.calculateBottomPadding() + 8.dp
                    ),
                onClick = {
                    onAction(ClaimAction.OnSupportCallClick)
                }
            )
        }
    } // End of Box


    //--------------------------------------------------------------
    // Product Selection Bottom Sheet
    //--------------------------------------------------------------

    if (uiState.productSelectionContext != null) {
        AddPolicyBottomSheet(
            title = "Select Product",
            onDismissRequest = {
                onAction(ClaimAction.OnDismissBottomSheet)
            },
            onOptionSelected = { product ->
                // Now passing the Enum type directly
                onAction(ClaimAction.OnProductSelected(product))
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedClaimContent(
    uiState: ClaimUiState, // 🚀 ADDED THIS
    selectedTab: ClaimTab,
    onAction: (ClaimAction) -> Unit,
    modifier: Modifier = Modifier
) {

    AnimatedContent(
        targetState = selectedTab,
        modifier = modifier,
        transitionSpec = {

            val animationSpec = tween<IntOffset>(
                durationMillis = 300
            )

            if (targetState.ordinal > initialState.ordinal) {

                slideInHorizontally(
                    animationSpec = animationSpec,
                    initialOffsetX = { it / 4 }
                ) + fadeIn(
                    animationSpec = tween(300)
                ) togetherWith

                        slideOutHorizontally(
                            animationSpec = animationSpec,
                            targetOffsetX = { -it / 4 }
                        ) + fadeOut(
                    animationSpec = tween(250)
                )

            } else {

                slideInHorizontally(
                    animationSpec = animationSpec,
                    initialOffsetX = { -it / 4 }
                ) + fadeIn(
                    animationSpec = tween(300)
                ) togetherWith

                        slideOutHorizontally(
                            animationSpec = animationSpec,
                            targetOffsetX = { it / 4 }
                        ) + fadeOut(
                    animationSpec = tween(250)
                )
            }
        },
        label = "ClaimContentAnimation"
    ) { tab ->

        when (tab) {

            ClaimTab.MY_CLAIMS -> {

//                MyClaimsEmptyState(
//                    onFileClaimClick = {
//                        onAction(
//                            ClaimAction.OnFileClaimClick
//                        )
//                    }
//                )

                // Check if the list is empty
                if (uiState.myClaims.isEmpty()) {
                    MyClaimsEmptyState(
                        onFileClaimClick = {
                            onAction(ClaimAction.OnFileClaimClick)
                        }
                    )
                } else {
                    // Show the populated list!
                    MyClaimsListSection(
                        claims = uiState.myClaims,
                        onFileClaimClick = {
                            onAction(ClaimAction.OnFileClaimClick)
                        },
                        onDeleteClaim = { claimId ->

                            onAction(ClaimAction.OnDeleteSingleClaim(claimId))

                        }
                    )
                }
            }

            ClaimTab.CLAIM_SUPPORT -> {

                ClaimSupportMenuSection(

                    onAction = onAction
                )
            }
        }
    }
}

@Composable
private fun Header() {

    Column(
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Claims",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "My Claims"
)
@Composable
private fun MyClaimsPreview() {

    PolicyBossCustomerTheme {

        ClaimSupportScreen(
            uiState = ClaimUiState(
                selectedTab = ClaimTab.MY_CLAIMS
            ),
            contentPadding = PaddingValues(),
            onAction = {}
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Claim Support"
)
@Composable
private fun ClaimSupportPreview() {

    PolicyBossCustomerTheme {

        ClaimSupportScreen(
            uiState = ClaimUiState(
                selectedTab = ClaimTab.CLAIM_SUPPORT
            ),
            contentPadding = PaddingValues(),
            onAction = {}
        )
    }
}

