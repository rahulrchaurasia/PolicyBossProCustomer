

package com.policyboss.customer.feature.profile.ui


import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.profile.model.ProfileUiState
import com.policyboss.customer.feature.profile.ui.component.ProfileAvatarHeader
import com.policyboss.customer.feature.profile.ui.component.ProfileMenuItem
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors

// =========================================================
// ProfileScreen.kt
// =========================================================
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: ProfileUiState, // 🚀 CHANGE 9: Accept State
    onCloseClick:() -> Unit,
    onLogoutClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.Background) // Using your background color
            .verticalScroll(scrollState)
            .padding(bottom = contentPadding.calculateBottomPadding()),


        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       // Spacer(modifier = Modifier.height(32.dp))
        // 2. TopBar sits exactly at the top. It handles its own status bar padding.
        AppTopBar(
            title = "",
            trailingIcon = painterResource(id = R.drawable.ic_close),
            onTrailingClick = onCloseClick
        )
    // 3. Wrap your inner content in another Column to apply your 20.dp horizontal padding
        // without accidentally shrinking the AppTopBar's width.
        // 1. Profile Header (Avatar + Details)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileAvatarHeader(
                userName = uiState.userName,
                userEmail = uiState.userEmail.ifEmpty {"test@gmail.com" },
                onEditAvatarClick = { /* Handle Image Upload */ }
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 2. Menu Sections (Wrapped in a modern Card/Surface)
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White, // Or AppColors.Surface
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    ProfileMenuItem(
                        icon = Icons.Outlined.Person,
                        title = "Personal Information",
                        onClick = { /* Navigate to Details */ }
                    )
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(horizontal = 16.dp))

                    ProfileMenuItem(
                        icon = Icons.Outlined.Policy,
                        title = "My Policies",
                        onClick = { /* Navigate to Policies */ }
                    )
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(horizontal = 16.dp))

                    ProfileMenuItem(
                        icon = Icons.Outlined.Settings,
                        title = "Settings",
                        onClick = { /* Navigate to Settings */ }
                    )
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), modifier = Modifier.padding(horizontal = 16.dp))

                    ProfileMenuItem(
                        icon = Icons.AutoMirrored.Outlined.HelpOutline,
                        title = "Help & Support",
                        onClick = { /* Navigate to Support */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 3. Logout Button (Outlined, destructive style)
            OutlinedButton(
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFD32F2F) // Standard Material Red for Logout
                ),
                border = BorderStroke(1.dp, Color(0xFFD32F2F).copy(alpha = 0.5f))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = "Logout",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Logout",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // App Version Footer
            Text(
                text = "Version 1.0.0",
                style = MaterialTheme.typography.labelMedium,
                color = AppColors.TextSecondary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }


    }
}

// ==========================================
// PREVIEWS
// ==========================================




@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProfileScreenPreview() {
    MaterialTheme {
        // Use a dummy Scaffold to simulate the screen constraints
        Scaffold { innerPadding ->
            ProfileScreen(
                contentPadding = innerPadding,
                onLogoutClick = {},

                onCloseClick = {},

                uiState = ProfileUiState()
            )
        }
    }
}