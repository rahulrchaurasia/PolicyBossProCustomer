package com.policyboss.customer.feature.claimSupport.claimSupportJourney.drivingLicenseAndPolicyReport.ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.policyboss.customer.R
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.progreebar.AppStepProgressBar
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun SingleDocumentUploadScreen(
    title: String,
    step: Int,
    totalSteps: Int,
    documentUri: Uri?,
    emptyButtonText: String,
    selectedButtonText: String,
    informationTitle: String,
    informationItems: List<String>,
    onUploadClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        AppTopBar(
            title = "Raise a claim",
            onBackClick = onBackClick,
            trailingIcon = painterResource(id = R.drawable.ic_settings),
            onTrailingClick = { },
            titleColor = AppColors.TextPrimary,
            backIconTint = AppColors.TextPrimary,
            trailingIconTint = AppColors.TextPrimary
        )

        // Progress
        AppStepProgressBar(
            currentStep = step,
            totalSteps = totalSteps,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$step/$totalSteps",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = AppColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Document Preview or Placeholder
            if (documentUri == null) {
                DocumentUploadPlaceholder(onClick = onUploadClick)
            } else {
                DocumentPreview(uri = documentUri, onRemoveClick = onRemoveClick)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info Box
            DocumentInformationBox(title = informationTitle, items = informationItems)
            Spacer(modifier = Modifier.height(32.dp))
        }

        // Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .navigationBarsPadding()
        ) {
            PrimaryCTAButton(
                text = if (documentUri != null) selectedButtonText else emptyButtonText,
                onClick = onContinueClick,
                contentColor = AppColors.White,
                arrowBackgroundColor = AppColors.White,
                arrowTint = AppColors.ClaimDarkBg
            )
        }
    }
}

@Composable
private fun DocumentUploadPlaceholder(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8F9FA))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.FileUpload,
            contentDescription = "Upload document",
            tint = AppColors.TextPrimary,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun DocumentPreview(uri: Uri, onRemoveClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "Uploaded document",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
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

@Composable
private fun DocumentInformationBox(title: String, items: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, AppColors.HighlightCardBorder, RoundedCornerShape(12.dp))
            .background(AppColors.HighlightCardBackground, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = AppColors.WarningYellow
        )
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { item ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(text = "•", color = AppColors.WarningYellow, modifier = Modifier.padding(end = 8.dp))
                Text(text = item, style = MaterialTheme.typography.bodyMedium, color = AppColors.WarningYellow)
            }
        }
    }
}