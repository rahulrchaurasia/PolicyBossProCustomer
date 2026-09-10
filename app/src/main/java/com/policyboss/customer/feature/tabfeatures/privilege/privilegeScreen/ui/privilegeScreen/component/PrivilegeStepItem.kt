package com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.PrimaryYellow
import com.policyboss.customer.ui.theme.bodyMediumNormal
import com.policyboss.customer.ui.theme.labelSmallSemiBold


@Composable
 fun PrivilegeStepItem(
    stepNumber: Int,
    text: String,
    isCompleted: Boolean,  // e.g., true for Step 1
    isActive: Boolean,
    isLast: Boolean = false
) {

    // ----------------------------
    // UI State
    // ----------------------------
     //region UIState
    val stepTextStyle = if (isActive) {
        MaterialTheme.typography.bodyLarge.copy(
            color = AppColors.White
        )
    } else {
        MaterialTheme.typography.bodyMediumNormal.copy(
            color = if (isCompleted) {
                AppColors.TextSecondary.copy(alpha = 0.5f)
            } else {
                AppColors.TextSecondary
            }
        )
    }

    val textDecoration = if (isCompleted) {
        TextDecoration.LineThrough
    } else {
        TextDecoration.None
    }

    //endregion

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), // Fixed height to easily draw the vertical connecting line
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Step Indicator with connecting line drawn behind
        Box(
            modifier = Modifier
                .width(24.dp)
                .fillMaxHeight()
                .drawBehind {

                    val centerX = size.width / 2f
                    val centerY = size.height / 2f

                    // Top line
                    if (stepNumber > 1) {
                        drawLine(
                            color = if (isCompleted || isActive) {
                                AppColors.PrimaryYellow
                            } else {
                                AppColors.White
                            },
                            start = Offset(centerX, 0f),
                            end = Offset(centerX, centerY),
                            strokeWidth = 2.dp.toPx()
                        )
                    }

                    // Bottom line
                    if (!isLast) {
                        drawLine(
                            color = if (isCompleted) {
                                AppColors.PrimaryYellow
                            } else {
                                AppColors.White
                            },
                            start = Offset(centerX, centerY),
                            end = Offset(centerX, size.height),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                },
            contentAlignment = Alignment.Center
        )

        {
            if (isCompleted) {
                Surface(shape = CircleShape, color = AppColors.PrimaryYellow, modifier = Modifier.size(20.dp)) {
                    Icon(

                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        tint = AppColors.White,
                        modifier = Modifier.padding(2.dp)

                    )
                }
            } else {

                Surface(
                    shape = CircleShape,
                    color = if (isActive) PrimaryYellow else AppColors.White,
                    contentColor = if (isActive) Color.White else AppColors.TextPrimary,
                    border = if (!isActive) BorderStroke(1.dp, AppColors.TextGray) else null,
                    modifier = Modifier.size(20.dp)
                )
                {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stepNumber.toString(),
                            style = MaterialTheme.typography.labelSmallSemiBold
                        )
                    }
                }
            }

        }

        Spacer(modifier = Modifier.width(16.dp))


        Text(
            text = text,
            style = stepTextStyle,
            textDecoration = textDecoration,
            modifier = Modifier.weight(1f)
        )

        if (isActive) {

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

@Preview(showBackground = true, backgroundColor = 0xFF1E2330)
@Composable
private fun PrivilegeStepItemPreview() {
    Column {
        _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeStepItem(
            stepNumber = 1,
            text = "Completed Step",
            isCompleted = true,
            isActive = false
        )
        _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeStepItem(
            stepNumber = 2,
            text = "Active Step",
            isCompleted = false,
            isActive = true
        )
        _root_ide_package_.com.policyboss.customer.feature.tabfeatures.privilege.privilegeScreen.ui.privilegeScreen.component.PrivilegeStepItem(
            stepNumber = 3,
            text = "Upcoming Step",
            isCompleted = false,
            isActive = false,
            isLast = true
        )
    }
}