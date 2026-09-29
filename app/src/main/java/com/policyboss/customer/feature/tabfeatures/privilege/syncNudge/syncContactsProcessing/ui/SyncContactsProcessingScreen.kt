package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.ui


import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContactsProcessing.state.SyncProcessingState
import com.policyboss.customer.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncContactsProcessingScreen(
    state: SyncProcessingState,
    onCompleteProfileClicked: () -> Unit
) {
    // Prevent dismissing the bottom sheet by swiping
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,

        confirmValueChange = { sheetValue ->
            // Allow the sheet to open, but prevent it from being hidden
            sheetValue != SheetValue.Hidden
        }
    )

    // Block the physical back button if the bottom sheet is showing to force user to click the button
    BackHandler(enabled = state.isSuccess) {
        // Do nothing, forcing them to use the "Complete Profile" button
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.ClaimDarkBg) // Dark background from your theme
    ) {
        // 1. Background Rings (Using Crop as established previously)
        Image(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.3f // Dim the rings slightly on the dark background
        )

        // 2. Center Content
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Stacked Avatars with Badge
            StackedAvatarsBox(contactCount = state.contactsFound)

            Spacer(modifier = Modifier.height(24.dp))

            // Dynamic Text
            Text(
                text = if (state.isSuccess) "Contacts fetched" else "Fetching contacts",
                color = AppColors.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Animated Crossfade between Progress Bar and Earning Potential Card
            Box(contentAlignment = Alignment.Center, modifier = Modifier.height(80.dp)) {

                // Progress Bar (Visible while syncing)
                androidx.compose.animation.AnimatedVisibility(
                    visible = state.isSyncing,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LinearProgressIndicator(
                        progress = state.progress,
                        color = AppColors.GoldBackground,
                        trackColor = Color(0xFF334155), // Dark slate
                        modifier = Modifier
                            .width(180.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(50))
                    )
                }

                // Earning Potential Card (Visible on success)
                androidx.compose.animation.AnimatedVisibility(
                    visible = state.isSuccess,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = AppColors.GoldBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 12.dp, horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Your earning potential",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = AppColors.GoldText
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)) {
                                        append("₹1,25,000")
                                    }
                                    withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)) {
                                        append("/year")
                                    }
                                },
                                color = AppColors.ProBadgeText
                            )
                        }
                    }
                }
            }
        }
    }

    // 3. Non-Cancellable Bottom Sheet
    if (state.isSuccess) {
        ModalBottomSheet(
            onDismissRequest = { /* Blocked by confirmValueChange = { false } */ },
            sheetState = sheetState,
            containerColor = AppColors.Surface,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            SuccessBottomSheetContent(onCompleteClicked = onCompleteProfileClicked)
        }
    }
}

@Composable
fun StackedAvatarsBox(contactCount: Int) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        // Avatar 1 (Back Left)
        Image(
            painter = painterResource(id = R.drawable.ic_avatar1),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .offset(x = (-30).dp, y = (-10).dp)
                .zIndex(1f)
        )
        // Avatar 2 (Back Right)
        Image(
            painter = painterResource(id = R.drawable.ic_avatar2),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .offset(x = 20.dp, y = (-20).dp)
                .zIndex(2f)
        )
        // Avatar 3 (Front Left)
        Image(
            painter = painterResource(id = R.drawable.ic_avatar3),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .offset(x = (-15).dp, y = 15.dp)
                .zIndex(3f)
        )
        // Avatar 4 (Front Right - Main)
        Image(
            painter = painterResource(id = R.drawable.ic_avatar4),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .offset(x = 15.dp, y = 5.dp)
                .zIndex(4f)
        )

        // Contact Badge (+219)
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp, y = 10.dp)
                .size(48.dp)
                .background(Color(0xFFDDF7EC), CircleShape) // Using BrandSuccessLight / Pale Cyan
                .border(2.dp, AppColors.ClaimDarkBg, CircleShape) // Add a border to separate it from the rings
                .zIndex(5f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+$contactCount",
                color = AppColors.TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun SuccessBottomSheetContent(onCompleteClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp, top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congratulations!",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = AppColors.TextPrimary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Limitless earning awaiting",
            fontSize = 14.sp,
            color = AppColors.TextPrimary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Pending Card
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = AppColors.HighlightCardBackground,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(1.dp, AppColors.HighlightCardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular Progress Indicator for profile
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(48.dp)) {
                CircularProgressIndicator(
                    progress = 0.4f,
                    color = AppColors.WarningYellow,
                    trackColor = AppColors.HighlightCardDividerLight,
                    strokeWidth = 4.dp,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "40%",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.GoldText
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Profile Pending",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = AppColors.GoldText
                )
                Text(
                    text = "Complete the 'Privilege User' profile to avail earnings on renewals",
                    fontSize = 12.sp,
                    color = AppColors.GoldText,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Complete Profile Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(50))
                .background(AppColors.BrandDark)
                .clickable { onCompleteClicked() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Complete Profile",
                color = AppColors.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Box(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(AppColors.White)
                    .align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow Forward",
                    tint = AppColors.BrandDark
                )
            }
        }
    }
}

@Preview(showSystemUi = true, name = "1. Syncing State")
@Composable
fun SyncContactsProcessingScreenSyncingPreview() {
    SyncContactsProcessingScreen(
        state = SyncProcessingState(
            isSyncing = true,
            isSuccess = false,
            progress = 0.5f,
            contactsFound = 109
        ),
        onCompleteProfileClicked = {}
    )
}

@Preview(showSystemUi = true, name = "2. Success State")
@Composable
fun SyncContactsProcessingScreenSuccessPreview() {
    SyncContactsProcessingScreen(
        state = SyncProcessingState(
            isSyncing = false,
            isSuccess = true,
            progress = 1f,
            contactsFound = 219
        ),
        onCompleteProfileClicked = {}
    )
}