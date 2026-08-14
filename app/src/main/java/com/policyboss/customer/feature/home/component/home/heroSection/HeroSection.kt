package com.policyboss.customer.feature.home.component.home.heroSection




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.component.home.PromoBannersRow
import com.policyboss.customer.feature.home.component.home.header.HeaderSection
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination

/*
1. It Draws First (The Layering): Because the HeroSection is written first inside the scaffold's box, it becomes the bottom layer (Layer 1). When the LazyColumn draws after it, the list is placed on the layer above it (Layer 2). This means your cards naturally slide over the white curve instead of getting trapped underneath it.

2. The Scaffold Controls the Math: Instead of the HeroSection trying to guess its own height (and missing the status bar gap), the CollapsingScaffold acts as the single source of truth. It calculates the exact, pixel-perfect height (currentHeaderHeight) and forces the HeroSection to match it perfectly on every single frame of the scroll.

Because the HeroSection obeys the exact height given by the Scaffold, and because it sits on the bottom layer, everything aligns completely flush and the clipping bug is physically impossible!
 */
@Composable
fun HeroSection(
    userName: String,
    initials: String,
    promoBanners: List<PromoBanner>,
    onProfileClick: () -> Unit,
    onBannerClick: (PromoBanner) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        // 🚀 We use the modifier passed from the Scaffold so the height tracks perfectly.
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds() // ✅ Safely clips contents as the parent height shrinks during scroll
    ) {

        // =====================================================
        // LAYER 1: GRADIENT BACKGROUND
        // =====================================================
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

        // =====================================================
        // LAYER 2: HEADER + PROMO BANNERS
        // =====================================================
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            HeaderSection(
                userName = userName,
                initials = initials,
                onProfileClick = onProfileClick
            )

            Spacer(modifier = Modifier.height(24.dp))

            PromoBannersRow(
                banners = promoBanners,
                onBannerClick = onBannerClick
            )
        }

        // =====================================================
        // LAYER 3: CLOUDS
        // =====================================================
        Image(
            painter = painterResource(R.drawable.ic_claude),
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .aspectRatio(371.79f / 154f) // Exact Figma ratio
                .align(Alignment.BottomStart)
                .offset(x = (-55).dp)
                .graphicsLayer { rotationZ = 6f },
            contentScale = ContentScale.FillWidth
        )

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

        // =====================================================
        // LAYER 4: WHITE CURVE
        // ✅ Locked to the bottom of the container.
        // Sized exactly at 56.dp to cover the flat bottoms of the clouds.
        // =====================================================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
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