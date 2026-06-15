package com.policyboss.customer.feature.onboarding.ui.na

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.core.extension.floatingAnimation
import com.policyboss.customer.feature.onboarding.component.ContactSyncSection
import com.policyboss.customer.feature.onboarding.component.EarningsCard
import com.policyboss.customer.feature.onboarding.component.FeatureTag


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size


import coil.compose.AsyncImage


import androidx.compose.ui.zIndex


// ============================================
// CUSTOM LAST SCREEN (THE SCATTERED UI)
// ============================================

//@Composable
//fun OnboardingLastScreenContent() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    listOf(Color(0xFF2E90FA), Color(0xFFCBF4DF))
//                )
//            )
//    ) {
//        // We use a Box to scatter the elements freely but relative to the screen dimensions
//
//        // 1. Top Right: Zero Hassle Pill
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(top = 120.dp, end = 24.dp)
//                .floatingAnimation(delayStart = 0)
//        ) {
//            FeatureTag(text = "Zero hassle, Instant pay", icon = R.drawable.ic_wallet)
//        }
//
//        // 2. Middle Left: Contact Syncing Group
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .padding(top = 220.dp, start = 24.dp)
//                .floatingAnimation(delayStart = 500) // Slight delay so they don't bounce identically
//        ) {
//            ContactSyncSection()
//        }
//
//        // 3. Middle Right/Center: Earnings Card
//        Box(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .padding(top = 80.dp) // Pushed down slightly below center
//                .fillMaxWidth(0.85f)
//                .floatingAnimation(delayStart = 1000)
//        ) {
//            EarningsCard()
//
//            // Nested Real-time earnings pill attached to the bottom of the card
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .offset(y = 16.dp)
//            ) {
//                FeatureTag(text = "Real-time earnings", icon = R.drawable.star_circle)
//            }
//        }
//    }
//}

@Composable
fun OnboardingLastScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2E90FA), Color(0xFFCBF4DF))
                )
            )
    ) {
        // 1. Top Right: Wallet Illustration + Zero Hassle Pill
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 40.dp)
                .floatingAnimation(1500, 0)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = R.drawable.ic_wallet, // Your wallet/coin resource
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
                FeatureTag(text = "Zero hassle, Instant pay")
            }
        }

        // 2. Middle Left: Contact Syncing Group
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 30.dp, bottom = 100.dp)
                .floatingAnimation(1800, 500)
        ) {
            ContactSyncSection()
        }

        // 3. Center/Bottom: Earnings Card with Overlapping Pill
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 260.dp)
                .floatingAnimation(2000, 1000)
        ) {
            // Main Card
            EarningsCard()

            // Overlapping Pill (Bottom Right of the card)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 10.dp, y = 15.dp)
                    .zIndex(2f)
            ) {
                FeatureTag(text = "Real-time earnings")
            }
        }
    }
}