package com.policyboss.customer.feature.tabfeatures.privilege.syncNudge.syncContact.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.badge.AvatarWithReSyncBadge
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

@Composable
fun SyncContactsScreen(
    onSyncClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.Background) // Used theme color
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 12.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- TOP SECTION (Header & Banner) ---
        Text(
            text = "Account Created!",

            style = MaterialTheme.typography.titleLarge,

            color = AppColors.TextPrimary // Migrated to Theme
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Now, add your network to help them save\non insurance while you earn on their\nrenewals",
            fontSize = 15.sp,
            color = AppColors.TextSecondary, // Migrated to Theme
            textAlign = TextAlign.Center,

            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Yellow Earning Banner
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(
                    color = AppColors.GoldBackground, // Migrated to Theme
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(vertical = 16.dp, horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "On an avg. users earn up to",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.GoldText // Migrated to Theme
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)) {
                            append("₹1,00,000")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)) {
                            append("/year")
                        }
                    },
                    color = AppColors.ProBadgeText // Migrated to Theme
                )
            }
        }

        // --- MIDDLE SECTION (Vector Art & Avatars) ---
        // --- MIDDLE SECTION (Vector Art & Avatars) ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            // 1. Background Circles
            Image(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop


            )

            // 2. Center Smartphone
            Image(
                painter = painterResource(id = R.drawable.ic_smartphone),
                contentDescription = "Smartphone",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.Center)
            )

            // 3. Floating Avatars using the Reusable Component!

            // Top Left Avatar
            AvatarWithReSyncBadge(
                avatarResId = R.drawable.ic_avatar4,
                avatarSize = 64.dp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = (-95).dp, y = (-120).dp)
            )

            // Top Right Avatar
            AvatarWithReSyncBadge(
                avatarResId = R.drawable.ic_avatar1,
                avatarSize = 60.dp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = 90.dp, y = (-90).dp)
            )

            // Bottom Left Avatar
            AvatarWithReSyncBadge(
                avatarResId = R.drawable.ic_avatar3,
                avatarSize = 58.dp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = (-80).dp, y = 110.dp)
            )

            // Bottom Right Avatar
            AvatarWithReSyncBadge(
                avatarResId = R.drawable.ic_avatar2,
                avatarSize = 72.dp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = 95.dp, y = 100.dp)
            )
        }

        // --- BOTTOM SECTION (Privacy & Button) ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Security Lock",
                tint = AppColors.PrimaryBlue, // Migrated to Theme
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "No harm to privacy, no spams, trusted security",
                fontSize = 13.sp,
                color = AppColors.Gray500, // Migrated to Theme
                fontWeight = FontWeight.Medium
            )
        }

        // Sync Contacts Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(50))
                .background(AppColors.BrandDark) // Migrated to Theme
                .clickable { onSyncClicked() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sync Contacts",
                color = AppColors.White, // Migrated to Theme
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            // Circular Arrow Button on the right
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(AppColors.White) // Migrated to Theme
                    .align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow Forward",
                    tint = AppColors.BrandDark // Migrated to Theme
                )
            }
        }
    }
}

// 1. Default State Preview
@Preview(showBackground = true, name = "Default State")
@Composable
fun SyncContactsScreenDefaultPreview() {
    // Wrap in your app's theme if you have one, e.g., PolicyBossTheme { ... }
    PolicyBossCustomerTheme {

        SyncContactsScreen(
            //isLoading = false,
            onSyncClicked = {}
        )
    }

}

// 2. Loading State Preview
//@Preview(showBackground = true, name = "Loading State")
//@Composable
//fun SyncContactsScreenLoadingPreview() {
//    SyncContactsScreen(
//       // isLoading = true,
//        onSyncClicked = {}
//    )
//}