package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.policyboss.customer.R

import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosAction
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.model.state.DamagePhotosUiState
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.ui.component.DamagePhotoInformationBox
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.damagePhotos.ui.component.SelectedPhotoItem
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.progreebar.AppStepProgressBar
import com.policyboss.customer.ui.components.text.AppAnimatedErrorText
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun DamagePhotosScreen(
    uiState: DamagePhotosUiState,
    onAction: (DamagePhotosAction) -> Unit,
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
            currentStep = 3,
            totalSteps = 5,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "3/5",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Damage Photos",
                style = MaterialTheme.typography.headlineMedium,
                color = AppColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Upload Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF8F9FA))
                    .clickable { onAction(DamagePhotosAction.OnUploadClick) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FileUpload,
                    contentDescription = "Upload Photo",
                    tint = AppColors.TextPrimary,
                    modifier = Modifier.size(32.dp)
                )
            }

            // Selected Photos
            if (uiState.photos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(uiState.photos, key = { it.toString() }) { uri ->
                        SelectedPhotoItem(
                            uri = uri,
                            onRemove = { onAction(DamagePhotosAction.OnPhotoRemoved(uri)) }
                        )
                    }
                }
            }

            // 🚀 REUSABLE COMPONENT USED HERE
            AppAnimatedErrorText(errorMessage = uiState.errorMessage)

            Spacer(modifier = Modifier.height(24.dp))

            // Info Box
            DamagePhotoInformationBox()
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
                text = "Confirm and Continue",
                onClick = { onAction(DamagePhotosAction.OnContinueClick) },
                contentColor = AppColors.White,
                arrowBackgroundColor = AppColors.White,
                arrowTint = AppColors.ClaimDarkBg
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, name = "Damage Photos - Empty")
@Composable
fun DamagePhotosScreenEmptyPreview() {
    MaterialTheme {
        DamagePhotosScreen(
            uiState = DamagePhotosUiState(
                photos = emptyList(),
                maxPhotos = 5
            ),
            onAction = { /* Do nothing in preview */ },
            onBackClick = { /* Do nothing in preview */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Damage Photos - Selected")
@Composable
fun DamagePhotosScreenSelectedPreview() {
    MaterialTheme {
        DamagePhotosScreen(
            uiState = DamagePhotosUiState(
                photos = listOf(
                    "content://dummy/photo1.jpg".toUri(),
                    "content://dummy/photo2.jpg".toUri(),
                    "content://dummy/photo3.jpg".toUri()
                ),
                maxPhotos = 5
            ),
            onAction = { /* Do nothing in preview */ },
            onBackClick = { /* Do nothing in preview */ }
        )
    }
}