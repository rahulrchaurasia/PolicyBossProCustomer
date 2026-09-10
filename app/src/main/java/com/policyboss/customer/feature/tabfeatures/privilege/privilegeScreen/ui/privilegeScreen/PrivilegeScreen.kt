package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.privillageState.PrivilegeAction
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.privillageState.PrivilegeUiState
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeAssistanceSection
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeBenefitsSection
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeHeaderSection
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeStepsSection
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeTrustSection

import com.policyboss.customer.ui.components.floatingVideo.FloatingVideoPlayer
import com.policyboss.customer.ui.theme.AppColors



@Composable
fun PrivilegeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: PrivilegeUiState,
    exoPlayer: ExoPlayer?, // Injected from ViewModel
    onAction: (PrivilegeAction) -> Unit
) {

    // 2. Applied ONLY to the root element
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.DarkBackground)
    ) {

        LazyColumn(

            // 3. Child starts fresh with capital-M 'Modifier'
            //ex. Modifier.fillMaxSize()
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 0.dp, // Force 0 top padding
                bottom = contentPadding.calculateBottomPadding()
            )

        )
        {
            item {
                PrivilegeHeaderSection(onAction = onAction)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                PrivilegeStepsSection(currentStep = uiState.currentSetupStep)
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                PrivilegeBenefitsSection()
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                PrivilegeAssistanceSection(onAction = onAction)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                PrivilegeTrustSection()
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // 3. The Floating Video Overlay

        if (uiState.isFloatingVideoVisible && exoPlayer != null) {
            FloatingVideoPlayer(
                player = exoPlayer,
                onCloseClick = { onAction(PrivilegeAction.CloseFloatingVideo) },
                onVideoClick = { onAction(PrivilegeAction.OnVideoClick) }, // ADD THIS
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Pin to bottom right
                    .padding(
                        end = 16.dp,
                        // Dynamically add the BottomBar height PLUS a 16dp margin!
                        bottom = contentPadding.calculateBottomPadding() + 16.dp
                    )
            )
        }
    }

}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF131722, // DarkBackground
    device = "id:pixel_5", // Optional: Gives a standard phone frame size
    showSystemUi = true
)
@Composable
private fun PrivilegeScreenPreview() {
    // Wrap in your AppTheme if you have one, e.g., MyPolicyAppTheme { ... }
    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.PrivilegeScreen(
        contentPadding = PaddingValues(bottom = 16.dp), // Mock Scaffold padding
        uiState = PrivilegeUiState(
            isLoading = false,
            currentSetupStep = 2 // Shows Step 1 completed, Step 2 active
        ),
        onAction = {

        },
        modifier = Modifier,
        exoPlayer = null // FIX: Pass null here to prevent Preview crashes!
    )
}
