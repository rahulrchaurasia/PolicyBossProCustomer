package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.policyboss.customer.R


@Composable
fun SelectedPhotoItem(uri: Uri, onRemove: () -> Unit) {
    Box(modifier = Modifier.size(80.dp)) {

        // 1. Main Image / File Fallback
        AsyncImage(
            model = uri,
            contentDescription = "Uploaded File",
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_file),
            fallback = painterResource(id = R.drawable.ic_file),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF4F6F8)) // Light grey background for the file icon
        )

        // 2. Custom Close Button (Matches Image 1 Exactly)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(2.dp) // Keeps it fully inside the square with a small margin
                .size(24.dp) // Overall size of the white circle
                .background(Color.White, CircleShape)
                .clip(CircleShape)
                .clickable { onRemove() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close), // Your custom icon
                contentDescription = "Remove",
                tint = Color.Black,
                modifier = Modifier.size(14.dp) // Size of the actual 'X' inside the white circle
            )
        }
    }
}

@Preview(showBackground = true, name = "Selected Photo Item Preview")
@Composable
fun SelectedPhotoItemPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            SelectedPhotoItem(
                uri = "content://dummy/image.jpg".toUri(),
                onRemove = { }
            )
        }
    }
}