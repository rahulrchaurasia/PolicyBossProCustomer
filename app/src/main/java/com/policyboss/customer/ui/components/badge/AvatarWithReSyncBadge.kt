package com.policyboss.customer.ui.components.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Make sure to replace this with your actual R class path if it differs
import com.policyboss.customer.R

@Composable
fun AvatarWithReSyncBadge(
    avatarResId: Int,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 64.dp,
    badgeSize: Dp = 24.dp
) {
    // The parent Box controls the overall placement, wrapping the avatar size
    Box(modifier = modifier) {

        // 1. Main Avatar
        Image(
            painter = painterResource(id = avatarResId),
            contentDescription = null,
            modifier = Modifier.size(avatarSize)
        )

        // 2. The Resync Badge
        Image(
            painter = painterResource(id = R.drawable.ic_resync),
            contentDescription = "Resync Status",
            modifier = Modifier
                .size(badgeSize)
                // Pin to the bottom right of the Avatar
                .align(Alignment.BottomEnd)
                // Push it halfway outside the bounds using positive offsets
                .offset(x = (badgeSize / 3), y = (badgeSize / 4))
        )
    }
}

@Preview(showBackground = true, name = "Avatar With Badge Preview")
@Composable
fun AvatarWithReSyncBadgePreview() {
    // Adding some padding in the preview so the offset badge isn't cut off by the preview boundaries
    Box(
        modifier = Modifier.padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        AvatarWithReSyncBadge(
            avatarResId = R.drawable.ic_avatar1, // Uses your actual drawable
            avatarSize = 64.dp,
            badgeSize = 24.dp
        )
    }
}