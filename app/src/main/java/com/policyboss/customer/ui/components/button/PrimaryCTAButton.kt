package com.policyboss.customer.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.labelMediumSemiBold




@Composable
fun PrimaryCTAButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    showArrow: Boolean = true,
    hideKeyboardOnEmit: Boolean = true, // 🚀 Control flag if ever needed, defaults to true
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    containerColor: Color = AppColors.DarkBackground,
    contentColor: Color = Color.White,
    arrowBackgroundColor: Color = Color.White,
    arrowTint: Color = AppColors.DarkBackground,
    shape: Shape = RoundedCornerShape(999.dp)
) {

    // 🚀 Get focus manager and keyboard controller inside the button
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        onClick = {
            if (hideKeyboardOnEmit) {
                // 🚀 Automatically clear focus and hide keyboard before running action
                focusManager.clearFocus()
                keyboardController?.hide()
            }
            onClick()
        },
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = AppColors.ButtonDisabled,
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        ),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {



        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = text,
                style = textStyle,
                color = contentColor,
                modifier = Modifier.align(Alignment.Center)
            )

            if (showArrow) {

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = arrowBackgroundColor,
                            shape = CircleShape
                        )
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = arrowTint,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PrimaryCTAButtonAllStatesPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        PrimaryCTAButton(
            text = "Get Started",
            onClick = {},
            showArrow = false
        )
        PrimaryCTAButton(
            text = "Set up Privilege Account",
            onClick = { },
            textStyle = MaterialTheme.typography.labelMediumSemiBold
        )
        PrimaryCTAButton(
            text = "Continue",
            onClick = {}
        )



        PrimaryCTAButton(
            text = "Disabled",
            onClick = {},
            enabled = false
        )
    }
}