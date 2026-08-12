package com.policyboss.customer.feature.home.ui.main.homeScreen

// ⚠️ Make sure to import your specific shimmer extension function here!
// import com.policyboss.customer.utils.shimmerLoadingAnimation



// ⚠️ Ensure your shimmer extension is imported here
// import com.policyboss.customer.utils.shimmerLoadingAnimation



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.utils.extension.shimmerLoadingAnimation

// ⚠️ Ensure your shimmer extension is imported here
// import com.policyboss.customer.utils.shimmerLoadingAnimation

@Composable
fun HomeLoaderScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 0.dp, // Match actual screen which handles its own top spacing
            bottom = contentPadding.calculateBottomPadding() + 32.dp
        ),
        // We handle spacing manually because of the offset on the quick actions
        verticalArrangement = Arrangement.Top
    ) {

        // =====================================================
        // 1. HERO / HEADER SECTION SKELETON (Matches Blue Area)
        // =====================================================
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp) // Match your HeroHeight
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(Modifier.height(48.dp))

                    // Header Row: Text + Profile Circle
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            // "Welcome back!" skeleton line
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(20.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .shimmerLoadingAnimation(isLoading = true)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Subtitle skeleton line
                            Box(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(14.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .shimmerLoadingAnimation(isLoading = true)
                            )
                        }

                        // Profile Initials Circle
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .shimmerLoadingAnimation(isLoading = true)
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    // Promo Banner Card Skeleton
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(180.dp) // Approximate height of the yellow banner in screenshot
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerLoadingAnimation(isLoading = true)
                    )
                }
            }
        }

        // =====================================================
        // 2. QUICK ACTIONS GRID SKELETON (2x2 Layout from Screenshot)
        // =====================================================
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = (-42).dp), // Match the overlap from the real UI
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Row 1
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(180.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerLoadingAnimation(isLoading = true)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(180.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerLoadingAnimation(isLoading = true)
                    )
                }

                // Row 2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(180.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerLoadingAnimation(isLoading = true)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(180.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerLoadingAnimation(isLoading = true)
                    )
                }
            }
        }

        // =====================================================
        // 3. EARNING OPPORTUNITY SECTION SKELETON
        // =====================================================
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                // No need to offset this, the offset modifier in QuickActions
                // doesn't affect the layout bounds of subsequent items.
            ) {
                // Section Title Skeleton
                Box(
                    modifier = Modifier
                        .width(220.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerLoadingAnimation(isLoading = true)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dark Background Card Skeleton
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .shimmerLoadingAnimation(isLoading = true)
                )
            }
        }
    }
}

// ==========================================
// PREVIEWS
// ==========================================

@Preview(
    name = "Facebook Style Loader Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun HomeLoaderScreenPreview() {
    MaterialTheme {
        HomeLoaderScreen(
            contentPadding = PaddingValues(0.dp)
        )
    }
}

//@Composable
//fun HomeLoaderScreen(
//    modifier: Modifier = Modifier,
//    contentPadding: PaddingValues
//) {
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//        contentPadding = PaddingValues(
//            top = 0.dp, // Match actual screen which handles its own top spacing
//            bottom = contentPadding.calculateBottomPadding() + 32.dp
//        ),
//        // We handle spacing manually because of the offset on the quick actions
//        verticalArrangement = Arrangement.Top
//    ) {
//
//        // =====================================================
//        // 1. HERO / HEADER SECTION SKELETON (Matches Blue Area)
//        // =====================================================
//        item {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(380.dp) // Match your HeroHeight
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Spacer(Modifier.height(48.dp))
//
//                    // Header Row: Text + Profile Circle
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 24.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Column {
//                            // "Welcome back!" skeleton line
//                            Box(
//                                modifier = Modifier
//                                    .width(200.dp)
//                                    .height(20.dp)
//                                    .clip(RoundedCornerShape(8.dp))
//                                    .shimmerLoadingAnimation(isLoading = true)
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            // Subtitle skeleton line
//                            Box(
//                                modifier = Modifier
//                                    .width(250.dp)
//                                    .height(14.dp)
//                                    .clip(RoundedCornerShape(8.dp))
//                                    .shimmerLoadingAnimation(isLoading = true)
//                            )
//                        }
//
//                        // Profile Initials Circle
//                        Box(
//                            modifier = Modifier
//                                .size(48.dp)
//                                .clip(CircleShape)
//                                .shimmerLoadingAnimation(isLoading = true)
//                        )
//                    }
//
//                    Spacer(Modifier.height(24.dp))
//
//                    // Promo Banner Card Skeleton
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 24.dp)
//                            .height(180.dp) // Approximate height of the yellow banner in screenshot
//                            .clip(RoundedCornerShape(24.dp))
//                            .shimmerLoadingAnimation(isLoading = true)
//                    )
//                }
//            }
//        }
//
//        // =====================================================
//        // 2. QUICK ACTIONS GRID SKELETON (2x2 Layout from Screenshot)
//        // =====================================================
//        item {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp)
//                    .offset(y = (-42).dp), // Match the overlap from the real UI
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // Row 1
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(180.dp)
//                            .clip(RoundedCornerShape(24.dp))
//                            .shimmerLoadingAnimation(isLoading = true)
//                    )
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(180.dp)
//                            .clip(RoundedCornerShape(24.dp))
//                            .shimmerLoadingAnimation(isLoading = true)
//                    )
//                }
//
//                // Row 2
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(180.dp)
//                            .clip(RoundedCornerShape(24.dp))
//                            .shimmerLoadingAnimation(isLoading = true)
//                    )
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(180.dp)
//                            .clip(RoundedCornerShape(24.dp))
//                            .shimmerLoadingAnimation(isLoading = true)
//                    )
//                }
//            }
//        }
//
//        // =====================================================
//        // 3. EARNING OPPORTUNITY SECTION SKELETON
//        // =====================================================
//        item {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp)
//                // No need to offset this, the offset modifier in QuickActions
//                // doesn't affect the layout bounds of subsequent items.
//            ) {
//                // Section Title Skeleton
//                Box(
//                    modifier = Modifier
//                        .width(220.dp)
//                        .height(24.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .shimmerLoadingAnimation(isLoading = true)
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Dark Background Card Skeleton
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(280.dp)
//                        .clip(RoundedCornerShape(24.dp))
//                        .shimmerLoadingAnimation(isLoading = true)
//                )
//            }
//        }
//    }
//}
//
//// ==========================================
//// PREVIEWS
//// ==========================================
//
//@Preview(
//    name = "Facebook Style Loader Preview",
//    showBackground = true,
//    backgroundColor = 0xFFFFFFFF
//)
//@Composable
//private fun HomeLoaderScreenPreview() {
//    MaterialTheme {
//        HomeLoaderScreen(
//            contentPadding = PaddingValues(0.dp)
//        )
//    }
//}

