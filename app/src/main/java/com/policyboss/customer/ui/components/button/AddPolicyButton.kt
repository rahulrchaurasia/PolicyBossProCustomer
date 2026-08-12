package com.policyboss.customer.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun AddPolicyButton(
    modifier: Modifier = Modifier,
    text: String = "Add new policy", // Updated to sentence case matching the image
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            // 1. Height remains fixed
            .height(52.dp)
            // 2. Border perfectly follows the circle shape
            .border(
                width = 1.dp,
                color = AppColors.Border,
                shape = CircleShape
            )
            // 3. Clip ensures the ripple stays inside the pill shape
            .clip(CircleShape)
            .background(color = AppColors.White)
            .clickable(onClick = onClick)
            // Increased horizontal padding slightly to balance the deeper curves
            .padding(horizontal = 24.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )

        Icon(
            painter = painterResource(R.drawable.ic_add_circle),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Preview(
    name = "Add New Policy Button",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5
)
@Composable
private fun AddNewPolicyButtonPreview() {

    MaterialTheme {

        AddPolicyButton(

            modifier = Modifier.padding(16.dp),

            onClick = {}
        )
    }
}