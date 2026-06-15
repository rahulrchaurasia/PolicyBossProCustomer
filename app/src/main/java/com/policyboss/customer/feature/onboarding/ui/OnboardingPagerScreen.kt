package com.policyboss.customer.feature.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale



import androidx.compose.material3.*

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.components.card.BottomActionCard

import androidx.compose.runtime.rememberCoroutineScope

import kotlinx.coroutines.launch

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush

import androidx.compose.foundation.Image
import com.policyboss.customer.feature.onboarding.model.OnboardingPage

@Composable
fun rememberOnboardingPages(): List<OnboardingPage> {
    return remember {
        listOf(
            OnboardingPage(
                imageResId = R.drawable.onboarding1,
                title = "Earn from your renewals \n — and your network's too. "),
            OnboardingPage(
                imageResId = R.drawable.onboarding2,
                title = "Access all your policies \n in one place."),
            OnboardingPage(
                imageResId = R.drawable.onboarding3,
                title = "Experience our exclusive India-first features ",

            )
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerScreen(
    onFinish: () -> Unit
) {
    val pages = rememberOnboardingPages()
    val pagerState = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {

        // ✅ Background + Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->

            val page = pages[pageIndex]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF2E90FA),
                                Color(0xFFCBF4DF)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center // 👈 KEY FIX
            ) {

                if (page.content != null) {
                    page.content.invoke()
                } else {
                    Image(
                        painter = painterResource(id = page.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                            .offset(y = (-25).dp)

                    )




                }
            }
        }

        // ✅ Clean Header
        HeaderSection(
            onSkipClick = onFinish,
            showSkip = pagerState.currentPage != pages.lastIndex
        )

        // ✅ Bottom Card
        BottomActionCard(
            page = pages[pagerState.currentPage],
            pageIndex = pagerState.currentPage,
            totalPages = pages.size,
            onContinue = {
                if (pagerState.currentPage == pages.lastIndex) {
                    onFinish()
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// ============================================
// HEADER (STATIC)
// ============================================

@Composable
fun HeaderSection(
    onSkipClick: () -> Unit,
    showSkip: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        if (showSkip) {
            Text(
                text = "Skip",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onSkipClick() }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}
