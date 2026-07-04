package com.policyboss.customer.feature.privilege.ui.privilegeScreen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.R
import com.policyboss.customer.ui.theme.AppColors

private val DarkBackground = Color(0xFF131722)
private val CardBackground = Color(0xFF1E2330)
private val PrimaryYellow = Color(0xFFFDB833)
private val TextGray = Color(0xFFA0AAB9)

@Composable
 fun PrivilegeStepItem(
    stepNumber: Int,
    text: String,
    isCompleted: Boolean,  // e.g., true for Step 1
    isActive: Boolean,
    isLast: Boolean = false
) {
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
                    if (!isLast) {
                        drawLine(
                            color = Color.Gray.copy(alpha = 0.3f),
                            start = Offset(size.width / 2, size.height / 2),
                            end = Offset(size.width / 2, size.height + 20.dp.toPx()), // Extend to next item
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Surface(shape = CircleShape, color = PrimaryYellow, modifier = Modifier.size(20.dp)) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.Black, modifier = Modifier.padding(2.dp))
                }
            } else {
                Surface(
                    shape = CircleShape,
                    color = if (isActive) PrimaryYellow else Color.Transparent,
                    contentColor = if (isActive) Color.Black else TextGray,
                    border = if (!isActive) BorderStroke(1.dp, TextGray) else null,
                    modifier = Modifier.size(20.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = stepNumber.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

//        Text(
//            text = text,
//            color = if (isActive) Color.White else TextGray,
//            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
//            fontSize = if (isActive) 16.sp else 14.sp,
//            textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
//            modifier = Modifier.weight(1f)
//        )

        Text(
            text = text,
            // 1. Strike through the text ONLY if the step is completed
            textDecoration = if (isCompleted) {
                TextDecoration.LineThrough
            } else {
                TextDecoration.None
            },
            // 2. Dim the color if completed so it fades into the background like the screenshot
            color = when {
                isActive -> Color.White
                isCompleted -> Color.Gray.copy(alpha = 0.5f)
                else -> Color.Gray
            },
            fontSize = if (isActive) 16.sp else 14.sp,
            modifier = Modifier.weight(1f)
        )

        if (isActive) {
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null, tint = Color.White)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1E2330)
@Composable
private fun PrivilegeStepItemPreview() {
    Column {
        PrivilegeStepItem(stepNumber = 1, text = "Completed Step", isCompleted = true, isActive = false)
        PrivilegeStepItem(stepNumber = 2, text = "Active Step", isCompleted = false, isActive = true)
        PrivilegeStepItem(stepNumber = 3, text = "Upcoming Step", isCompleted = false, isActive = false, isLast = true)
    }
}