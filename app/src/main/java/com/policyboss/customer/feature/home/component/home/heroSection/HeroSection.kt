package com.policyboss.customer.feature.home.component.home.heroSection

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview

import com.policyboss.customer.feature.home.component.home.PromoBannersRow
import com.policyboss.customer.feature.home.component.home.header.HeaderSection

import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import com.policyboss.customer.R

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.graphics.Brush
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination


private val HeroHeight = 380.dp

@Composable
fun HeroSection(
    userName: String,
    initials: String,
    promoBanners: List<PromoBanner>, // Add this
    onProfileClick: () -> Unit,
    onBannerClick: (PromoBanner) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(HeroHeight) // 380.dp
    ) {
        // =====================================
        // LAYER 1: GRADIENT BACKGROUND (Absolute Back)
        // =====================================
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2E90FA),
                            Color(0xFFCBF4DF)
                        )
                    )
                )
        )

        // =====================================
        // LAYER 2: HEADER & BANNERS (Middle Back)
        // Moved up! This ensures the banners are drawn BEFORE the clouds.
        // =====================================
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(48.dp))

            HeaderSection(
                userName = userName,
                initials = initials,
                onProfileClick = onProfileClick
            )

            Spacer(Modifier.height(24.dp))

            PromoBannersRow(
                banners = promoBanners,
                onBannerClick = onBannerClick
            )
        }

        // =====================================
        // LAYER 3: CLOUDS (Middle Front)
        // Drawn AFTER the banners, so their tops overlap the yellow banner bottom.
        // Updated with exact Figma aspect ratios.
        // =====================================

        // Left Cloud (ic_claude)
        Image(
            painter = painterResource(R.drawable.ic_claude),
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .aspectRatio(371.79f / 154f) // Exact Figma ratio
                .align(Alignment.BottomStart)
                .offset(x = (-55).dp)
                .graphicsLayer { rotationZ = 6f }, // Match Figma rotation
            contentScale = ContentScale.FillWidth
        )

        // Right Cloud (ic_claude1)
        Image(
            painter = painterResource(R.drawable.ic_claude1),
            contentDescription = null,
            modifier = Modifier
                .width(270.dp)
                .aspectRatio(359.07f / 148.73f) // Exact Figma ratio
                .align(Alignment.BottomEnd)
                .offset(x = 25.dp)
                .graphicsLayer { rotationZ = 2f },
            contentScale = ContentScale.FillWidth
        )

        // =====================================
        // LAYER 4: WHITE CURVE OVERLAY (Absolute Front)
        // Drawn very last so it cleanly cuts off the flat bottoms of the clouds.
        // =====================================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                )
                .background(Color.White)
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HeroSectionPreview() {

    MaterialTheme {

        val mockBanners = listOf(
            PromoBanner(
                id = "renew_earn",
                tagText = "RENEW & EARN",
                title = "Become a ‘Privileged user’ and earn instantly on renewals",
                buttonText = "Complete Setup",
                imageRes = R.drawable.ic_launcher_background, // Replace with your actual drawable, e.g., ic_car_money_shield
                isYellowTheme = true,
                destination = BannerDestination.PolicyAction(
                    BannerAction.RenewCar
                )
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
        Box(
            modifier = Modifier.background(Color.White)
        ) {

            HeroSection(
                userName = "Rahul",
                initials = "RC",
               promoBanners = mockBanners,
                onProfileClick = {},
                onBannerClick = {}
            )
        }
    }
}