package com.policyboss.customer.feature.home.component.home.videoSection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.model.video.VideoModel
import com.policyboss.customer.ui.components.pageIndicator.AppPagerIndicator
import com.policyboss.customer.ui.theme.AppColors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.ui.theme.bodyMediumSemiBold
import com.policyboss.customer.ui.theme.labelMediumSemiBold
import com.policyboss.customer.R



// Compose Core & UI

import androidx.compose.ui.res.painterResource



import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width


// Compose Pager

// Compose Animation

// Compose Material 3 & Icons
import androidx.compose.material3.Icon



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoSliderSection(
    modifier: Modifier = Modifier,
    videos: List<VideoModel>,
    onViewMoreClick: () -> Unit,
    onVideoClick: (VideoModel) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { videos.size })

    // 1. APPLY THE CARD SHAPE AND BACKGROUND HERE
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(AppColors.CardBackground)
            .padding(24.dp) // Inner padding for the card content
    ) {
        // --- SECTION HEADER ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Videos",
                style = MaterialTheme.typography.bodyMediumSemiBold,
                color = AppColors.TextSecondary
            )

            // Custom "View more" row matching your reference
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onViewMoreClick() }
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View more",
                    style = MaterialTheme.typography.labelMediumSemiBold,
                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = AppColors.TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- HORIZONTAL PAGER ---
        HorizontalPager(
            state = pagerState,
            // Removed horizontal contentPadding so the image aligns with the text above it
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            VideoCard(
                video = videos[page],
                onClick = { onVideoClick(videos[page]) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- DOT INDICATORS ---
        AppPagerIndicator(
            totalPages = videos.size,
            currentPage = pagerState.currentPage,
            activeWidth = 8.dp,
            inactiveWidth = 8.dp,
            indicatorHeight = 8.dp,
            activeColor = Color.DarkGray,   // Use AppColors.Gray
            inactiveColor = Color.LightGray, // Use AppColors.Border
            // Removed bottom padding to keep the card compact
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// =================================================================
// PREVIEW
// =================================================================

@Preview(showBackground = true, backgroundColor = 0xFFF3F4F6)
@Composable
fun VideoSliderSectionPreview() {
    MaterialTheme {

        // Dummy data for the preview
        val dummyVideos = listOf(
            VideoModel(
                id = "v1",
                title = "How to Sell Insurance to Millennials",
                youtubeVideoId = "iXl8iH-Vu8M"
            ),
            VideoModel(
                id = "v2",
                title = "Handling Customer Objections Like a Pro",
                youtubeVideoId = "dQw4w9WgXcQ"
            ),
            VideoModel(
                id = "v3",
                title = "Tax Benefits of Life Insurance in 2026",
                youtubeVideoId = "tgbNymZ7vqY"
            )
        )

        Box(modifier = Modifier.padding(vertical = 24.dp)) {
            VideoSliderSection(
                videos = AppDummyData.dummyVideos,
                onViewMoreClick = { println("View more clicked") },
                onVideoClick = { video -> println("Clicked video: ${video.title}") }
            )
        }
    }
}