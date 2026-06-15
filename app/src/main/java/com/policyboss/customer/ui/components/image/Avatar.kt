package com.policyboss.customer.ui.components.image

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.ui.*
import androidx.compose.ui.draw.clip

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*

import androidx.compose.material3.MaterialTheme

import coil.compose.AsyncImage

import com.policyboss.customer.R

sealed interface AvatarImage {
    data class Drawable(val resId: Int) : AvatarImage
    data class Url(val url: String) : AvatarImage
}
@Composable
fun Avatar(
    image: AvatarImage,
    size: Dp = 40.dp,
    showBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(size)) {

        when (image) {

            is AvatarImage.Drawable -> {
                Image(
                    painter = painterResource(id = image.resId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.background,
                            CircleShape
                        )
                )
            }

            is AvatarImage.Url -> {
                AsyncImage(
                    model = image.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.background,
                            CircleShape
                        )
                )
            }
        }

//        if (showBadge) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_sync_small),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(size * 0.35f)
//                    .align(Alignment.BottomEnd)
//                    .offset(2.dp, 2.dp)
//            )
//        }
    }
}