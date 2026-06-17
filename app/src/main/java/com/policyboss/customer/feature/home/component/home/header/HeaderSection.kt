package com.policyboss.customer.feature.home.component.home.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape

// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.component.home.section.CuratedPoliciesSection
import com.policyboss.customer.feature.home.component.home.earningOpportunitySection.EarningOpportunitySection
import com.policyboss.customer.feature.home.component.home.vaultSection.PolicyVaultSection
import com.policyboss.customer.feature.home.component.home.PromoBannersRow
import com.policyboss.customer.feature.home.component.home.QuickActionsGrid
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.HomeState.HomeUiState
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination
import com.policyboss.customer.feature.home.model.vault.VaultTabIds

@Composable
fun HeaderSection(
    userName: String,
    initials: String,
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit // 1. Accept the callback
) {
    Row(
        // 2. Apply the parent modifier here
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back! $userName",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = "Start insuring smarter with Policy Boss",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
                .clickable { onProfileClick() }, // 3. Trigger the callback on click
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}










// ---------------------------- MAIN PREVIEW ----------------------------

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun HomeScreenPreview() {

    MaterialTheme {

        val mockBanners = listOf(
            EarningBanner(
                "1",
                "Earnings instantly hit your account upon renewal",
                R.drawable.ic_airplane
            ), // Replace with actual preview drawables
            EarningBanner("2", "Help your network save smart, while boosting your income.", R.drawable.ic_launcher_background)
        )

        val mockPromoBanners = listOf(
            PromoBanner(
                id = "renew_earn",
                tagText = "RENEW & EARN",
                title = "Become a ‘Privileged user’ and earn instantly on renewals",
                buttonText = "Complete Setup",
                imageRes = R.drawable.ic_banner1, // Replace with your actual drawable
                isYellowTheme = true, // Triggers the yellow gradient
                destination = BannerDestination.Privilege
            ),
            PromoBanner(
                id = "renew_car",
                tagText = "RENEW CAR INSURANCE",
                title = "Act fast — your ‘Honda Amaze’ protection expires in 21 days",
                buttonText = "Renew Now",
                imageRes = R.drawable.ic_banner2, // Replace with your actual drawable
                isYellowTheme = false, // Triggers the blue gradient
                destination = BannerDestination.PolicyAction(
                    BannerAction.RenewCar
                )

            ),
            PromoBanner(
                id = "build_portfolio_renew",
                tagText = "BUILD YOUR POLICY PORTFOLIO",
                title = "Link and access all your policies in just one click",
                buttonText = "Renew Now",
                imageRes = R.drawable.ic_banner3, // Replace with your actual drawable
                isYellowTheme = false,
                destination = BannerDestination.PolicyAction(
                    BannerAction.RenewLife
                )
            ),
            PromoBanner(
                id = "renew_life",
                tagText = "RENEW LIFE INSURANCE",
                title = "Act fast — your ‘life insurance’ expires in 21 days",
                buttonText = "Renew Now",
                imageRes = R.drawable.ic_banner5, // Replace with your actual drawable
                isYellowTheme = false,
                destination = BannerDestination.PolicyAction(
                    BannerAction.BuildPortfolioRenew
                )
            ),
            PromoBanner(
                id = "build_portfolio_sync",
                tagText = "BUILD YOUR POLICY PORTFOLIO",
                title = "Link and access all your policies in just one click",
                buttonText = "Sync Email",
                imageRes = R.drawable.ic_banner4, // Replace with your actual drawable
                isYellowTheme = false,
                destination = BannerDestination.PolicyAction(
                    BannerAction.BuildPortfolioSync
                )
            )

        )
        // (Optional) You can mock the entire state if your screen needs it
        val mockUiState = HomeUiState(
            earningBanners = mockBanners,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F4F6))
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            HeaderSection(
                userName = mockUiState.userName,
                initials = mockUiState.userInitials,
                modifier = Modifier,
                onProfileClick = {}
            )

            PromoBannersRow(
                banners = mockPromoBanners,
                modifier = Modifier,
                onBannerClick = {}
            )

            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                QuickActionsGrid()

                // 2. Pass the mock list to your section
                EarningOpportunitySection(
                    banners = mockUiState.earningBanners,
                    onJoinPrivilegeClick = {},
                    onBannerClick = {},
                    // Removed the duplicate padding since the parent column already has horizontal = 24.dp
                    modifier = Modifier
                )

                CuratedPoliciesSection()

                PolicyVaultSection(

                    modifier = Modifier,

                    selectedTab = VaultTabIds.MOTOR,

                    policies = HomeDummyData.vaultPolicies,

                    onTabSelected = {},

                    onViewAllClick = {},

                    onRenewClick = {},

                    onViewDetailsClick = {}
                )
            }
        }
    }
}// ---------------------------- INDIVIDUAL PREVIEWS ----------------------------

@Preview(showBackground = true)
@Composable
fun HeaderSectionPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(AppColors.BluePrimary)
                .padding(vertical = 24.dp)
        ) {
            HeaderSection(
                userName = "Rahul",
                initials = "RC",
                modifier = Modifier,
                onProfileClick = {}
            )
        }
    }
}






