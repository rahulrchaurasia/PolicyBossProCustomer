package com.policyboss.customer.feature.home.component.home


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.component.home.card.BannerCard
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.WarningYellow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.policyboss.customer.feature.home.model.PromoBanner

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme

import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination

//========================================
//  Its horizontal scroll only

@Composable
fun PromoBannersRow(
    banners: List<PromoBanner>, // 1. Accept the list of banners
    modifier: Modifier = Modifier,
    //onBannerClick: (String) -> Unit
    onBannerClick: (PromoBanner) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp), // Handles start/end spacing cleanly
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = banners,
            key = { it.id } // Optimizes recomposition
        ) { banner ->

            // 2. Dynamically assign colors based on the theme flag
            // 2. DYNAMICALLY assign BOTH gradient and text colors!
            val (gradientColors, dynamicTextColor) = if (banner.isYellowTheme) {
                Pair(
                    listOf(WarningYellow, AppColors.GoldBackground),
                    AppColors.GoldText // Dark text for the yellow card

                )
            } else {
                Pair(
                    listOf(AppColors.BlueGradientDark, AppColors.BlueGradientLight),
                    Color.White // White text for the blue cards
                )
            }

            BannerCard(
                banner = banner,
                gradientColors = gradientColors,
                textColor = dynamicTextColor,
                onBannerClick = onBannerClick
            )
        }
    }
}



// Make sure your R class and PromoBanner data class are imported
// import com.policyboss.customer.R
// import com.policyboss.customer.model.PromoBanner

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 400)
@Composable
fun PromoBannerPreview() {
    MaterialTheme {

        // 1. Create a dummy list of banners just for the preview
        val mockBanners = listOf(
            PromoBanner(
                id = "renew_earn",
                tagText = "RENEW & EARN",
                title = "Become a ‘Privileged user’ and earn instantly on renewals",
                buttonText = "Complete Setup",
                imageRes = R.drawable.ic_launcher_background, // Replace with your actual drawable, e.g., ic_car_money_shield
                isYellowTheme = true,
                destination = BannerDestination.Privilege
            ),
            PromoBanner(
                id = "build_portfolio",
                tagText = "BUILD YOUR POLICY PORTFOLIO",
                title = "Link and access all your policies in just one click",
                buttonText = "Sync Email",
                imageRes = R.drawable.ic_launcher_background, // Replace with your actual drawable, e.g., ic_shield_bell
                isYellowTheme = false,
                destination = BannerDestination.PolicyAction(
                    BannerAction.RenewCar
                )
            )
        )

        // 2. Pass the mock list into your composable
        PromoBannersRow(
            banners = mockBanners,
            modifier = Modifier.padding(vertical = 16.dp), // Added vertical padding so the drop shadows (if any) aren't clipped in the preview
            onBannerClick = {}
        )
    }
}