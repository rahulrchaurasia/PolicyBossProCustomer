package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.bodyMediumSemiBold

@Composable
 fun PrivilegeBenefitItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = AppColors.GoldText,
            modifier = Modifier.size(16.dp)
        ) {

            Icon(

                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = AppColors.White,
                modifier = Modifier.padding(2.dp)

            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = AppColors.GoldText,
            style = MaterialTheme.typography.bodyMediumSemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDB833)
@Composable
private fun PrivilegeBenefitItemPreview() {
    _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeBenefitItem(
        text = "Help your network renew & 3X your income potential"
    )
}