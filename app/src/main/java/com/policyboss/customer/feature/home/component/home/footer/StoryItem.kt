package com.policyboss.customer.feature.home.component.home.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.policyboss.customer.R
import com.policyboss.customer.feature.home.model.bossepidia.BosspediaStory
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun StoryItem(
    story: BosspediaStory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (story.hasUnseenContent) {
        AppColors.BluePrimary// The blue you specified for unread
    } else {
        AppColors.Dividers // A light gray for read/seen stories
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(86.dp) // The size from your XML
            .clip(CircleShape)
            // Add the border (unread vs read)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
            )
            .clickable { onClick() }
            .padding(5.dp) // Space between the border and the inner circle
    ) {
        // Inner circle with gradient background
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2E90FA),
                            Color(0xFFCBF4DF)
                        ),
                        // Adjusting coordinates roughly matching your XML gradient
                        start = Offset(0f, 0f), 
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                )
        ) {
            // The transparent PolicyBoss logo overlay
            Icon(
                painter = painterResource(id = R.drawable.ic_policyboss_transparent),
                contentDescription = "PolicyBoss Logo",
                tint = Color.White, // Assuming it should be white over the gradient
                modifier = Modifier
                    .size(48.dp) // Adjust size as needed to fit nicely inside
            )

//            AsyncImage(
//                model = story.imageUrl, // Loads the URL from your data class
//                contentDescription = "Story Image",
//                contentScale = ContentScale.Crop, // Ensures the image fills the circle perfectly
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(CircleShape)
//            )
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun StoryItemPreview() {
    MaterialTheme {
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preview 1: Unseen content (Shows Blue Border)
            StoryItem(
                story = BosspediaStory(
                    id = "1",
                    hasUnseenContent = true
                ),
                onClick = {}
            )

            // Preview 2: Seen content (Shows Gray Border)
            StoryItem(
                story = BosspediaStory(
                    id = "2",
                    hasUnseenContent = false
                ),
                onClick = {}
            )
        }
    }
}