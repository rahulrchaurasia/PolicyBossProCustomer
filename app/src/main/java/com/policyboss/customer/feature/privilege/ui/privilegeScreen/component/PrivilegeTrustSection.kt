package com.policyboss.customer.feature.privilege.ui.privilegeScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors.TextGray

@Composable
 fun PrivilegeTrustSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Why 1L+ users trust us",
            style = MaterialTheme.typography.titleSmall,
            color = TextGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PrivilegeTrustItem(icon = R.drawable.ic_spam, text = "No spams")
            PrivilegeTrustItem(icon = R.drawable.ic_hassle ,text = "Hassle free")
            PrivilegeTrustItem(icon = R.drawable.ic_lock, text = "100% safe")
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeTrustSectionPreview() {
    PrivilegeTrustSection()
}