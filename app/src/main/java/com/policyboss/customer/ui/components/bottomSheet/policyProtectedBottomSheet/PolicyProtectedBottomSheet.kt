package com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.model.vault.VaultPolicy
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.FloatingCloseButton

import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.PolicyHeaderImage
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.PolicyTitle
import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.card.PolicyDetailsCard

/*
User click
     │
     ▼

HomeScreen

     │
     ▼

onAction(
 OnShowPolicyBottomSheetClick
)
     │
     ▼
 HomeRoute
     │
     ▼
viewModel.onAction()
     │
     ▼
HomeViewModel
     │
     ▼
_uiState.update {

   selectedVaultPolicy =
      policy
}

     │

     ▼

HomeRoute

collect uiState

     │

     ▼

HomeScreen

     │

     ▼

PolicyProtectedBottomSheet

 */

private val policySheetGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF7FBCFF),
        Color(0xFF1887FF),
      //  Color(0xFF2F86F0)
    )


)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyProtectedBottomSheet(
    policy: VaultPolicy,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )



    ModalBottomSheet(
        onDismissRequest = onDismiss,

        sheetState = sheetState,

        dragHandle = null,

        containerColor = Color.Transparent,

        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {


            Column(
                modifier = Modifier
                    .padding(top = 24.dp)

                    .fillMaxWidth()

                    .clip(
                        RoundedCornerShape(
                            topStart = 36.dp,
                            topEnd = 36.dp
                        )
                    )

                    .background(
                        brush = policySheetGradient
                    )

                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 28.dp,
                        bottom = 20.dp
                    ),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PolicyHeaderImage()

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                PolicyTitle(title = policy.title)

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                PolicyDetailsCard(

                    companyLogo = policy.companyLogo,

                    idv = policy.idv,

                    premium = policy.premium,

                    expiry = policy.expiry
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                PrimaryCTAButton(
                    text = "Download Policy",

                    onClick = {

                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }

            FloatingCloseButton(
                onDismiss = onDismiss,

                modifier = Modifier
                    .align(
                        Alignment.TopCenter
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PolicyProtectedBottomSheetPreview() {

    PolicyProtectedBottomSheet(

        policy = AppDummyData.vaultPolicies.first(),

        onDismiss = {}
    )
}

