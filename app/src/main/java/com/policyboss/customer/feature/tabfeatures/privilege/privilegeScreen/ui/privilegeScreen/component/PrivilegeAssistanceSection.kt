package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.privillageState.PrivilegeAction

import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.TextGray
import com.policyboss.customer.ui.theme.bodyMediumNormal
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
 fun PrivilegeAssistanceSection(
    onAction: (PrivilegeAction) -> Unit
 ) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Still having doubts?",
            color = TextGray,
            style = MaterialTheme.typography.titleSmall,

            modifier = Modifier.padding(bottom = 8.dp)
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AppColors.DarkCardBackground,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* onAction(PrivilegeAction.OnGetAssistanceClick) */ }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(

                    painter = painterResource(R.drawable.ic_headset),
                    contentDescription = "Assistance",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(32.dp)

                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Get Assistance",
                        style = MaterialTheme.typography.labelMediumSemiBold,
                        color = Color.White )

                    Text(text = "Connect with your RM", color = TextGray,
                        style = MaterialTheme.typography.bodyMediumNormal,
                     )
                }


                Icon(

                    painter = painterResource(
                        id = R.drawable.ic_chevron_right
                    ),
                    contentDescription = null,
                    tint = AppColors.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeAssistanceSectionPreview() {
    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeAssistanceSection(
        onAction = {})
}