package com.policyboss.customer.feature.home.component.home.earningOpportunitySection



// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.component.home.card.EarningBannerCard
import com.policyboss.customer.feature.home.model.EarningBanner
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.WarningYellow
@Composable
fun EarningOpportunitySection(
    banners: List<EarningBanner>,
    onBannerClick: (String) -> Unit,
    onJoinPrivilegeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(AppColors.DarkBackground)
            .padding(vertical = 24.dp)
    ) {

        // 1. Top Star Badge
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp)) // Using rounded square, or use a custom hexagon shape
                .background(WarningYellow),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = AppColors.DarkBackground)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Titles & Info Pill
//        Text(
//            text = "Turn your renewals into an\nearning opportunity",
//            style = MaterialTheme.typography.titleLarge.copy(
//                fontWeight = FontWeight.Bold,
//                lineHeight = 28.sp
//            ),
//            color = Color.White,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = androidx.compose.ui.text.style.TextAlign.Center
//        )

        Text(
            text = "Turn your renewals into an earning opportunity",
            color = Color.White,
            textAlign = TextAlign.Center,
            // 1. Use your theme's typography
            // 2. labelSmall already has FontWeight.Medium and the Geist font!
            // 3. We just override the size to 13.sp using .copy()
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(WarningYellow.copy(alpha = 0.15f))
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Join 1L+ users already ahead with India's\nfirst exclusive benefits",
                color = WarningYellow, // or AppColors.WarningYellow based on your imports
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Dynamic Banners LazyRow
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = banners, key = { it.id }) { banner ->
                EarningBannerCard(
                    banner = banner,
                    onClick = {

                        onBannerClick(

                            banner.id
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.1f), modifier = Modifier.padding(horizontal = 24.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // 4. Bottom Call to Action
        PrivilegeBottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = onJoinPrivilegeClick
        )
    }
}

