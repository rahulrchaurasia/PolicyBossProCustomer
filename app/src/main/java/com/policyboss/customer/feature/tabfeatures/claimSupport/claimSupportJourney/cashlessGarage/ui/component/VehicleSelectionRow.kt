package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.ui.component


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportJourney.cashlessGarage.model.VehicleType
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun VehicleSelectionRow(
    type: VehicleType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconRes = when (type) {
        VehicleType.CAR -> R.drawable.ic_car
        VehicleType.BIKE -> R.drawable.ic_bike
        VehicleType.CV -> R.drawable.ic_cv // Replace with your actual icons
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) AppColors.PrimaryBlue else AppColors.BorderSecondary,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = if (isSelected) AppColors.PrimaryBlue else AppColors.TextSecondary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Text(
            text = type.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary,
            modifier = Modifier.weight(1f)
        )
        
        RadioButton(
            selected = isSelected,
            onClick = null, // handled by Row click
            colors = RadioButtonDefaults.colors(selectedColor = AppColors.PrimaryBlue)
        )
    }
}