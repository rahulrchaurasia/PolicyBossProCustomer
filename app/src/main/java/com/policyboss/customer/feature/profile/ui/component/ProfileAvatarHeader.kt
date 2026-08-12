package com.policyboss.customer.feature.profile.ui.component



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors

// ==========================================
// REUSABLE SUB-COMPONENTS
// ==========================================

@Composable
fun ProfileAvatarHeader(
    userName: String,
    userEmail: String,
    onEditAvatarClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Avatar Box with Edit Badge
        Box(contentAlignment = Alignment.BottomEnd) {
            // Main Circular Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(AppColors.BrandBlue.copy(alpha = 0.1f))
                    .border(2.dp, AppColors.BrandBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Replace this Icon with AsyncImage/Coil when you load real URLs
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile Picture",
                    tint = AppColors.BrandBlue,
                    modifier = Modifier.size(48.dp)
                )
            }

            // Edit Camera Badge Overlay
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .offset(x = (-4).dp, y = (-4).dp)
                    .clip(CircleShape)
                    .background(AppColors.BrandBlue)
                    .border(2.dp, Color.White, CircleShape)
                    .clickable { onEditAvatarClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.CameraAlt,
                    contentDescription = "Edit Profile Picture",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Details
        Text(
            text = userName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Black // Or AppColors.TextPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = userEmail,
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.TextSecondary
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ProfileAvatarHeaderPreview() {
    // Wrapping in MaterialTheme to ensure typography loads correctly
    MaterialTheme {
        Surface(
            modifier = Modifier.padding(24.dp),
            color = Color.White
        ) {
            ProfileAvatarHeader(
                userName = "Rahul Chaurasia",
                userEmail = "rahul.chaurasia@policyboss.com",
                onEditAvatarClick = {}
            )
        }
    }
}
