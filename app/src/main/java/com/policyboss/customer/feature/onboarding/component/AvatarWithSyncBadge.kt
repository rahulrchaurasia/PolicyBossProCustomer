package com.policyboss.customer.feature.onboarding.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage
import com.policyboss.customer.R

@Composable
fun AvatarWithSyncBadge(image: Int, size: Dp, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(size)) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
            contentScale = ContentScale.Crop
        )
        // Small Sync Badge on each avatar
        AsyncImage(
            model = R.drawable.ic_sync,
            contentDescription = null,
            modifier = Modifier
                .size(size / 2.5f)
                .align(Alignment.BottomEnd)
                .offset(x = 2.dp, y = 2.dp)
        )
    }
}