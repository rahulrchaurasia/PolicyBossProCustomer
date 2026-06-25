package com.policyboss.customer.feature.joinPrivilege.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp


@Composable
fun JoinPrivilegeScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        AppTopBar(
            title = "Join Privilege",
            onBackClick = onBackClick
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Join Privilege Screen",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun JoinPrivilegeScreenPreview() {

    PolicyBossCustomerTheme {

        JoinPrivilegeScreen(
            onBackClick = {},
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}