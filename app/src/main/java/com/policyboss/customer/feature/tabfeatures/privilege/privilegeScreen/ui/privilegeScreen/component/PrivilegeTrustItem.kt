package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.labelMediumSemiBold

@Composable
fun PrivilegeTrustItem(
    @DrawableRes icon: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified, // Preserve original vector colors
            modifier = Modifier
                .size(width = 27.dp, height = 24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.labelMediumSemiBold.copy(
                color = AppColors.White
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeTrustItemPreview() {
    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeTrustItem(
        icon = R.drawable.ic_lock,
        text = "100% safe"
    )
}