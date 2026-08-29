package com.policyboss.customer.feature.claimSupport.claimSupportJourney.damagePhotos.ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage


@Composable
 fun SelectedPhotoItem(uri: Uri, onRemove: () -> Unit) {
    Box(modifier = Modifier.size(80.dp)) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
        )
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(20.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = Color.Black,
                modifier = Modifier.size(14.dp)
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
                // Note: AsyncImage might just show a blank/gray box in the
                // Android Studio preview window because this is a fake URI.
                uri = "content://dummy/image.jpg".toUri(),
                onRemove = { /* Do nothing in preview */ }
            )
        }
    }
}