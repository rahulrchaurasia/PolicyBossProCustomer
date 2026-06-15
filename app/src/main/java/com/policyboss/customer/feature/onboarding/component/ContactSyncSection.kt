package com.policyboss.customer.feature.onboarding.component

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

import com.policyboss.customer.R
import com.policyboss.customer.ui.components.image.Avatar
import com.policyboss.customer.ui.components.image.AvatarImage
import com.policyboss.customer.ui.components.image.AvatarWithIcon

//@Composable
//fun ContactSyncSection() {
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        // Overlapping Avatars
//        Box(
//            modifier = Modifier
//                .height(70.dp)
//                .width(120.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            // Avatar 1 (Left, Back)
//
//            Avatar(
//                image = AvatarImage.Drawable(R.drawable.avatar1),
//                size = 56.dp,
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .zIndex(1f)
//            )          // Avatar 2 (Right, Back)
//
//            // Right avatar (back)
//            Avatar(
//                image = AvatarImage.Drawable(R.drawable.avatar2),
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .offset(x = (-10).dp)
//            )
//
//            // Center avatar (front)
//            Avatar(
//                image = AvatarImage.Drawable(R.drawable.dollar_full),
//                size = 56.dp,
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .zIndex(1f)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        FeatureTag(text = "Contact syncing", icon = R.drawable.ic_sync)
//    }
//}


@Composable
fun ContactSyncSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(width = 140.dp, height = 110.dp)) {
            // Top Left Avatar
            AvatarWithSyncBadge(
                image = R.drawable.avatar1,
                size = 40.dp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            // Top Right Avatar
            AvatarWithSyncBadge(
                image = R.drawable.avatar2,
                size = 35.dp,
                modifier = Modifier.align(Alignment.TopEnd).padding(top = 10.dp, end = 10.dp)
            )
            // Bottom Center Avatar (Main)
            AvatarWithSyncBadge(
                image = R.drawable.avatar3,
                size = 65.dp,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        FeatureTag(text = "Contact syncing")
    }
}