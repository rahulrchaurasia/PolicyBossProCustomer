package com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.FloatingCloseButton
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.PolicyDetailsCard
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.PolicyHeaderImage
import com.policyboss.customer.ui.components.bottomSheet.policyProtectedBottomSheet.component.PolicyTitle
import com.policyboss.customer.ui.components.button.PrimaryCTAButton

private val policySheetGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF58AAFF),
        Color(0xFF3B95FF),
        Color(0xFF2F86F0)
    )
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyProtectedBottomSheet(
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

                PolicyTitle()

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                PolicyDetailsCard()

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

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)

@Composable
fun PreviewPolicyProtectedBottomSheet() {
    // Wrap in your app's theme if you have one (e.g., AppTheme { ... })
    MaterialTheme {
        // We use a Box with BottomCenter alignment to simulate the bottom of the screen
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            PolicyProtectedBottomSheet(
                onDismiss = { /* Do nothing in preview */ }
            )
        }
    }
}