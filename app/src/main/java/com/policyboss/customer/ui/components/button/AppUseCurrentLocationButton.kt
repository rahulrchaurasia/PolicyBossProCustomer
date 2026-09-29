package com.policyboss.customer.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun AppUseCurrentLocationButton(
    text : String = "Use Current Location",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, AppColors.PrimaryBlue),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = AppColors.PrimaryBlue)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location), 
            contentDescription = "Location Icon", 
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun CardButtonPreview() {

    MaterialTheme {

        AppUseCurrentLocationButton(
            text = "Use Current Location",
            onClick = {}
        )
    }
}