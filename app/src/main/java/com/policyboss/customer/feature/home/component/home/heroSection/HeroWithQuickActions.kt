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
import com.policyboss.customer.feature.home.component.home.QuickActionsGrid
import com.policyboss.customer.feature.home.model.PromoBanner
import com.policyboss.customer.feature.home.model.QuickAction
import com.policyboss.customer.feature.home.model.banner.BannerAction
import com.policyboss.customer.feature.home.model.banner.BannerDestination

@Composable
fun HeroWithQuickActions(

    userName: String,

    initials: String,

    promoBanners: List<PromoBanner>,

    quickActions: List<QuickAction>,

    onProfileClick: () -> Unit,

    onBannerClick: (PromoBanner) -> Unit,

    onQuickActionClick: (String) -> Unit

) {

    val overlap = 42.dp

    Box(

        modifier = Modifier

            .fillMaxWidth()

    ) {

        Column(

            modifier = Modifier

                .padding(bottom = overlap)

        ) {

            HeroSection(

                userName = userName,

                initials = initials,

                promoBanners = promoBanners,

                onProfileClick = onProfileClick,

                onBannerClick = onBannerClick

            )

        }



        QuickActionsGrid(

            actions = quickActions,

            onClick = onQuickActionClick,

            modifier = Modifier

                .align(Alignment.BottomCenter)

                .padding(horizontal = 24.dp)

        )
    }
}