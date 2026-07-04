package com.policyboss.customer.feature.privilege.ui.privilegeScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.policyboss.customer.ui.theme.AppColors.TextGray
import com.policyboss.customer.ui.theme.AppColors

@Composable
 fun PrivilegeTrustSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Why 1L+ users trust us",
            color = TextGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PrivilegeTrustItem(icon = Icons.Default.Check, text = "No spams")
            PrivilegeTrustItem(icon = Icons.Default.ThumbUp, text = "Hassle free")
            PrivilegeTrustItem(icon = Icons.Default.Lock, text = "100% safe")
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeTrustSectionPreview() {
    PrivilegeTrustSection()
}