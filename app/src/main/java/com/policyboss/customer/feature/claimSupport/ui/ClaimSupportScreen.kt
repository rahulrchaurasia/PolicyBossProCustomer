package com.policyboss.customer.feature.claimSupport.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeAction
import com.policyboss.customer.feature.privilege.model.privilegeState.PrivilegeUiState
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme


@Composable
fun ClaimSupportScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiState: PrivilegeUiState, // Your data class from ViewModel
    onAction: (PrivilegeAction) -> Unit

) {

    Column(
        modifier = modifier.fillMaxSize()
            .background(AppColors.Background)
            .padding(contentPadding),
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Claim Support ",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(
    name = "Claim Support Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ClaimSupportScreenPreview() {

    PolicyBossCustomerTheme {

        ClaimSupportScreen(
            modifier = Modifier,
            contentPadding = PaddingValues(),
            uiState = PrivilegeUiState(),
            onAction = {}
        )
    }
}