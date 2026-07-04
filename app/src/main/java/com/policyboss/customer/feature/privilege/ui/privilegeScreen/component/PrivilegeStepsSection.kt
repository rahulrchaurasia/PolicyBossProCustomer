package com.policyboss.customer.feature.privilege.ui.privilegeScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.AppColors


@Composable
 fun PrivilegeStepsSection(currentStep: Int) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Earning on renewals is just few steps away!",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AppColors.CardBackground,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                PrivilegeStepItem(
                    stepNumber = 1,
                    text = "Add your car policy details",
                    isCompleted = currentStep > 1,
                    isActive = currentStep == 1
                )
                PrivilegeStepItem(
                    stepNumber = 2,
                    text = "Set up 'Privilege Account'",
                    isCompleted = currentStep > 2,
                    isActive = currentStep == 2
                )
                PrivilegeStepItem(
                    stepNumber = 3,
                    text = "Complete an easy quiz",
                    isCompleted = currentStep > 3,
                    isActive = currentStep == 3
                )
                PrivilegeStepItem(
                    stepNumber = 4,
                    text = "Upload documents",
                    isCompleted = currentStep > 4,
                    isActive = currentStep == 4,
                    isLast = true
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF131722)
@Composable
private fun PrivilegeStepsSectionPreview() {
    PrivilegeStepsSection(currentStep = 2)
}