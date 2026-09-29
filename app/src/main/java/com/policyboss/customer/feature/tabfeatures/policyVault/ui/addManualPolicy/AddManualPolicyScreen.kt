package com.policyboss.customer.feature.tabfeatures.policyVault.ui.addManualPolicy




import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState.AddPolicyAction
import com.policyboss.customer.feature.tabfeatures.policyVault.model.AddPolicyState.AddPolicyUiState
import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.AddPolicyType

import com.policyboss.customer.ui.components.button.PrimaryCTAButton
import com.policyboss.customer.ui.components.divider.OrDivider
import com.policyboss.customer.ui.components.loading.AppLoadingOverlay
import com.policyboss.customer.ui.components.textfield.AppOutlinedTextField
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.headlineMediumBold

@Composable
fun AddManualPolicyScreen(
    uiState: AddPolicyUiState,
    contentPadding: PaddingValues,
    policyType: AddPolicyType,
    onAction: (AddPolicyAction) -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 1. Setup Focus and Scroll Management
    val focusManager = LocalFocusManager.current
    val policyFocusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()

    // =========================================
    // WRAP THE ENTIRE SCREEN IN THE OVERLAY
    // =========================================
    AppLoadingOverlay(
        isLoading = uiState.isLoading,
        message = "Saving Policy..." // Custom message for this specific screen
    ){
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = Color.White,
            topBar = {
                AppTopBar(
                    title = "",
                    onBackClick = onBackClick,
                    backIconTint = AppColors.TextPrimary,

                    trailingIcon = painterResource(id = R.drawable.ic_close),
                    trailingIconTint = AppColors.TextPrimary,
                    onTrailingClick = onCloseClick
                )
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    // 2. Apply Window Insets correctly
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = contentPadding.calculateBottomPadding(),
                        start = 24.dp,
                        end = 24.dp
                    )
                    // 3. Make content scrollable & react to keyboard appearing
                    .imePadding()
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_car),
                    contentDescription = "Motor Insurance",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(56.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Motor Insurance",
                    style = MaterialTheme.typography.headlineMediumBold, // Using requested typography
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(32.dp))

                // =====================================
                // CAR NUMBER INPUT
                // =====================================
                Text(
                    text = "Car number",
                    style = MaterialTheme.typography.headlineMedium,
                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))

                AppOutlinedTextField(
                    value = uiState.carNumber,
                    onValueChange = { onAction(AddPolicyAction.OnCarNumberChanged(it)) },
                    placeholder = "Enter your car number",
                    // 4. Keyboard configured for "Next"
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            // Move focus to the Policy Number field
                            policyFocusRequester.requestFocus()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                OrDivider()

                Spacer(modifier = Modifier.height(24.dp))

                // =====================================
                // POLICY NUMBER INPUT
                // =====================================
                Text(
                    text = "Policy number",
                    style = MaterialTheme.typography.headlineMedium,
                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))

                AppOutlinedTextField(
                    // 5. Attach the FocusRequester
                    modifier = Modifier.focusRequester(policyFocusRequester),
                    value = uiState.policyNumber,
                    onValueChange = { onAction(AddPolicyAction.OnPolicyNumberChanged(it)) },
                    placeholder = "Enter your policy number",
                    // 6. Keyboard configured for "Done"
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // Drop the keyboard
                            focusManager.clearFocus()


                            // regionOptionally trigger submit if form is valid
//                            if (uiState.isFormValid) {
//                                onAction(AddPolicyAction.OnSubmit)
//                            }
                            //endregion
                        }
                    )
                )

                // 7. Spacer with weight pushes the button down if the screen is tall enough,
                // but the scrollState ensures it's always reachable if the keyboard pushes it up.
                Spacer(modifier = Modifier.weight(1f, fill = false))
                Spacer(modifier = Modifier.height(32.dp))

                // =====================================
                // SUBMIT BUTTON
                // =====================================
                PrimaryCTAButton(
                    text = "Confirm and continue",
                    textStyle = MaterialTheme.typography.labelLarge,
                    enabled = uiState.isFormValid,
                    onClick = {
                        focusManager.clearFocus() // Always clear focus on manual click
                        onAction(AddPolicyAction.OnSubmit)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Car Policy Form - Empty")
@Composable
fun AddManualPolicyScreenCarPreview() {
    MaterialTheme {
        AddManualPolicyScreen(
            // 1. Pass a default (empty) state
            uiState = AddPolicyUiState(
                carNumber = "",
                policyNumber = ""
            ),
            contentPadding = PaddingValues(),
            policyType = AddPolicyType.CAR,
            onAction = {}, // 2. Pass an empty lambda for actions
            onBackClick = {},
            onCloseClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Car Policy Form - Filled")
@Composable
fun AddManualPolicyScreenCarFilledPreview() {
    MaterialTheme {
        AddManualPolicyScreen(
            // Example of previewing a filled-out state (button will be enabled)
            uiState = AddPolicyUiState(
                carNumber = "MH-12-AB-1234",
                policyNumber = ""
            ),
            contentPadding = PaddingValues(),
            policyType = AddPolicyType.CAR,
            onAction = {},
            onBackClick = {},
            onCloseClick = {},
            modifier = Modifier
        )
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Health Policy Form")
//@Composable
//fun AddManualPolicyScreenHealthPreview() {
//    MaterialTheme {
//        AddManualPolicyScreen(
//            contentPadding = PaddingValues(),
//            policyType = AddPolicyType.HEALTH,
//            onBackClick = {},
//            onCloseClick = {}, // Added missing parameter
//            onSavePolicy = { carNumber, policyNumber -> } // Updated lambda signature
//        )
//    }
//}
