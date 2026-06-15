package com.policyboss.customer.ui.components.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable

import androidx.compose.ui.draw.clip

import coil.compose.AsyncImage



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp

import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.tooling.preview.Preview

import coil.compose.AsyncImage
import com.policyboss.customer.R

@Composable
fun AvatarWithIcon(image: Int, modifier: Modifier, showBorder: Boolean = false) {
    Box(modifier = modifier) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(CircleShape)
                .then(if (showBorder) Modifier.border(2.dp, Color.White, CircleShape) else Modifier)
        )
        // The little sync icon on the corner of the avatar
        AsyncImage(
            model = R.drawable.ic_wallet,
            contentDescription = null,
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.BottomEnd)
        )
    }
}