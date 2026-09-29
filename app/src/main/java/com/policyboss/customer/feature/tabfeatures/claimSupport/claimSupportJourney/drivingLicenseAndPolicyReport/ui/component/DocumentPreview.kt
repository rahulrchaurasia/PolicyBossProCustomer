package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.policyboss.customer.ui.theme.AppColors




import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.policyboss.customer.R

import androidx.compose.ui.tooling.preview.Preview


// ==========================================
// 1. THE WRAPPER (Handles the Android Logic)
// ==========================================
@Composable
fun DocumentPreview(uri: Uri, onRemoveClick: () -> Unit) {
    val context = LocalContext.current

    // Check if the URI belongs to a PDF document
    val isPdf = remember(uri) {
        val mimeType = context.contentResolver.getType(uri)
        mimeType?.contains("pdf", ignoreCase = true) == true
    }

    // Call the stateless UI component
    DocumentPreviewContent(
        uri = uri,
        isPdf = isPdf,
        onRemoveClick = onRemoveClick
    )
}

// ==========================================
// 2. THE VISUAL CONTENT (Stateless, easy to preview)
// ==========================================
@Composable
private fun DocumentPreviewContent(
    uri: Uri,
    isPdf: Boolean,
    onRemoveClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8F9FA))
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(20.dp))
    ) {
        if (isPdf) {
            // Show Generic PDF Preview
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_file), // Using your existing file icon
                    contentDescription = "PDF Document",
                    modifier = Modifier.size(64.dp),
                    tint = AppColors.TextSecondary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "PDF Document Attached",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.TextPrimary,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            // Show Image Preview
            AsyncImage(
                model = uri,
                contentDescription = "Uploaded document",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        // Close Button
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color(0xFFE5E7EB), CircleShape)
                .clickable(onClick = onRemoveClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove document",
                tint = AppColors.TextPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// ==========================================
// 3. THE PREVIEWS
// ==========================================

@Preview(showBackground = true, name = "1. Image Selected Preview")
@Composable
private fun DocumentPreviewImagePreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            DocumentPreviewContent(
                uri = Uri.EMPTY, // Coil will handle the empty URI gracefully in preview
                isPdf = false,   // Force the Image UI to show
                onRemoveClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "2. PDF Selected Preview")
@Composable
private fun DocumentPreviewPdfPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            DocumentPreviewContent(
                uri = Uri.EMPTY,
                isPdf = true,    // 🚀 Force the PDF UI to show
                onRemoveClick = {}
            )
        }
    }
}