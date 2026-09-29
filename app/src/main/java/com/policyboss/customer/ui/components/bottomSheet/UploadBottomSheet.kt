package com.policyboss.customer.ui.components.bottomSheet

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


// ==========================================
// 1. THE WRAPPER (Used in your App Routes)
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadBottomSheet(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onFilesClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.Transparent, // Makes the sheet invisible so we can draw our own
        dragHandle = null // Removes the default gray pill
    ) {
        // We call the visual content here!
        UploadBottomSheetContent(
            onDismiss = onDismiss,
            onCameraClick = onCameraClick,
            onGalleryClick = onGalleryClick,
            onFilesClick = onFilesClick
        )
    }
}

// ==========================================
// 2. THE VISUAL CONTENT (Stateless for Previews)
// ==========================================
@Composable
private fun UploadBottomSheetContent(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onFilesClick: () -> Unit
) {
    // 🚀 Use a Box to perfectly overlap the Button and the White Content
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        // THE WHITE SHEET CONTENT
        Column(
            modifier = Modifier
                .padding(top = 24.dp) // Push the white background down to leave room for the floating button
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp, bottom = 32.dp) // Top padding ensures text clears the button
        ) {
            Text(
                text = "Upload from",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            UploadOptionRow(
                title = "Camera",
                iconId = R.drawable.ic_camera,
                onClick = onCameraClick
            )
            HorizontalDivider(color = AppColors.BorderSecondary)

            UploadOptionRow(
                title = "Choose from gallery",
                iconId = R.drawable.ic_gallery,
                onClick = onGalleryClick
            )
            HorizontalDivider(color = AppColors.BorderSecondary)

            UploadOptionRow(
                title = "Choose from files",
                iconId = R.drawable.ic_file,
                onClick = onFilesClick
            )
        }

        // THE FLOATING CLOSE BUTTON
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, AppColors.BorderSecondary, CircleShape)
                .clickable { onDismiss() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = AppColors.TextPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun UploadOptionRow(title: String, iconId: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = AppColors.TextPrimary,
            fontWeight = FontWeight.Medium
        )
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = title,
            tint = AppColors.TextSecondary,
            modifier = Modifier.size(24.dp)
        )
    }
}

// ==========================================
// 3. THE PREVIEW
// ==========================================
@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF808080, // Using a hex gray to simulate the dim background overlay
    name = "Upload Bottom Sheet"
)
@Composable
fun UploadBottomSheetPreview() {
    PolicyBossCustomerTheme {
        // 🚀 Preview the CONTENT, not the Wrapper!
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            UploadBottomSheetContent(
                onDismiss = { },
                onCameraClick = { },
                onGalleryClick = { },
                onFilesClick = { }
            )
        }
    }
}